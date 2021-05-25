package ru.itis.mimimeter.impl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.mimimeter.impl.dto.UserForm;

@Controller
public class SignInController {

    @GetMapping("/signIn")
    public String signIn(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "sign_in";
    }
}