package spyra.lukasz.javaquizzes.feature.quizselect;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for quiz entity used to present details in controller
 */
@Getter
@Setter
class QuizView {

    private long id;

    private String title;

    private int score;

    private String created;

    private String updated;

}
