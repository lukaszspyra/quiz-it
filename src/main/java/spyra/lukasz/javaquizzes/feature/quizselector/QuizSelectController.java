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
    public String allOpenQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findNotRestricted());
        return "quizzes";
    }

    @GetMapping("/quiz/{id}")
    public String startSingleQuiz(Model model, @PathVariable long id) {
        model.addAttribute("quiz", quizService.getByIdNotRestricted(id));
        return "quiz";
    }

    @GetMapping("/admin/java-quizzes")
    public String adminJavaQuizzes(Model model) {

        return "#";
    }

}
