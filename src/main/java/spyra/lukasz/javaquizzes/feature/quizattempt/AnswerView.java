package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.shared.Answer;

/**
 * DTO for {@link Answer} entity
 */
@Getter
@Setter
class AnswerView {

    private long id;

    private boolean correct;

    private String content;

}
