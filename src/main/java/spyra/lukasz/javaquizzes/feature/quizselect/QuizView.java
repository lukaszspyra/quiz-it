package spyra.lukasz.javaquizzes.feature.quizselect;

import lombok.Getter;

import java.util.UUID;

/**
 * DTO for quiz entity used to present details in controller
 */
@Getter
final class QuizView {

    private final UUID id;

    private final String title;

    private final int score;

    private final String created;

    private final String updated;

    private QuizView(final QuizView.Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.score = builder.score;
        this.created = builder.created;
        this.updated = builder.updated;
    }

    static class Builder {

        private UUID id;
        private String title;
        private int score;
        private String created;
        private String updated;

        Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        Builder withScore(int score) {
            this.score = score;
            return this;
        }

        Builder withCreated(String created) {
            this.created = created;
            return this;
        }

        Builder withUpdated(String updated) {
            this.updated = updated;
            return this;
        }

        QuizView build() {
            return new QuizView(this);
        }
    }
}
