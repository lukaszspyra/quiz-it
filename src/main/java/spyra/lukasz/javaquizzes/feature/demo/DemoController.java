package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Performs demo quiz attempts
 */
@Controller
@RequiredArgsConstructor
class DemoController {

    private final DemoService service;

    private final DemoStarter starter;

    private final DemoProgresser progresser;

    private final DemoFinisher finisher;

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
        final List<DemoView> demos = service.findDemosByTitle(title);
        if (demos.isEmpty()) {
            return "demo";
        }
        model.addAttribute("demoView", demos.get(0));
        return "demo-start";
    }

    @GetMapping("/demo/{demo_id}/start")
    String startDemo(@PathVariable(name = "demo_id") long demoId,
                     HttpSession session) {
        final TakeDemoDTO takeDemoDTO = starter.takeQuiz(demoId);
        session.setAttribute("demo", takeDemoDTO);
        return "redirect:/demo/{demo_id}/attempt";
    }

    @GetMapping("/demo/{demo_id}/attempt")
    String nextDemoQuestion(@PathVariable(name = "demo_id") long demoId,
                            Model model,
                            HttpSession session) {
        List<QuestionDTO> questionsNotAnswered = ((TakeDemoDTO) session.getAttribute("demo")).getQuestionDTOs();
        if (questionsNotAnswered.isEmpty()) {
            return "redirect:/demo/{demo_id}/finish/";
        }
        model.addAttribute("demo_id", demoId);
        session.setAttribute("question", questionsNotAnswered.get(0));
        return "demo-attempt";
    }

    @PostMapping("/demo/{demo_id}/answer")
    String submitDemoAnswers(@RequestParam(value = "given_answers", required = false) Long[] answerIds,
                             HttpSession session) {
        List<Long> markedIds = answerIds == null ? Collections.emptyList() : Arrays.asList(answerIds);
        final QuestionDTO question = (QuestionDTO) session.getAttribute("question");
        final TakeDemoDTO demo = (TakeDemoDTO) session.getAttribute("demo");
        demo.removeQuestion(question);
        final TakeDemoDTO updatedDemo = progresser.progressQuiz(question, markedIds, demo);
        session.setAttribute("demo", updatedDemo);
        return "redirect:/demo/{demo_id}/attempt";
    }

    @GetMapping("/demo/{demo_id}/finish")
    String finishDemo(Model model,
                      HttpSession session) {
        model.addAttribute("result", finisher.finishQuizAttempt(session));
        return "demo-result";
    }
}
