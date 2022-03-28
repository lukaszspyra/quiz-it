package spyra.lukasz.javaquizzes.feature.quizcreator.apiparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * Handles data required for generating random {@link Quiz} from API
 */
@Controller
class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/quizzes/random")
    String chooseRandomQuiz() {
        return "random-quiz";
    }

    /**
     * Passes required parameters for creating random quiz. Result database id is returned to view controller
     *
     * @param tag        describes category for the generated quiz
     * @param questionNumber for the generated quiz
     * @return redirects to AttemptController with quiz id from database
     * @throws IOException          when {@link java.util.Properties} file is not found
     * @throws InterruptedException when there is API connection issue with sending request
     */
    @PostMapping("/quizzes/random")
    String generateRandomQuiz(@PathParam(value = "tag") String tag,
                              @PathParam(value = "no_of_questions") String questionNumber) throws IOException, InterruptedException {
        final Quiz createdQuiz = apiService.getRandomQuizFromApi(tag, questionNumber);
        return "redirect:/quiz/" + createdQuiz.getId();
    }
}
