package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class AttemptService {

    private final QuizProviderRepository quizProviderRepository;

    private final QuizQuestionsProviderRepository questionsRepository;

    private final QuestionMapper questionMapper;

    /**
     * Retrieves quiz questions and returns them shuffled and mapped
     *
     * Second query is performed to bind Answers to Questions by Hibernate cache, requiring @Transactional annotation
     * @param quizId given quiz id
     * @return questions
     */
   @Transactional
   public List<QuestionView> findQuizQuestionsRandomOrder(UUID quizId) {
        Quiz quiz = quizProviderRepository.findQuizById(quizId);
        List<Question> questionsByQuiz = questionsRepository.findQuestionsByQuiz(quiz);
        List<Question> questions = quiz.getQuestions();
        Collections.shuffle(questions);
        return questionMapper.toView(questions);
    }
}
