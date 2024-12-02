package spyra.lukasz.javaquizzes.feature.quizcreate.apiparse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.io.IOException;

/**
 * Handles data required for generating random {@link Quiz} from API
 */
@Controller
@RequiredArgsConstructor
class ApiController {

    private final ApiService apiService;

    @GetMapping("/quizzes/random")
    String chooseRandomQuiz() {
        return "quiz-random-start";
    }

    /**
     * Passes required parameters for creating random quiz. Result database id is returned to view controller
     *
     * @param tag        describes category for the generated quiz
     * @param questionsNumber for the generated quiz
     * @return redirects to AttemptController with quiz id from database
     * @throws IOException          when {@link java.util.Properties} file is not found
     * @throws InterruptedException when there is API connection issue with sending request
     */
    @PostMapping("/quizzes/random")
    String generateRandomQuiz(@RequestParam(value = "tag") String tag,
                              @RequestParam(value = "questions") String questionsNumber) throws IOException, InterruptedException {
        final Quiz createdQuiz = apiService.getRandomQuizFromApi(tag, questionsNumber);
        return "redirect:/quiz/" + createdQuiz.getId() + "/start";
    }
}
