package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Answer;

import java.util.List;

@Component
class ScoreCounter {

    /**
     * Calculates score based on correct and incorrect answers.
     * Former are scored +1, whilst latter -1.
     *
     * @param answered list of all answers given by user
     * @return calculated score for given answers
     */
    int count(List<Answer> answered){
        int allAnswers = answered.size();
        int correctAnswers = Math.toIntExact(answered.stream()
                .filter(Answer::isCorrect)
                .count());
        return scoreFormula(allAnswers, correctAnswers);
    }

    private int scoreFormula(int allAnswers, int correctAnswers) {
        int falseAnswers = allAnswers - correctAnswers;
        return correctAnswers - (falseAnswers);
    }

}
