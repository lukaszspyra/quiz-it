package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
class QuestionJson {

    private String content;

    private int score;

    private List<AnswerJson> answers;

}
