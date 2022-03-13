package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.Collections;
import java.util.List;

@Service
class AttemptService {

    @Autowired
    private QuizProviderRepository quizProviderRepository;

    @Autowired
    private QuestionMapper questionMapper;

    List<QuestionView> getQuizQuestionsRandomOrder(long id) {
        List<Question> questions = quizProviderRepository.findQuizById(id).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }
}
