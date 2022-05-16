package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Controls quiz attempt taken by the {@link spyra.lukasz.javaquizzes.shared.User}
 */
@Controller
class AttemptController {

    private final Starter starter;

    private final Progresser progresser;

    private final Finisher finisher;

    private final AttemptService attemptService;

    private final QuestionTimeReader propertyReader;

    AttemptController(Starter starter, Progresser progresser, Finisher finisher, AttemptService attemptService, QuestionTimeReader propertyReader) {
        this.starter = starter;
        this.progresser = progresser;
        this.finisher = finisher;
        this.attemptService = attemptService;
        this.propertyReader = propertyReader;
    }

    /**
     * Starts quiz attempt based on the PathVariable with quiz id
     *
     * @param quizId    of currently start run quiz
     * @param principal with name of currently logged in User
     * @param session   server side storage to hold timers (both total timer and one question timer)
     *                  and questions remaining in given quiz
     * @return redirect to {@link AttemptController#progressQuiz(long, long, Model, HttpSession)} with created {@link TakeQuiz#getId()}
     * @throws IOException if {@link java.util.Properties} with config data not found
     */
    @GetMapping("/quiz/{quiz_id}/start")
    String startSingleQuiz(@PathVariable(name = "quiz_id") long quizId,
                           Principal principal,
                           HttpSession session) throws IOException {
        TakeQuiz takeQuiz = starter.takeQuiz(quizId, principal.getName());
        List<QuestionView> questions = attemptService.getQuizQuestionsRandomOrder(quizId);
        int questionTimer = Integer.parseInt(propertyReader.readProperty("question_time", "settings.properties"));
        long attemptTimer = takeQuiz.calcTimeForQuizInEpochSeconds(questions.size() * questionTimer);
        session.setAttribute("question_timer", questionTimer);
        session.setAttribute("questions", questions);
        session.setAttribute("attempt_timer", attemptTimer);
        return "redirect:/quiz/{quiz_id}/attempt/" + takeQuiz.getId();
    }

    /**
     * Controls quiz attempt progress
     *
     * @param quizId    currently run quiz
     * @param attemptId current quiz attempt stored in database
     * @param model     to get data for view
     * @param session   holds questions not answered of given quiz
     * @return view for quiz attempt
     */
    @SuppressWarnings("unchecked cast")
    @GetMapping("/quiz/{quiz_id}/attempt/{attempt_id}")
    String progressQuiz(@PathVariable(name = "quiz_id") long quizId,
                        @PathVariable(name = "attempt_id") long attemptId,
                        Model model,
                        HttpSession session) {
        List<QuestionView> questionsNotAnswered = (List<QuestionView>) session.getAttribute("questions");
        if (questionsNotAnswered.isEmpty()) {
            return "redirect:/quiz/{quiz_id}/finish/" + attemptId;
        }
        model.addAttribute("attempt_id", attemptId);
        model.addAttribute("quiz_id", quizId);
        model.addAttribute("question", questionsNotAnswered.get(0));
        return "attempt";
    }

    /**
     * Process answers from POST form of attempt view
     * <p>
     * Answers are received in the form of id long[], quiz is progressed by {@link Progresser#progressQuiz(List, long, long)}
     * and redirected
     *
     * @param answerIds  received by POST form from the User
     * @param questionId currently answered question
     * @param attemptId  current quiz attempt id
     * @param quizId     current quiz run
     * @param session    storage for questions
     * @return redirects to {@link AttemptController#progressQuiz(long, long, Model, HttpSession)}
     */
    @SuppressWarnings("unchecked cast")
    @PostMapping("/quiz/{quiz_id}/answer/{attempt_id}")
    String giveAnswers(@RequestParam(value = "given_answers", required = false) Long[] answerIds,
                       @RequestParam(value = "question_id") long questionId,
                       @PathVariable(value = "attempt_id") long attemptId,
                       @PathVariable(value = "quiz_id") long quizId,
                       HttpSession session) {
        List<Long> answers = answerIds == null ? Collections.emptyList() : Arrays.asList(answerIds);
        progresser.progressQuiz(answers, attemptId, questionId);
        List<QuestionView> questions = (List<QuestionView>) session.getAttribute("questions");
        List<QuestionView> questionsNotAnswered = new LinkedList<>(questions.subList(1, questions.size()));
        session.setAttribute("questions", questionsNotAnswered);
        return "redirect:/quiz/{quiz_id}/attempt/" + attemptId;
    }

    /**
     * Process quiz end
     *
     * @param attemptId currently finished quiz attempt
     * @param model     with attempt results
     * @param session   cleaned from questions variable
     * @return result view
     */
    @GetMapping("/quiz/{quiz_id}/finish/{attempt_id}")
    String finishQuiz(@PathVariable(value = "attempt_id") long attemptId,
                      Model model,
                      HttpSession session) {
        FinalResultView takeQuiz = finisher.finishQuizAttempt(session, attemptId);
        model.addAttribute("result", takeQuiz);
        return "result";
    }

    /**
     * Called when total time of quiz passed
     *
     * @param answerIds  any marked answers
     * @param questionId current quiz question
     * @param attemptId  current attempt
     * @param quizId     base for attempt
     * @return redirects to {@link AttemptController#finishQuiz(long, Model, HttpSession)}
     */
    @PostMapping("/quiz/{quiz_id}/finish/{attempt_id}")
    String giveTimeOutAnswers(@RequestParam(value = "given_answers", required = false) Long[] answerIds,
                              @RequestParam(value = "question_id") long questionId,
                              @PathVariable(value = "attempt_id") long attemptId,
                              @PathVariable(value = "quiz_id") long quizId) {
        List<Long> answers = answerIds == null ? Collections.emptyList() : Arrays.asList(answerIds);
        progresser.progressQuiz(answers, attemptId, questionId);
        return "redirect:/quiz/{quiz_id}/finish/" + attemptId;
    }
}
