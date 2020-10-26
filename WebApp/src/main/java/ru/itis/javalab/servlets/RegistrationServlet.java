package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;
    private CookiesService cookiesService;

    @Override
    public void init(ServletConfig config) {

        this.usersService = (UsersService) config.getServletContext().getAttribute("usersService");
        this.cookiesService = (CookiesService) config.getServletContext().getAttribute("cookiesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/jsp/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (!usersService.getUserByUsername(username).isEmpty()) {
            PrintWriter out = resp.getWriter();
            out.println("Username " + username + " already taken");
            resp.sendRedirect("/reg");
        } else {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .uuid(String.valueOf(UUID.randomUUID()))
                    .build();
            usersService.addUser(user);
            Cookie cookie = new Cookie("uuid", user.getUuid());
            cookie.setMaxAge(60 * 60 * 24);
            resp.addCookie(cookie);
            cookiesService.add(cookie.getValue());
            resp.sendRedirect("/login");
        }
    }
}
