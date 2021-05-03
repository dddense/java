package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semestralwork.dto.PostDto;
import ru.itis.semestralwork.dto.UserDto;
import ru.itis.semestralwork.models.User;
import ru.itis.semestralwork.security.details.UserDetailsImpl;
import ru.itis.semestralwork.services.ConfirmationTokensService;
import ru.itis.semestralwork.services.UsersService;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static ru.itis.semestralwork.dto.UserDto.from;

@RestController
public class TestController {

    @Autowired
    private ConfirmationTokensService confirmationTokensService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/token/{token}")
    public ResponseEntity<String> getUserByToken(@PathVariable("token") String token) {

        User user = confirmationTokensService.confirmUserByToken(token)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        ConfirmationToken cToken = confirmationTokensService.getByToken(token).get();
//
//        return ResponseEntity.ok(cToken.getConfirmationToken());

        return ResponseEntity.ok(user.getEmail());
    }

    @GetMapping("/postTest")
    public String getPosts(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        User user = userDetails.getUser();
        List<User> users = usersService.getAllUsers();
        for (User u : users) {
            usersService.addUserIntoSubscriptions(user, u);
        }

        Set<UserDto> subs = from(user.getSubscriptions());
        List<PostDto> posts = new LinkedList<>();
        for (UserDto sub : subs) {
            posts.addAll(usersService.getPostsByUserId(sub.getId()));
        }

        model.addAttribute("posts", posts);

        return "news_page";
    }
}