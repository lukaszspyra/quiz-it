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
