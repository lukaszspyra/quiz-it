package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AnswerDetailsView {

    private long id;

    private boolean correct;

    private String content;
}
