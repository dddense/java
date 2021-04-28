package ru.itis.restfulapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.restfulapp.dto.EmailPasswordDto;
import ru.itis.restfulapp.dto.TokenDto;
import ru.itis.restfulapp.services.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody EmailPasswordDto emailPassword) {

        return ResponseEntity.ok(loginService.login(emailPassword));
    }
}