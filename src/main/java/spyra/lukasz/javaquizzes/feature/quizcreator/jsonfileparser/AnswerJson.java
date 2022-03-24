package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class AnswerJson {

    private String content;

    private boolean correct;
}
