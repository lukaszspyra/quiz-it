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

    /**
     * Retrieves quiz questions and returns them shuffled and mapped
     * @param quizId given quiz id
     * @return questions
     */
    List<QuestionView> getQuizQuestionsRandomOrder(long quizId) {
        List<Question> questions = quizProviderRepository.findQuizById(quizId).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }
}
