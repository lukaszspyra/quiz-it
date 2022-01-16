package spyra.lukasz.javaquizzes.feature.jsonparser;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
class QuizJson {

    private String title;

    private int maxScore;

    private LocalDateTime created;

    private LocalDateTime updated;

    private List<QuestionJson> questions;

}
