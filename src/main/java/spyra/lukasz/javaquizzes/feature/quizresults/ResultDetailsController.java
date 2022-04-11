package spyra.lukasz.javaquizzes.feature.quizresults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Presents quiz attempt results
 */
@Controller
class ResultDetailsController {

    private final ResultDetailsService service;

    @Autowired
    ResultDetailsController(ResultDetailsService service) {
        this.service = service;
    }

    @GetMapping("/attempt/{attempt_id}/result/")
    String showResultDetails(@PathVariable(value = "attempt_id") long attemptId,
                             Model model) {
        model.addAttribute("result", service.quizAttemptDetails(attemptId));
        return "result-details";
    }
}
