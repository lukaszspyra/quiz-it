package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.TakeQuizAnswer} entity
 */
@Getter
@Setter
final class TakeDemoAnswerDTO {

    private long id;

    private boolean correct;

    private String content;

}
