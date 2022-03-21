package spyra.lukasz.javaquizzes.feature.jsonfileparser;

import lombok.Getter;

import java.util.List;

@Getter
class QuestionJson {

    private QuestionJson(QuestionJson.Builder builder) {
        this.apiId = builder.apiId;
        this.score = builder.score;
        this.content = builder.content;
        this.answers = builder.answers;
        this.tags = builder.tags;
        this.restricted = builder.restricted;
        this.difficulty = builder.difficulty;
    }

    private final long apiId;

    private final int score;

    private final String content;

    private final List<AnswerJson> answers;

    private final List<String> tags;

    private final String difficulty;

    private final boolean restricted;

    static class Builder {

        private long apiId;

        private int score;

        private String content;

        private List<AnswerJson> answers;

        private List<String> tags;

        private String difficulty;

        private boolean restricted;

        Builder withApiId(long apiId) {
            this.apiId = apiId;
            return this;
        }

        Builder withScore(int score) {
            this.score = score;
            return this;
        }

        Builder withContent(String content) {
            this.content = content;
            return this;
        }

        Builder withAnswers(List<AnswerJson> answers) {
            this.answers = answers;
            return this;
        }

        Builder withTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        Builder withDifficulty(String difficulty){
            this.difficulty = difficulty;
            return this;
        }

        Builder withRestricted() {
            this.restricted = true;
            return this;
        }

        QuestionJson build() {
            return new QuestionJson(this);
        }
        
    }
}
