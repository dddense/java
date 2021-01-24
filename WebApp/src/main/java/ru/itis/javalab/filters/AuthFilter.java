package ru.itis.javalab.filters;

import ru.itis.javalab.services.CookiesService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private CookiesService cookiesService;

    @Override
    public void init(FilterConfig filterConfig) {

//        this.cookiesService = (CookiesService) filterConfig.getServletContext().getAttribute("cookiesService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (!uri.equals("/login") && !uri.equals("/reg")) {
//            Cookie[] cookies = request.getCookies();
//            Cookie auth = null;
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("Auth")) {
//                        auth = cookie;
//                        break;
//                    }
//                }
//                if (auth == null || cookiesService.getByValue(auth.getValue()).isEmpty()) {
//                    response.sendRedirect("/login");
//                    return;
//                }
//            }
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("authorized").equals("false")) {
                response.sendRedirect("/login");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
