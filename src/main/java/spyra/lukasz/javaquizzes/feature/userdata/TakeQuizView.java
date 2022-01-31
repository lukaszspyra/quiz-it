package spyra.lukasz.javaquizzes.feature.userdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class TakeQuizView {

    private long id;

    private int score;

    private String duration;

    private String quizTitle;

    private int quizMaxScore;
}
