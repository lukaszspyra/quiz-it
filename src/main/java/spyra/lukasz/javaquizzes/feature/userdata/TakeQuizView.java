package spyra.lukasz.javaquizzes.feature.userdata;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for quiz attempt details presentation
 */
@Getter
@Setter
class TakeQuizView {

    private UUID id;

    private int score;

    private String duration;

    private String quizTitle;

    private int quizMaxScore;

    private String difficulty;

    private String attemptTime;
}
