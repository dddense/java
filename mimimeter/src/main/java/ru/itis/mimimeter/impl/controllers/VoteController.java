package ru.itis.mimimeter.impl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.mimimeter.impl.dto.CatDto;
import ru.itis.mimimeter.impl.security.details.UserDetailsImpl;
import ru.itis.mimimeter.impl.services.CatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Controller
public class VoteController {

    private CatsService catsService;

    @Autowired
    public VoteController(CatsService catsService) {

        this.catsService = catsService;
    }

    @GetMapping("/vote")
    public String getVotePage(Model model, HttpServletRequest request) {

        HttpSession session = (HttpSession) request.getSession();

        Stack<Map.Entry<CatDto, CatDto>> pairs = new Stack<>();
        if (session.getAttribute("pairs") == null) {
            Map<CatDto, CatDto> cats = catsService.getAllPairs();
            for (Map.Entry<CatDto, CatDto> cat : cats.entrySet()) {
                pairs.push(cat);
            }
            session.setAttribute("pairs", pairs);
        }
        else {
            pairs = (Stack<Map.Entry<CatDto, CatDto>>) session.getAttribute("pairs");
        }

        Map.Entry<CatDto, CatDto> pair = pairs.pop();
        CatDto cat1 = pair.getKey();
        CatDto cat2 = pair.getValue();
        model.addAttribute("cat1", cat1);
        model.addAttribute("cat2", cat2);

        return "vote_page";
    }

    @PostMapping("/vote/{cat-id}")
    public String vote(@AuthenticationPrincipal UserDetailsImpl userDetails,
                       @PathVariable("cat-id") Long id,
                       Model model,
                       HttpServletRequest request) {

        catsService.vote(id, userDetails.getUser());
        HttpSession session = request.getSession();
        Stack<Map.Entry<CatDto, CatDto>> pairs =
                (Stack<Map.Entry<CatDto, CatDto>>) session.getAttribute("pairs");
        if (pairs.isEmpty()) {
            return "redirect:/results";
        }

        return "redirect:/vote";
    }

    @GetMapping("/results")
    public String getResultsPage(Model model) {

        List<CatDto> list = catsService.getTopTen();
        model.addAttribute("cats", list);

        return "vote_results_page";
    }
}