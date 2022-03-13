package spyra.lukasz.javaquizzes.feature.quizselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
class QuizSelectController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String openQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findNotRestricted());
        model.addAttribute("msg", "IT Technical quizzes");
        return "quizzes";
    }

    @GetMapping("/admin/java-quizzes")
    public String restrictedQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findRestricted());
        model.addAttribute("msg", "Java Quizzes");
        return "quizzes";
    }

    @GetMapping("/quiz/{id}")
    public String confirmQuizStart(Model model, @PathVariable long id) {
        model.addAttribute("quiz", quizService.getById(id));
        return "quiz";
    }

}
