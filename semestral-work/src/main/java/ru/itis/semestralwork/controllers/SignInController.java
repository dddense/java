package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.semestralwork.dto.UserForm;
import ru.itis.semestralwork.services.SmsService;

@Controller
public class SignInController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/signIn")
    public String signIn(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "sign_in";
    }
}