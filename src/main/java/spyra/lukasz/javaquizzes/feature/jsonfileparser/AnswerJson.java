package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class AnswerJson {

    private String content;

    private boolean correct;
}