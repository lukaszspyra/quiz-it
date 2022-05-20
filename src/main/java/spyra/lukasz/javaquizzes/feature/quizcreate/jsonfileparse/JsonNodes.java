package spyra.lukasz.javaquizzes.feature.quizcreate.jsonfileparse;

/**
 * Node values used in available JSON formatted quizzes
 */
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
    PREDEFINED("predefined"),

    DEMO("demo");

    private final String value;

    String getValue() {
        return value;
    }

    JsonNodes(String value) {
        this.value = value;
    }
}