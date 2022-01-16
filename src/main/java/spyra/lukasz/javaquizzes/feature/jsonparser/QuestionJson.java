package spyra.lukasz.javaquizzes.feature.jsonparser;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class QuestionJson {

    private String title;

    private int score;

    private List<AnswerJson> answers;

}
