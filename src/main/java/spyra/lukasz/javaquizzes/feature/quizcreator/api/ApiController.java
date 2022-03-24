package spyra.lukasz.javaquizzes.feature.quizcreator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/quizzes/random/pick")
    String chooseRandomQuiz() {
        return "random-quiz";
    }


}
