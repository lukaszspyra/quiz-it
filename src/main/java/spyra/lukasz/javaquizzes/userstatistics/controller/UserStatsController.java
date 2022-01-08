package spyra.lukasz.javaquizzes.userstatistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spyra.lukasz.javaquizzes.userstatistics.service.StatsService;

import java.security.Principal;

@Controller
class UserStatsController {

    @Autowired
    private StatsService userService;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserStatistics(principal.getName()));
        return "home";
    }

}
