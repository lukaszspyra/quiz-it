package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for Quiz entity read from available JSON format
 *
 * Uses custom deserializer for proper mapping. Due to large amount of parameters, Builder pattern implemented by static class.
 */
@JsonDeserialize(using = JsonCustomDeserializer.class)
@Getter
final class QuizJson {

    private QuizJson(QuizJsonBuilder builder) {
        this.title = builder.title;
        this.restricted = builder.restricted;
        this.predefined = builder.predefined;
        this.demo = builder.demo;
        this.maxScore = builder.maxScore;
        this.created = builder.created;
        this.updated = builder.updated;
        this.difficulty = builder.difficulty;
        this.questions = builder.questions;
    }

    private final String title;

    private final boolean restricted;

    private final boolean predefined;

    private final boolean demo;

    private final int maxScore;

    private final LocalDateTime created;

    private final LocalDateTime updated;

    private final String difficulty;

    private final List<QuestionJson> questions;

    static final class QuizJsonBuilder {

        private String title;
        private boolean restricted;
        private boolean predefined;

        private boolean demo;
        private int maxScore;
        private LocalDateTime created;
        private LocalDateTime updated;
        private String difficulty;
        private List<QuestionJson> questions;

        QuizJsonBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        QuizJsonBuilder setRestricted(boolean restricted) {
            this.restricted = restricted;
            return this;
        }

        QuizJsonBuilder setPredefined(boolean predefined) {
            this.predefined = predefined;
            return this;
        }

        QuizJsonBuilder setDemo(boolean demo){
            this.demo = demo;
            return this;
        }
        QuizJsonBuilder setMaxScore(int maxScore) {
            this.maxScore = maxScore;
            return this;
        }

        QuizJsonBuilder setCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        QuizJsonBuilder setUpdated(LocalDateTime updated) {
            this.updated = updated;
            return this;
        }

        QuizJsonBuilder setQuestions(List<QuestionJson> questions) {
            this.questions = questions;
            return this;
        }

        QuizJsonBuilder setDifficulty(String difficulty){
            this.difficulty = difficulty;
            return this;
        }

        QuizJson createQuizJson() {
            return new QuizJson(this);
        }
    }

}
