package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for attempt details presented for the user
 */
@Getter
@Setter
class AttemptDetailsView {

    private long attemptId;

    private int score;

    private String start;

    private String finish;

    private String duration;

    private String quizTitle;

    private int quizMaxScore;

    private List<QuestionDetailsView> questionsSolved;
}
