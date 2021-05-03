package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.semestralwork.dto.UserForm;
import ru.itis.semestralwork.services.UsersService;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/signUp")
    public String getSignUp(Model model) {

        model.addAttribute("userForm", new UserForm());

        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", form);
            return "sign_up";
        } else {
            usersService.addUser(form);
        }

        return "redirect:/signIn";
    }
}
