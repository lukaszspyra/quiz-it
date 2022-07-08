package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.Question;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class AttemptService {

    private final QuizProviderRepository quizProviderRepository;

    private final QuestionMapper questionMapper;

    /**
     * Retrieves quiz questions and returns them shuffled and mapped
     *
     * @param quizId given quiz id
     * @return questions
     */
    List<QuestionView> getQuizQuestionsRandomOrder(UUID quizId) {
        List<Question> questions = quizProviderRepository.findQuizById(quizId).getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }
}
