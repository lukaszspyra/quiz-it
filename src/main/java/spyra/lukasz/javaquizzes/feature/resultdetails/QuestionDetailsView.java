package spyra.lukasz.javaquizzes.feature.resultdetails;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class QuestionDetailsView {

    private long id;

    private int score;

    private String content;

    private List<AnswerDetailsView> possibleAnswers;

    private List<AnswerDetailsView> markedAnswers;
}
