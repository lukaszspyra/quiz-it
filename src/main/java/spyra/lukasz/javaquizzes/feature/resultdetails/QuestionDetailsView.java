package spyra.lukasz.javaquizzes.feature.resultdetails;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class QuestionDetailsView {

    private long questionId;

    private int questMaxScore;

    private int questAwardedScore;

    private String content;

    private List<AnswerDetailsView> possibleAnswers;

    private List<AnswerDetailsView> markedAnswers;
}
