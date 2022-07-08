package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * DTO for question presentation
 */
@Getter
@Setter
class QuestionDetailsView {

    private UUID questionId;

    private int questMaxScore;

    private int questAwardedScore;

    private String content;

    private String difficulty;

    private List<AnswerDetailsView> possibleAnswers;

    private List<AnswerDetailsView> markedAnswers;
}
