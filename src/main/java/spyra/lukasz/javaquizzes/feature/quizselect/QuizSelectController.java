package spyra.lukasz.javaquizzes.feature.quizselect;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Returns view with quizzes chosen by the user
 * <p>
 * Results are presented and user can choose given quiz to run
 */
@Controller
@RequiredArgsConstructor
class QuizSelectController {
    private final QuizService quizService;

    @GetMapping("/quizzes")
    String notRestrictedQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findPredefinedNotRestricted());
        return "quizzes";
    }

    @GetMapping("/admin/java-quizzes")
    String restrictedQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findRestricted());
        return "quizzes-java";
    }

    @GetMapping("/quiz/{id}")
    String confirmQuizStart(Model model, @PathVariable long id) {
        model.addAttribute("quiz", quizService.getById(id));
        return "quiz";
    }

}
