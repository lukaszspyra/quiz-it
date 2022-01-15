package spyra.lukasz.javaquizzes.feature.jsonparser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class AnswerJson {

    private String title;

    private boolean correct;

}
