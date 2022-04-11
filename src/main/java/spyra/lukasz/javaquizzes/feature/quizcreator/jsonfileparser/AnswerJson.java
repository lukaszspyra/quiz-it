package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.Answer} entity read from available JSON format
 */
@AllArgsConstructor
@Getter
class AnswerJson {

    private String content;

    private boolean correct;
}
