package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link spyra.lukasz.javaquizzes.shared.Quiz}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class AttemptView {

    private UUID id;

    private String title;

    private int maxScore;

    private LocalDateTime created;

    private LocalDateTime updated;

    private List<QuestionView> questions;

}
