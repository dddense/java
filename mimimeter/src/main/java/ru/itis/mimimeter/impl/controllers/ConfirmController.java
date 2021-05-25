package ru.itis.mimimeter.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.mimimeter.impl.services.ConfirmationTokensService;

@Controller
public class ConfirmController {

    private ConfirmationTokensService confirmationTokensService;

    @Autowired
    public ConfirmController(ConfirmationTokensService confirmationTokensService) {

        this.confirmationTokensService = confirmationTokensService;
    }

    @GetMapping("/confirm/{code}")
    public String confirm(@PathVariable("code") String code) {

        confirmationTokensService.confirmUserByToken(code);

        return "success_page";
    }
}
