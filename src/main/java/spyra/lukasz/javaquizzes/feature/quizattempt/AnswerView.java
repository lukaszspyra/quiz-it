package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.shared.Answer;

/**
 * DTO for {@link Answer} entity
 */
@Getter
@Setter
@AllArgsConstructor
class AnswerView {

    private long id;

    private boolean correct;

    private String content;

}
