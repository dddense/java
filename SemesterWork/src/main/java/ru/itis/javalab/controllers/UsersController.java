package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {

        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        System.out.println(users);

        return "users_view";
    }
}
