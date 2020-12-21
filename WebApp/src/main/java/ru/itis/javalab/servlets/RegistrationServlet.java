package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.CookiesService;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {

    private UsersService usersService;
//    private CookiesService cookiesService;

    @Override
    public void init(ServletConfig config) {

//        this.usersService = (UsersService) config.getServletContext().getAttribute("usersService");
//        this.cookiesService = (CookiesService) config.getServletContext().getAttribute("cookiesService");
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        usersService = context.getBean(UsersServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("Authenticated", "false");
//        req.getRequestDispatcher("/jsp/reg.jsp").forward(req, resp);
        req.getRequestDispatcher("/ftlh/reg.ftlh").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String uri = req.getRequestURI();
        if (uri.equals("/login") || uri.equals("/reg")) {
            uri = "/";
        }

        if (!usersService.getUserByUsername(username).isEmpty()) {
            resp.sendRedirect("/login");
        } else {
            User user = User.builder()
                    .username(username)
                    .password(usersService.setPassword(password))
                    .uuid(String.valueOf(UUID.randomUUID()))
                    .build();
            usersService.addUser(user);

//            Cookie cookie = new Cookie("Auth", user.getUuid());
//            cookie.setMaxAge(60 * 60 * 24);
//            resp.addCookie(cookie);
//            cookiesService.add(cookie.getValue());
            req.getSession().setAttribute("Authenticated", "true");

            resp.sendRedirect(uri);
        }
    }
}