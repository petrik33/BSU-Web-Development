package Filters;

import DataPackage.DAOException;
import Entities.User;
import Service.AuthService;
import Service.UserRole;
import WebControllers.IController;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

import static Routing.AuthMapping.isAuthorizedToAccess;
import static Routing.ControllerMappings.resolveControllerForRequest;

@WebFilter(urlPatterns = "/*")
public class HomeFilter implements Filter {

    private JakartaServletWebApplication application;
    private ITemplateEngine templateEngine;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.application = JakartaServletWebApplication.buildApplication(filterConfig.getServletContext());
        this.templateEngine = buildTemplateEngine(this.application);
    }

    private ITemplateEngine buildTemplateEngine(final IWebApplication application) {
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setCacheable(true);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getRequestURI().equals(httpRequest.getContextPath() + "/login") && httpRequest.getMethod().equalsIgnoreCase("post")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Optional<User> userOptional;
            try {
                userOptional = AuthService.FindUser(username);
            } catch (DAOException e) {
                throw new ServletException(e.getMessage());
            }

            if (userOptional.isEmpty()) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?error=InvalidLogin");
                return;
            }

            if (!AuthService.CheckPassword(userOptional.get(), password)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?error=InvalidPassword");
                return;
            }

            User authenticatedUser = userOptional.get();
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("role", AuthService.GetUserRole(authenticatedUser).name());
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            return;
        }

        if (process((HttpServletRequest) request, (HttpServletResponse) response)) {
            chain.doFilter(request, response);
        }
    }

    private boolean process(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {
        try {
            final IServletWebExchange webExchange = this.application.buildExchange(request, response);
            final IWebRequest webRequest = webExchange.getRequest();
            final Writer writer = response.getWriter();

            if (isStaticResource(webRequest.getPathWithinApplication())) {
                return false;
            }

            UserRole role;
            HttpSession session = request.getSession(false);
            if (session == null) {
                session = request.getSession(true);
                role = UserRole.GUEST;
                session.setAttribute("role", role.name());
            } else {
                String roleName = (String) session.getAttribute("role");
                try {
                    role = UserRole.valueOf(roleName);
                } catch (Exception e) {
                    role = UserRole.GUEST;
                    session.setAttribute("role", role.name());
                }
            }

            if (!isAuthorizedToAccess(webRequest, role)) {
                return false;
            }

            IController controller = resolveControllerForRequest(webRequest);
            if (controller == null) {
                return false;
            }

            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            controller.process(webExchange, templateEngine, writer);
            return true;
        } catch (
                Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // Just ignore this
            }
            throw new ServletException(e);
        }

    }

    private boolean isStaticResource(String path) {
        return path.endsWith(".ico") || path.endsWith(".css") || path.endsWith(".js");
    }
}

