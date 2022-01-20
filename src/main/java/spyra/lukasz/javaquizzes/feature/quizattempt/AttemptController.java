package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @GetMapping("/quiz/start/{id}")
    public String startSingleQuiz(Model model, @PathVariable long id) {
        model.addAttribute("quiz", attemptService.getQuizById((id)));
        return "attempt";
    }

}
