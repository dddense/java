package ru.itis.mimimeter.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.mimimeter.impl.dto.UserDto;
import ru.itis.mimimeter.impl.models.User;
import ru.itis.mimimeter.impl.security.details.UserDetailsImpl;
import ru.itis.mimimeter.impl.services.UsersService;

@Controller
public class ProfileController {

    private UsersService usersService;

    @Autowired
    public ProfileController(UsersService usersService) {

        this.usersService = usersService;
    }

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        User user = userDetails.getUser();

        model.addAttribute("user", UserDto.from(user));

        return "my_profile_page";
    }
}
