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

        void withApiId(long apiId) {
            this.apiId = apiId;
        }

        void withScore(int score) {
            this.score = score;
        }

        void withContent(String content) {
            this.content = content;
        }

        void withAnswers(List<AnswerJson> answers) {
            this.answers = answers;
        }

        void withTags(List<String> tags) {
            this.tags = tags;
        }

        void withDifficulty(String difficulty){
            this.difficulty = difficulty;
        }

        void withRestricted() {
            this.restricted = true;
        }
        QuestionJson build() {
            return new QuestionJson(this);
        }
        
    }
}
