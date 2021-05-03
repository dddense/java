package ru.itis.semestralwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.semestralwork.dto.UsersPage;
import ru.itis.semestralwork.services.UsersService;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ResponseBody
    @GetMapping("/users/search")
    public ResponseEntity<UsersPage> search(@RequestParam("size") Integer size,
                                            @RequestParam("page") Integer page,
                                            @RequestParam(value = "q", required = false) String query,
                                            @RequestParam(value = "sort", required = false) String sort,
                                            @RequestParam(value = "dir", required = false) String direction) {


        return ResponseEntity.ok(usersService.search(size, page, query, sort, direction));
    }

    @GetMapping("/users")
    public String getUsersPage() {

        return "search_page";
    }
}
