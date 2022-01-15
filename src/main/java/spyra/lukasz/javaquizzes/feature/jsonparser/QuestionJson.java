package spyra.lukasz.javaquizzes.feature.jsonparser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class QuestionJson {

    private String title;

    private int score;

    private List<AnswerJson> answers;

}
