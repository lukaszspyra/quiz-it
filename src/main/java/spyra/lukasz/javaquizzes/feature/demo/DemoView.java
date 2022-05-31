package spyra.lukasz.javaquizzes.feature.demo;

import lombok.Getter;

/**
 * DTO for demo quiz entity used to present details in controller
 */
@Getter
final class DemoView {

    private final long id;

    private final String title;

    private final int score;

    private final String created;

    private final String updated;

    private DemoView(final DemoView.Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.score = builder.score;
        this.created = builder.created;
        this.updated = builder.updated;
    }

    static class Builder {

        private long id;
        private String title;
        private int score;
        private String created;
        private String updated;

        Builder withId(long id) {
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

        DemoView build() {
            return new DemoView(this);
        }
    }
}
