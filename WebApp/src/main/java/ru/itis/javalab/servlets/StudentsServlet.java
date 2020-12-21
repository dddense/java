package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.services.StudentsService;
import ru.itis.javalab.services.StudentsServiceImpl;

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

//        ServletContext servletContext = config.getServletContext();
//        this.studentsService = (StudentsService) servletContext.getAttribute("studentsService");
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        this.studentsService = context.getBean(StudentsServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println(usersService.getAllUsers());
        //System.out.println(usersService.getUsersByAge(20));
        Cookie [] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals("color")) {
                cookie = c;
            }
        }
        request.setAttribute("color", cookie);
        request.setAttribute("students", studentsService.getAll());
//        request.getRequestDispatcher("/jsp/students.jsp").forward(request, response);
        request.getRequestDispatcher("/ftlh/students.ftlh").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String color = req.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
        resp.sendRedirect("/students");
    }
}