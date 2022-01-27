package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Answer;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

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

    void saveGivenAnswers(List<Long> answerIds, long takeQuizId, long questId) {
        List<Answer> answered = answerRepository.findAllById(answerIds);
        int questionScore = scoreCounter.count(answered);
        TakeQuiz takenQuiz = updateScore(takeQuizId, questionScore);
        Question question = questionRepository.getById(questId);
        for (var answer : answered) {
            TakeQuizAnswer answerForSaving = new TakeQuizAnswer();
            answerForSaving.setAnswer(answer);
            answerForSaving.setTakeQuiz(takenQuiz);
            answerForSaving.setQuestion(question);
            takeQuizAnswerRepository.save(answerForSaving);
        }
    }

    private TakeQuiz updateScore(long takeQuizId, int questionScore) {
        TakeQuiz currentQuiz = takeQuizRepository.getById(takeQuizId);
        int updatedScore = currentQuiz.getScore() + questionScore;
        currentQuiz.setScore(updatedScore);
        return takeQuizRepository.save(currentQuiz);
    }

}
