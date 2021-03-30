package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.services.PlayerService;
import ru.itis.javalab.services.TeamsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TeamsController {

    @Autowired
    TeamsService teamsService;
    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/teams/{team-name}", method = RequestMethod.GET)
    public String getTeam(@PathVariable("team-name") String teamName, Model model) {

        model.addAttribute("team_name", teamsService.getByName(teamName).get().getName());
        System.out.println(teamsService.getByName(teamName));

        model.addAttribute("players", playerService.getAll());
        System.out.println(playerService.getAllByTeamName(teamName));
        return "team";
    }
}