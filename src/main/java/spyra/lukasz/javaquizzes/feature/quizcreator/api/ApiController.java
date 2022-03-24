package spyra.lukasz.javaquizzes.feature.quizcreator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.websocket.server.PathParam;
import java.io.IOException;

@Controller
class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/quizzes/random")
    String chooseRandomQuiz() {
        return "random-quiz";
    }

    @PostMapping("/quizzes/random")
    String generateRandomQuiz(@PathParam(value = "tag") String tag,
                              @PathParam(value = "difficulty") String difficulty) throws IOException, InterruptedException {
        final Quiz createdQuiz = apiService.getRandomQuizFromApi(tag, difficulty);
        return "redirect:/quiz/" + createdQuiz.getId();
    }
}
