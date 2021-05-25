package ru.itis.mimimeter.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.mimimeter.impl.models.CatForm;
import ru.itis.mimimeter.impl.security.details.UserDetailsImpl;
import ru.itis.mimimeter.impl.services.CatsService;

@Controller
public class CatsController {

    private CatsService catsService;

    @Autowired
    public CatsController(CatsService catsService) {

        this.catsService = catsService;
    }

    @GetMapping("/cats")
    public String getCatsPage() {

        return "cats_page";
    }

    @PostMapping("/cats/add")
    public String uploadCat(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            @RequestParam("cat") CatForm catForm,
                            @RequestParam("image") MultipartFile file) {

        catsService.addCat(catForm, userDetails.getUser(), file);

        return "redirect:/cats";
    }
}
