package spyra.lukasz.javaquizzes.feature.quizattempt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AnswerView {

    private long id;

    private boolean correct;

    private String content;

}
