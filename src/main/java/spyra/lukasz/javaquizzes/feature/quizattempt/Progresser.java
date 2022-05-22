package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Progresses quiz with each question answered.
 */
@Component
@RequiredArgsConstructor
class Progresser {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    private final TakeQuizRepository takeQuizRepository;

    private final TakeQuizAnswerRepository takeQuizAnswerRepository;

    private final ScoreCounter scoreCounter;

    /**
     * Progresses quiz by assigning chosen answers to current question
     *
     * @param answerIds chosen by the user
     * @param takeQuizId present quiz attempt
     * @param questId present answered question
     */
    void progressQuiz(final List<Long> answerIds, final long takeQuizId, final long questId) {
        List<Answer> answered = answerRepository.findAllById(answerIds);
        TakeQuiz currentQuiz = takeQuizRepository.getById(takeQuizId);
        Question question = questionRepository.getById(questId);
        TakeQuiz updatedQuiz = updateQuiz(currentQuiz, scoreCounter.count(answered), LocalDateTime.now());
        updateGivenAnswers(answered, question, updatedQuiz);
    }

    /**
     * Creates new complete {@link TakeQuizAnswer} and saves in database
     * @param answered marked answers by the user
     * @param question current quiz question
     * @param updatedQuiz current quiz attempt
     */
    private void updateGivenAnswers(List<Answer> answered, Question question, TakeQuiz updatedQuiz) {
        for (var answer : answered) {
            takeQuizAnswerRepository.save(new TakeQuizAnswer(updatedQuiz, question, answer));
        }
    }

    private TakeQuiz updateQuiz(final TakeQuiz currentQuiz, final int questionScore, final LocalDateTime questFinishTime) {
        return takeQuizRepository.save(currentQuiz.progressQuizAttempt(questionScore, questFinishTime));
    }

}
