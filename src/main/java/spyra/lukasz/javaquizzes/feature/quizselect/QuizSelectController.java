package spyra.lukasz.javaquizzes.feature.quizselect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Returns view with quizzes chosen by the user
 *
 * Results are presented and user can choose given quiz to run
 */
@Controller
class QuizSelectController {

    private final QuizService quizService;

    @Autowired
    QuizSelectController(final QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes")
    String freeQuizzes(Model model) {
        return "free-quizzes";
    }

    @GetMapping("/quizzes/predefined")
    String pickPredefinedQuiz(Model model) {
        model.addAttribute("quizzes", quizService.findPredefinedNotRestricted());
        model.addAttribute("msg", "Predefined Quizzes");
        return "quizzes";
    }

    @GetMapping("/admin/java-quizzes")
    String restrictedQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findRestricted());
        model.addAttribute("msg", "Java Quizzes");
        return "quizzes";
    }

    @GetMapping("/quiz/{id}")
    String confirmQuizStart(Model model, @PathVariable long id) {
        model.addAttribute("quiz", quizService.getById(id));
        return "quiz";
    }

}
