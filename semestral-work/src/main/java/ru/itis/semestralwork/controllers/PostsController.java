package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.semestralwork.dto.PostDto;
import ru.itis.semestralwork.dto.PostForm;
import ru.itis.semestralwork.dto.UserDto;
import ru.itis.semestralwork.models.User;
import ru.itis.semestralwork.security.details.UserDetailsImpl;
import ru.itis.semestralwork.services.UsersService;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static ru.itis.semestralwork.dto.UserDto.from;

@Controller
public class PostsController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/posts")
    public String getNews(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        User user = userDetails.getUser();
        Set<UserDto> subs = from(user.getSubscriptions());
        List<PostDto> posts = new LinkedList<>();

        for (UserDto sub : subs) {
            posts.addAll(usersService.getPostsByUserId(sub.getId()));
        }

        model.addAttribute("posts", posts);

        return "news_page";
    }

    @PostMapping("/posts")
    public String addPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                          @Valid PostForm postForm,
                          BindingResult bindingResult,
                          Model model) {

        if (!bindingResult.hasErrors()) {
            User user = userDetails.getUser();
            usersService.addPostByUser(user, postForm.getContent());
        }

        return "redirect:/profile";
    }
}