package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.*;

import java.time.LocalDateTime;
import java.util.List;

@Component
class Progresser {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    @Autowired
    private TakeQuizAnswerRepository takeQuizAnswerRepository;

    @Autowired
    private ScoreCounter scoreCounter;

    void progressQuiz(final List<Long> answerIds, final long takeQuizId, final long questId) {
        List<Answer> answered = answerRepository.findAllById(answerIds);
        TakeQuiz currentQuiz = takeQuizRepository.getById(takeQuizId);
        Question question = questionRepository.getById(questId);
        TakeQuiz updatedQuiz = updateQuiz(currentQuiz, scoreCounter.count(answered), LocalDateTime.now());
        updateGivenAnswers(answered, question, updatedQuiz);
    }

    void updateGivenAnswers(List<Answer> answered, Question question, TakeQuiz updatedQuiz) {
        for (var answer : answered) {
            TakeQuizAnswer answerForSaving = new TakeQuizAnswer();
            answerForSaving.setAnswer(answer);
            answerForSaving.setTakeQuiz(updatedQuiz);
            answerForSaving.setQuestion(question);
            takeQuizAnswerRepository.save(answerForSaving);
        }
    }

    TakeQuiz updateQuiz(final TakeQuiz currentQuiz, final int questionScore, final LocalDateTime questFinishTime) {
        updateScore(currentQuiz, questionScore);
        updateFinishTime(currentQuiz, questFinishTime);
        return takeQuizRepository.save(currentQuiz);
    }

    private void updateFinishTime(TakeQuiz currentQuiz, LocalDateTime questFinishTime) {
        currentQuiz.setFinish(questFinishTime);
    }

    private void updateScore(TakeQuiz currentQuiz, int questionScore) {
        int updatedScore = currentQuiz.getScore() + questionScore;
        currentQuiz.setScore(updatedScore);
    }
}
