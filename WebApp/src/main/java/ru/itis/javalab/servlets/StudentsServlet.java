package ru.itis.javalab.servlets;

import ru.itis.javalab.services.StudentsService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

    private StudentsService studentsService;

    @Override
    public void init(ServletConfig config) {

        ServletContext servletContext = config.getServletContext();
        this.studentsService = (StudentsService) servletContext.getAttribute("studentsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println(usersService.getAllUsers());
        //System.out.println(usersService.getUsersByAge(20));
        request.setAttribute("students", studentsService.getAll());
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
    }
}