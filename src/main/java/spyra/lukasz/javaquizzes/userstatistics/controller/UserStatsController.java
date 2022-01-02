package spyra.lukasz.javaquizzes.userstatistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spyra.lukasz.javaquizzes.userstatistics.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
class UserStatsController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        model.addAttribute("quizzes", userService.getUserStatistics("test1@test.pl"));
        return "home";
    }

}
