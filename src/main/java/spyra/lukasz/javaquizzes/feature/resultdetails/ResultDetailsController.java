package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
class ResultDetailsController {

    @Autowired
    private ResultDetailsService service;

    @GetMapping("/attempt/{attempt_id}/result/")
    String showResultDetails(@PathVariable(value = "attempt_id") long attemptId,
                             Model model) {
        model.addAttribute(service.quizAttemptDetails(attemptId));
        return "result-details";
    }

}
