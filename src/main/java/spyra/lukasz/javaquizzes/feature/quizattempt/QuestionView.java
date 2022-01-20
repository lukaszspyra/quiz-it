package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionView {

    private long id;

    private int score;

    private String content;

    private List<AnswerView> answers;

}
