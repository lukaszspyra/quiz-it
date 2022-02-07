package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
class AttemptController {

    @Autowired
    private Starter starter;

    @Autowired
    private Progresser progresser;

    @Autowired
    private Finisher finisher;

    @Autowired
    private AttemptService attemptService;


    @GetMapping("/quiz/{quiz_id}/start/")
    String startSingleQuiz(@PathVariable(name = "quiz_id") long quizId,
                           Principal principal,
                           HttpSession session) {
        TakeQuiz takeQuiz = starter.takeQuiz(quizId, principal.getName());
        List<QuestionView> questions = attemptService.getQuizQuestionsRandomOrder(quizId);
        long attemptTimer = starter.calcTimeForQuizInEpochSeconds(takeQuiz, 45);
        session.setAttribute("questions", questions);
        session.setAttribute("attempt_timer", attemptTimer);
        return "redirect:/quiz/{quiz_id}/attempt/" + takeQuiz.getId();
    }

    @SuppressWarnings("unchecked cast")
    @PostMapping("/quiz/{quiz_id}/answer/{attempt_id}")
    String givenAnswers(@RequestParam(value = "given_answers", required = false) Long[] answerIds,
                        @RequestParam(value = "question_id") long questionId,
                        @PathVariable(value = "attempt_id") long takeQuizId,
                        @PathVariable(value = "quiz_id") long quizId,
                        HttpSession session) {
        List<Long> answers = answerIds == null ? Collections.emptyList() : Arrays.asList(answerIds);
        progresser.progressQuiz(answers, takeQuizId, questionId);
        List<QuestionView> questions = (List<QuestionView>) session.getAttribute("questions");
        List<QuestionView> questionsNotAnswered = new ArrayList<>(questions.subList(1, questions.size()));
        session.setAttribute("questions", questionsNotAnswered);
        return "redirect:/quiz/{quiz_id}/attempt/" + takeQuizId;
    }

    @SuppressWarnings("unchecked cast")
    @GetMapping("/quiz/{quiz_id}/attempt/{attempt_id}")
    String progressQuiz(@PathVariable(name = "quiz_id") long quizId,
                        @PathVariable(name = "attempt_id") long attemptId,
                        Model model,
                        HttpSession session) {
        List<QuestionView> questionsNotAnswered = (List<QuestionView>) session.getAttribute("questions");
        if (questionsNotAnswered.isEmpty()) {
            TakeQuiz takeQuiz = finisher.finishQuizAttempt(session, attemptId);
            Duration duration = finisher.calcAttemptTime(takeQuiz);
            model.addAttribute("minutes", duration.toMinutes());
            model.addAttribute("seconds", duration.toSecondsPart());
            model.addAttribute("result", takeQuiz);
            return "result";
        }
        model.addAttribute("attempt_timer", session.getAttribute("attempt_timer"));
        model.addAttribute("attempt_id", attemptId);
        model.addAttribute("quiz_id", quizId);
        model.addAttribute("question", questionsNotAnswered.get(0));
        return "attempt";
    }
}
