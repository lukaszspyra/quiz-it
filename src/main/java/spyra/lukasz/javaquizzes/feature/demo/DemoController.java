package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spyra.lukasz.javaquizzes.shared.Quiz;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
class DemoController {

    private final DemoService service;

    private final DemoStarter starter;

    @GetMapping("/demo")
    String demo() {
        return "demo";
    }

    @GetMapping("/demo/{title}")
    public String selectDemo(Model model,
                             @PathVariable String title) {
        if (title == null || title.isBlank()) {
            return "demo";
        }
        final List<Quiz> demos = service.findDemosByTitle(title);
        if (demos.isEmpty()) {
            return "demo";
        }
        model.addAttribute("demo", demos.get(0));
        return "# demo start view";
    }

    @GetMapping("/demo/{demo_id}/start")
    String startDemo(@PathVariable(name = "demo_id") long demoId,
                     Principal principal,
                     HttpSession session) {
        final TakeQuiz takeQuiz = starter.takeQuiz(demoId);
        session.setAttribute("questions", takeQuiz.getQuiz().getQuestions());
        session.setAttribute("demo", takeQuiz);
        return "redirect:/demo/{demo_id}/attempt";
    }

}
