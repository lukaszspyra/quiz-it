package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @GetMapping("/quiz/start/{id}")
    public String startSingleQuiz(Model model, @PathVariable long id, HttpSession session) {
        List<QuestionView> questions = getQuestionsFromSession(id, session);
        if (questions.isEmpty()) {
            return "result";
        }
        model.addAttribute("quiz_id", id);
        model.addAttribute("question", questions.remove(0));
        session.setAttribute("questions", questions);
        return "attempt";
    }

    @SuppressWarnings("unchecked cast")
    private List<QuestionView> getQuestionsFromSession(long id, HttpSession session) {
        Object sessionQuestions = session.getAttribute("questions");
        if (sessionQuestions == null) {
            return attemptService.getQuizById(id).getQuestions();
        }
        return (List<QuestionView>) sessionQuestions;
    }

}
