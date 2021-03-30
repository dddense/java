package ru.itis.javalab.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.security.details.UserDetailsImpl;

@Controller
public class ProfileController {

    @GetMapping(value = "/profile")
    public String getProfile() {

        return "profile_page";
    }
}
