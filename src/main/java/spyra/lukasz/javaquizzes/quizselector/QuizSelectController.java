package spyra.lukasz.javaquizzes.quizselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizSelectController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String allQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "quizzes";
    }


    @GetMapping("/quiz")
    public String startSingleQuiz(Model model) {
        model.addAttribute("quiz", quizService.getById(1));
        return "quiz";
    }

}
