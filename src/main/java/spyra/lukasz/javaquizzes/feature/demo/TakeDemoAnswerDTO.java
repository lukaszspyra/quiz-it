package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.TakeQuizAnswer} entity
 */
@Getter
@Setter
final class TakeDemoAnswerDTO {

    private UUID id;

    private boolean correct;

    private String content;

}
