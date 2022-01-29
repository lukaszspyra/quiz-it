package spyra.lukasz.javaquizzes.feature.quizselector;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
class QuizView {

    private long id;

    private String title;

    private int score;

    private String created;

    private String updated;

}
