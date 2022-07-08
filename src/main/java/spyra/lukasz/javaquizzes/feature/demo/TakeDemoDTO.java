package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.TakeQuiz} entity
 */
@Getter
@Setter
final class TakeDemoDTO {

    private UUID id;

    private int score;

    private LocalDateTime start;

    private LocalDateTime finish;

    private List<QuestionDTO> questionDTOs;

    /**
     * Calculates for view layer, total time used for demo
     *
     * @return difference between start and finish demo times
     */
    public Duration calcAttemptTime() {
        return Duration.between(start, finish);
    }

   boolean removeQuestion(QuestionDTO questionDTO){
        return questionDTOs.remove(questionDTO);
   }

}
