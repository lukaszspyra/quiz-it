package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
class AttemptController {

    @Autowired
    private AttemptService attemptService;


    @GetMapping("/quiz/start/{quiz_id}")
    ModelAndView startSingleQuiz(Principal principal, Model model, @PathVariable(name = "quiz_id") long quizId, HttpSession session) {
        TakeQuiz takeQuiz = attemptService.takeQuiz(quizId, principal.getName());
        List<QuestionView> questions = attemptService.getQuizQuestionsRandomOrder(quizId);
        session.setAttribute("questions", questions);
        session.setAttribute("take_quiz_id", takeQuiz.getId());
        return new ModelAndView("redirect:/quiz/attempt/"+quizId);
    }


    @SuppressWarnings("unchecked cast")
    @GetMapping("/quiz/attempt/{quiz_id}")
    String progressQuiz(Model model, @PathVariable(name = "quiz_id") long quizId, HttpSession session) {
        List<QuestionView> questions = (List<QuestionView>) session.getAttribute("questions");
        if (questions.isEmpty()) {
            session.removeAttribute("questions");
            return "result";
        }
        model.addAttribute("quiz_id", quizId);
        model.addAttribute("question", questions.remove(0));
        return "attempt";
    }

    @PostMapping("/quiz/answer/{quiz_id}")
    ModelAndView givenAnswers(@RequestParam(value = "given_answers", required = false) Long[] answers, @PathVariable(value = "quiz_id") long quizId, Model model){

        return new ModelAndView("redirect:/quiz/attempt/"+quizId );
    }

}
