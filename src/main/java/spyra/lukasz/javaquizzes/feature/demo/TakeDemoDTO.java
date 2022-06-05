package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
final class TakeDemoDTO {

    private long id;

    private int score;

    private LocalDateTime start;

    private LocalDateTime finish;

    private List<TakeDemoAnswerDTO> givenAnswers;

    private List<QuestionDTO> remainingQuestions;

    List<QuestionDTO> questionsToAnswer() {
        return remainingQuestions;
    }

}
