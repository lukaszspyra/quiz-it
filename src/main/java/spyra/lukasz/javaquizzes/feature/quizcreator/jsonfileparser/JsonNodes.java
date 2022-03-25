package spyra.lukasz.javaquizzes.feature.quizcreator.jsonfileparser;

enum JsonNodes {
    TITLE("title"),
    CREATED("created"),
    UPDATED("updated"),
    RESTRICTED("restricted"),
    QUESTIONS("questions"),
    SCORE("score"),
    ANSWERS("answers"),
    CORRECT("correct"),
    TAGS("tags"),
    NAME("name"),
    ID("id"),
    QUESTION("question"),
    CORRECT_ANSWERS("correct_answers"),
    DIFFICULTY("difficulty"),
    PREDEFINED("predefined");

    private final String value;

    public String getValue() {
        return value;
    }

    JsonNodes(String value) {
        this.value = value;
    }
}