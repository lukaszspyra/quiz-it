package spyra.lukasz.javaquizzes.feature.jsonparser;

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

    private int maxScore;

    private LocalDateTime created;

    private LocalDateTime updated;

    private List<QuestionJson> questions;

}
