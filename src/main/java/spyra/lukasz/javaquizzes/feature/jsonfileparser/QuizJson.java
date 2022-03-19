package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonDeserialize(using = JsonCustomDeserializer.class)
@Setter
@Getter
class QuizJson {

    private String title;

    private LocalDateTime created;

    private LocalDateTime updated;

    private int maxScore;

    private List<QuestionJson> questions;

}
