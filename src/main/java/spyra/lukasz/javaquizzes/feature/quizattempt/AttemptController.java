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


    @GetMapping("/quiz/start/{quiz_id}")
    String startSingleQuiz(@PathVariable(name = "quiz_id") long quizId,
                           Principal principal,
                           HttpSession session,
                           RedirectAttributes redirectAttr) {
        TakeQuiz takeQuiz = starter.takeQuiz(quizId, principal.getName());
        List<QuestionView> questions = attemptService.getQuizQuestionsRandomOrder(quizId);
        session.setAttribute("questions", questions);
        redirectAttr.addFlashAttribute("take_quiz_id", takeQuiz.getId());
        return "redirect:/quiz/attempt/{quiz_id}";
    }


    @SuppressWarnings("unchecked cast")
    @GetMapping("/quiz/attempt/{quiz_id}")
    String progressQuiz(@PathVariable(name = "quiz_id") long quizId,
                        Model model,
                        HttpSession session) {
        List<QuestionView> questionsNotAnswered = (List<QuestionView>) session.getAttribute("questions");
        if (questionsNotAnswered.isEmpty()) {
            TakeQuiz takeQuiz = finisher.finishQuizAttempt(session, (Long) model.getAttribute("take_quiz_id"));
            Duration duration = finisher.calcAttemptTime(takeQuiz);
            model.addAttribute("minutes", duration.toMinutes());
            model.addAttribute("seconds", duration.toSecondsPart());
            model.addAttribute("result", takeQuiz);
            return "result";
        }
        model.addAttribute("quiz_id", quizId);
        model.addAttribute("question", questionsNotAnswered.get(0));
        return "attempt";
    }

    @SuppressWarnings("unchecked cast")
    @PostMapping("/quiz/answer/{quiz_id}")
    String givenAnswers(@RequestParam(value = "given_answers", required = false) Long[] answerIds,
                        @RequestParam(value = "question_id") long questionId,
                        @RequestParam(value = "take_quiz_id") long takeQuizId,
                        RedirectAttributes redirectAttr,
                        HttpSession session) {
        List<Long> answers = answerIds == null ? Collections.emptyList() : Arrays.asList(answerIds);
        progresser.progressQuiz(answers, takeQuizId, questionId);
        List<QuestionView> questions = (List<QuestionView>) session.getAttribute("questions");
        List<QuestionView> questionsNotAnswered = new ArrayList<>(questions.subList(1, questions.size()));
        session.setAttribute("questions", questionsNotAnswered);
        redirectAttr.addFlashAttribute("take_quiz_id", takeQuizId);
        return "redirect:/quiz/attempt/{quiz_id}";
    }
}
