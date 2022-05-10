package spyra.lukasz.javaquizzes.feature.quizselect;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps {@link Quiz} to {@link QuizView}
 */
@Component
class QuizToViewMapper {

    List<QuizView> toView(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::toView)
                .collect(Collectors.toList());
    }

    QuizView toView(Quiz quiz) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        QuizView.Builder viewBuilder = new QuizView.Builder();
        return viewBuilder.withId(quiz.getId())
                .withTitle(quiz.getTitle())
                .withScore(quiz.getMaxScore())
                .withCreated(quiz.getCreated().format(formatter))
                .withUpdated(quiz.getUpdated().format(formatter))
                .build();
    }

}
