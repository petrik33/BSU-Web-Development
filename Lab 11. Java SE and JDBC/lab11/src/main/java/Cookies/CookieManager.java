package Cookies;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
    public static void addLastVisitToCookie(HttpServletRequest request, HttpServletResponse response) {
        var session = request.getSession(true);
        System.out.println("Last visit time in this session : " + session.getAttribute("lastAccessTime"));
        var lastAccessTime = System.currentTimeMillis();
        Cookie lastAccessTimeCookie = new Cookie("lastAccessTime", String.valueOf(lastAccessTime));
        lastAccessTimeCookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(lastAccessTimeCookie);
        session.setAttribute("lastAccessTime", lastAccessTime);
    }

    public static void addCountOfVisitToCookie(HttpServletRequest request, HttpServletResponse response) {
        var session = request.getSession(true);
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        System.out.println("Last count of visit in this session : " + visitCount);
        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount++;
        }
        Cookie visitCountCookie = new Cookie("visitCount", String.valueOf(visitCount));
        visitCountCookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(visitCountCookie);
        session.setAttribute("visitCount", visitCount);
    }
}
