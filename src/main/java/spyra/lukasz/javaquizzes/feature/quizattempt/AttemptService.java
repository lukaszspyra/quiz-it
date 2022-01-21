package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.feature.quizselector.QuizRepository;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.Collections;
import java.util.List;

@Service
class AttemptService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AttemptMapper attemptMapper;

    @Autowired
    private QuestionMapper questionMapper;

    AttemptView getQuizById(long id) {
        return attemptMapper.toView(quizRepository.getById(id));
    }

    List<QuestionView> getQuizQuestionsRandomOrder(long id) {
        List<Question> questions = quizRepository.getById(id).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }

}
