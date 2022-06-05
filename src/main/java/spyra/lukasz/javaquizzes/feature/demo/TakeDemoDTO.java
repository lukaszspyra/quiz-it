package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
final class TakeDemoDTO {

    private long id;

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

}
