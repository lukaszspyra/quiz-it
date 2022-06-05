package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
final class QuestionDTO {

    private long id;

    private String content;

    private List<AnswerDTO> answers;

}
