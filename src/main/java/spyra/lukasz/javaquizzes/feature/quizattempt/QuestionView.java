package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * DTO to sent question data to view
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class QuestionView {

    private UUID id;

    private int score;

    private String content;

    private String difficulty;

    private List<AnswerView> answers;

}
