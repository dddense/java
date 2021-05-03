package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.semestralwork.dto.PostDto;
import ru.itis.semestralwork.dto.PostForm;
import ru.itis.semestralwork.dto.UserDto;
import ru.itis.semestralwork.models.User;
import ru.itis.semestralwork.security.details.UserDetailsImpl;
import ru.itis.semestralwork.services.UsersService;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        User user = userDetails.getUser();
        List<PostDto> posts = PostDto.from(user.getPosts());
        if (posts == null) {
            posts = new LinkedList<>();
        }

        model.addAttribute("user", UserDto.from(user));
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("posts", posts);

        return "my_profile_page";
    }

    @GetMapping("/profile/{user-id}")
    public String getProfileById(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 Model model,
                                 @PathVariable("user-id") Long id) {

        UserDto user = usersService.getById(id);
        List<PostDto> posts = user.getPosts();

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return "profile_page";
    }

    @PostMapping("/profile/{user-id}")
    public String subscribe(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            Model model,
                            @PathVariable("user-id") Long id) {

        usersService
                .addUserIntoSubscriptions(userDetails.getUser(), usersService.getUserById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("Not found")));

        return "redirect:/profile/" + id.toString();
    }
}
