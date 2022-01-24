package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.feature.quizselector.QuizRepository;
import spyra.lukasz.javaquizzes.shared.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
class AttemptService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AttemptMapper attemptMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TakeQuizAnswerRepository takeQuizAnswerRepository;

    AttemptView getQuizById(long id) {
        return attemptMapper.toView(quizRepository.getById(id));
    }

    List<QuestionView> getQuizQuestionsRandomOrder(long id) {
        List<Question> questions = quizRepository.getById(id).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }


    public TakeQuiz takeQuiz(long quizId, String userEmail) {
        Quiz presentQuiz = quizRepository.getById(quizId);
        User activeUser = takeQuizRepository.findUserByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuiz(presentQuiz);
        takeQuiz.setStart(LocalDateTime.now());
        takeQuiz.setUser(activeUser);
        return takeQuizRepository.save(takeQuiz);
    }
}
