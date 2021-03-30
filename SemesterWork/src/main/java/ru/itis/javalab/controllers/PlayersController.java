package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.services.PlayerService;
import ru.itis.javalab.services.TeamsService;

import java.lang.reflect.Method;

@Controller
public class PlayersController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/players/{player_name}", method = RequestMethod.GET)
    public String getPlayer(@PathVariable("player_name") String playerName, Model model) {

        Long id = playerService.getByName(playerName).get().getId();
        model.addAttribute("player", playerService.getById(id).get());

        return "player";
    }
}
