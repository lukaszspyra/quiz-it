package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import spyra.lukasz.javaquizzes.shared.Answer;

import java.util.UUID;

/**
 * DTO for {@link Answer} entity
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
class AnswerView {

    private UUID id;

    private boolean correct;

    private String content;

}
