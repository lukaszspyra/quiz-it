package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
class QuestionJson {

    private long apiAdi;

    private int score;

    private String content;

    private List<AnswerJson> answers;

}
