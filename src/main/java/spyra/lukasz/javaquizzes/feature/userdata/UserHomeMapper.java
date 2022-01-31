package spyra.lukasz.javaquizzes.feature.userdata;

import org.mapstruct.Mapper;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import java.time.Duration;

@Mapper(componentModel = "spring")
interface UserHomeMapper {

    UserView from(User user);

    default TakeQuizView from(TakeQuiz takeQuiz) {
        TakeQuizView takeQuizView = new TakeQuizView();
        takeQuizView.setId(takeQuiz.getId());
        takeQuizView.setScore(takeQuiz.getScore());
        takeQuizView.setDuration(calcAttemptTime(takeQuiz));
        takeQuizView.setQuizMaxScore(takeQuiz.getQuiz().getMaxScore());
        takeQuizView.setQuizTitle(takeQuiz.getQuiz().getTitle());
        return takeQuizView;
    }

    private String calcAttemptTime(TakeQuiz takeQuiz) {
        var start = takeQuiz.getStart();
        var finish = takeQuiz.getFinish();
        var duration = Duration.between(start, finish);
        return duration.toMinutesPart() +
                "m : " +
                duration.toSecondsPart() +
                "s";
    }
}
