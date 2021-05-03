package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.semestralwork.services.ConfirmationTokensService;

@Controller
public class ConfirmController {

    @Autowired
    private ConfirmationTokensService confirmationTokensService;

    @GetMapping("/confirm/{code}")
    public String confirm(@PathVariable("code") String code) {

        confirmationTokensService.confirmUserByToken(code);

        return "success_page";
    }
}
