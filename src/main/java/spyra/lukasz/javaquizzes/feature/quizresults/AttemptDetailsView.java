package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * DTO for attempt details presented for the user
 */
@Getter
@Setter
class AttemptDetailsView {

    private UUID attemptId;

    private int score;

    private String start;

    private String finish;

    private String duration;

    private String quizTitle;

    private int quizMaxScore;

    private String difficulty;

    private List<QuestionDetailsView> questionsSolved;
}
