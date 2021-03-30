package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.UserForm;

@Controller
public class SignInController {

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String getSignInPage(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "sign_in";
    }
}
