package spyra.lukasz.javaquizzes.userstatistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spyra.lukasz.javaquizzes.userlogin.MyUserPrincipal;
import spyra.lukasz.javaquizzes.userstatistics.service.StatsService;

import java.security.Principal;

@Controller
class UserStatsController {

    @Autowired
    private StatsService userService;

    @GetMapping("/")
    public String home(Principal principal, Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserStatistics(principal.getName()));
        //TODO: for research purposes: check user data saved in session after modification in database
        MyUserPrincipal test = (MyUserPrincipal) authentication.getPrincipal();
        return "home";
    }

}
