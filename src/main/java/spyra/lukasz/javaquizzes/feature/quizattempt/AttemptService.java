package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.feature.quizselector.QuizRepository;
import spyra.lukasz.javaquizzes.shared.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
class AttemptService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TakeQuizAnswerRepository takeQuizAnswerRepository;

    List<QuestionView> getQuizQuestionsRandomOrder(long id) {
        List<Question> questions = quizRepository.getById(id).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }

    TakeQuiz takeQuiz(long quizId, String userEmail) {
        Quiz presentQuiz = quizRepository.getById(quizId);
        User activeUser = takeQuizRepository.findUserByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuiz(presentQuiz);
        takeQuiz.setStart(LocalDateTime.now());
        takeQuiz.setUser(activeUser);
        return takeQuizRepository.save(takeQuiz);
    }

    void saveGivenAnswers(List<Long> answerIds, long takeQuizId, long questId) {
        List<Answer> answered = answerRepository.findAllById(answerIds);
        TakeQuiz takenQuiz = takeQuizRepository.getById(takeQuizId);
        Question question = questionRepository.getById(questId);
        for (var answer : answered) {
            TakeQuizAnswer answerForSaving = new TakeQuizAnswer();
            answerForSaving.setAnswer(answer);
            answerForSaving.setTakeQuiz(takenQuiz);
            answerForSaving.setQuestion(question);
            takeQuizAnswerRepository.save(answerForSaving);
        }
    }
}
