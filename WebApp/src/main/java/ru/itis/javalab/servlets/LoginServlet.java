package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {

        this.usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        List<User> users = usersService.getUserByUsername(username);
        String uri = req.getRequestURI();
        if (uri.equals("/login") || uri.equals("/reg")) {
            uri = "/";
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            if (users.isEmpty()) {
                resp.sendRedirect("/reg");
                return;
            }
            User user = users.get(0);
            if (user.getPassword().equals(password)) {
                Cookie auth = new Cookie("Auth", user.getUuid());
                auth.setMaxAge(60 * 60 * 24);
                resp.addCookie(auth);
                resp.sendRedirect(uri);
            } else {
                resp.sendRedirect("/reg");
            }
        }
    }
}
