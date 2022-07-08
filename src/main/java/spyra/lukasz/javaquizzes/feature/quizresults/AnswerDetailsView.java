package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for answer details presented for user
 */
@Getter
@Setter
class AnswerDetailsView {

    private UUID id;

    private boolean correct;

    private String content;
}
