package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls progress during Demo quiz attempt.
 *
 * As it serves for demo purpose, results are not saved in database, only {@link TakeQuiz} instance is updated.
 */
@Component
@RequiredArgsConstructor
class DemoProgresser {

    private final ScoreCounter scoreCounter;

    private final TakeDemoMapper mapper;

    /**
     * Make progress of demo quiz attempt by updating {@link TakeQuiz} with given list of {@link Answer}
     * <p>
     * As it is demo feature, previous answers are not saved, but score is updated.
     *
     * @param questionDTO during attempt
     * @param markedIds   selected answers Ids
     * @param takeDemoDTO current {@link TakeQuiz} attempt instance
     * @return demo DTO with updated score
     */
    TakeDemoDTO progressQuiz(final QuestionDTO questionDTO, final List<Long> markedIds, final TakeDemoDTO takeDemoDTO) {
        final Question question = mapper.toQuestion(questionDTO);
        final TakeQuiz currentDemo = mapper.fromDTO(takeDemoDTO);

        final List<Answer> answered = question.selectAnswers(markedIds);
        final TakeQuiz updatedDemo = currentDemo.progressQuizAttempt(scoreCounter.count(answered), LocalDateTime.now());
        return mapper.toDTO(updatedDemo);
    }

}
