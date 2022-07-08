package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.Question} entity
 */
@Getter
@Setter
final class QuestionDTO {

    private UUID id;

    private String content;

    private List<AnswerDTO> answers;

}
