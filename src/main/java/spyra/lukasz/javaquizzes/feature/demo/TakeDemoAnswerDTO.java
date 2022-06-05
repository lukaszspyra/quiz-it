package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
final class TakeDemoAnswerDTO {

    private long id;

    private boolean correct;

    private String content;

}
