package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.Collections;
import java.util.List;

@Service
class AttemptService {

    private final QuizProviderRepository quizProviderRepository;

    private final QuestionMapper questionMapper;

    public AttemptService(QuizProviderRepository quizProviderRepository, QuestionMapper questionMapper) {
        this.quizProviderRepository = quizProviderRepository;
        this.questionMapper = questionMapper;
    }

    /**
     * Retrieves quiz questions and returns them shuffled and mapped
     *
     * @param quizId given quiz id
     * @return questions
     */
    List<QuestionView> getQuizQuestionsRandomOrder(long quizId) {
        List<Question> questions = quizProviderRepository.findQuizById(quizId).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }
}
