package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.Quiz}
 */
@Getter
@Setter
class AttemptView {

    private long id;

    private String title;

    private int maxScore;

    private LocalDateTime created;

    private LocalDateTime updated;

    private List<QuestionView> questions;

}
