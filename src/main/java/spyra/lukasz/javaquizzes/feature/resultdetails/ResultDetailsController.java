package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ResultDetailsController {


    @GetMapping
    String showResultDetails() {
        return "result-details";
    }

}
