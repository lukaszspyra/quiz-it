package spyra.lukasz.javaquizzes.feature.demo;

import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps {@link Quiz} to {@link DemoView}
 */
@Component
class DemoToViewMapper {

    List<DemoView> toView(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::toView)
                .collect(Collectors.toList());
    }

    DemoView toView(Quiz quiz) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DemoView.Builder viewBuilder = new DemoView.Builder();
        return viewBuilder.withId(quiz.getId())
                .withTitle(quiz.getTitle())
                .withScore(quiz.getMaxScore())
                .withCreated(quiz.getCreated().format(formatter))
                .withUpdated(quiz.getUpdated().format(formatter))
                .build();
    }

}
