package spyra.lukasz.javaquizzes.quizselector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spyra.lukasz.javaquizzes.quizselector.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class QuizSelectController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String home(Model model, HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        model.addAttribute("quizzes", quizService.findAll());
        return "quizzes";
    }

}