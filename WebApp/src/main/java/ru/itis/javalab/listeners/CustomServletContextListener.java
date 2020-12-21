package ru.itis.javalab.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.repositories.StudentsRepository;
import ru.itis.javalab.repositories.StudentsRepositoryJdbcImpl;
import ru.itis.javalab.repositories.old.CookiesRepository;
import ru.itis.javalab.repositories.old.CookiesRepositoryJdbcImpl;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        servletContext.setAttribute("springContext", context);

//        ServletContext servletContext = servletContextEvent.getServletContext();
//
//        Properties properties = new Properties();
//        try {
//            properties.load(servletContext.getResourceAsStream("WEB-INF/resources/db.properties"));
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
//        hikariConfig.setDriverClassName(properties.getProperty("db.driver.classname"));
//        hikariConfig.setUsername(properties.getProperty("db.username"));
//        hikariConfig.setPassword(properties.getProperty("db.password"));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//
//        servletContext.setAttribute("dataSource", dataSource);
//
//        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
//        StudentsRepository studentsRepository = new StudentsRepositoryJdbcImpl(dataSource);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        UsersService usersService = new UsersServiceImpl(usersRepository, passwordEncoder);
//
//        CookiesRepository cookiesRepository = new CookiesRepositoryJdbcImpl(dataSource);
//        CookiesService cookiesService = new CookiesServiceImpl(cookiesRepository);
//        StudentsService studentsService = new StudentsServiceImpl(studentsRepository);
//
//        servletContext.setAttribute("usersService", usersService);
//        servletContext.setAttribute("studentsService", studentsService);
//        servletContext.setAttribute("cookiesService", cookiesService);
//        servletContext.setAttribute("passwordEncoder", passwordEncoder);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
