package spyra.lukasz.javaquizzes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class IndexController {

    @GetMapping({"/", "/home"})
    public String commonIndex() {
        return "index";
    }

}
