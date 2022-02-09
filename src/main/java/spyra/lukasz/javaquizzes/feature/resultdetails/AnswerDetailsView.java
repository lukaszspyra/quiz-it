package spyra.lukasz.javaquizzes.feature.resultdetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AnswerDetailsView {

    private long id;

    private boolean correct;

    private String content;
}
