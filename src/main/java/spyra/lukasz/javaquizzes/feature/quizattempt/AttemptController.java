package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @GetMapping("/quiz/attempt/{quiz_id}")
    String startSingleQuiz(Model model, @PathVariable(name = "quiz_id") long quizId, HttpSession session) {
        List<QuestionView> questions = getQuestionsFromSession(quizId, session);
        if (questions.isEmpty()) {
            return "result";
        }
        model.addAttribute("quiz_id", quizId);
        model.addAttribute("question", questions.get(0));
        session.setAttribute("questions", questions);
        return "attempt";
    }

    @SuppressWarnings("unchecked cast")
    private List<QuestionView> getQuestionsFromSession(long id, HttpSession session) {
        Object sessionQuestions = session.getAttribute("questions");
        if (sessionQuestions == null) {
            return attemptService.getQuizQuestionsRandomOrder(id);
        }
        return (List<QuestionView>) sessionQuestions;
    }

    @PostMapping("/quiz/answer/{quiz_id}")
    ModelAndView givenAnswers(@RequestParam(value = "given_answers", required = false) String[] answers, @PathVariable(value = "quiz_id") long quizId, Model model){



        return new ModelAndView("redirect:/quiz/attempt/"+quizId );
    }

}
