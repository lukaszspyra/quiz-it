package spyra.lukasz.javaquizzes.feature.quizresults.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * Deletes quiz attempt results
 */
@Controller
@RequiredArgsConstructor
class ResultDeleteController {

    private final ResultRemoveService service;

    /**
     * Deletes quiz attempt result
     *
     * @param attemptId of entity to be deleted
     * @return redirects to profile view controller
     */
    @DeleteMapping("/attempt/{attempt_id}/result/")
    String deleteResult(@PathVariable(value = "attempt_id") UUID attemptId) {
        service.deleteAttemptResult(attemptId);
        return "redirect:/profile";
    }
}
