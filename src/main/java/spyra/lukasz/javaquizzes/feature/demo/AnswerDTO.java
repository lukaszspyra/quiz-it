package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.Answer} entity
 */
@Getter
@Setter
final class AnswerDTO {

    private UUID id;

    private boolean correct;

    private String content;

}
