package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * Presents quiz attempt results
 */
@Controller
@RequiredArgsConstructor
class ResultDetailsController {

    private final ResultDetailsService service;

    @GetMapping("/attempt/{attempt_id}/result/")
    String showResultDetails(@PathVariable(value = "attempt_id") UUID attemptId,
                             Model model) {
        model.addAttribute("result", service.quizAttemptDetails(attemptId));
        return "result-details";
    }
}
