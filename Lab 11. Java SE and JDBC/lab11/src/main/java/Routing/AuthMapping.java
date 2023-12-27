package Routing;

import Service.UserRole;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class AuthMapping {
    private static final Map<String, Predicate<UserRole>> accessByUrl;

    static {
        accessByUrl = new HashMap<String, Predicate<UserRole>>();
        accessByUrl.put("/", role -> true);
        accessByUrl.put("/login", role -> role == UserRole.NONE || role == UserRole.GUEST);
        accessByUrl.put("/home", role -> true);
        accessByUrl.put("/teams/getDevelopersByTeam", role -> role == UserRole.ADMIN || role == UserRole.USER);
        accessByUrl.put("/teams/getDevelopersByProject", role -> role == UserRole.ADMIN || role == UserRole.USER);
        accessByUrl.put("/project/getProjectsByCustomer", role -> role == UserRole.ADMIN);
    }
    public static boolean isAuthorizedToAccess(final IWebRequest request, UserRole role) {
        final String path = request.getPathWithinApplication();
        var predicateEntry = accessByUrl.entrySet().stream()
                .filter(x -> x.getKey().startsWith(path))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(predicateEntry) || path.equals("/")) return true;
        else {
            return predicateEntry.getValue().test(role);
        }
    }

    private AuthMapping() {
        super();
    }
}
