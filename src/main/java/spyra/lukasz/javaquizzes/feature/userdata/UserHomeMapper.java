package spyra.lukasz.javaquizzes.feature.userdata;

import org.mapstruct.Mapper;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * MapStruct mapper with default implementation of {@link TakeQuiz} to {@link TakeQuizView} mapping
 */
@Mapper(componentModel = "spring")
interface UserHomeMapper {

    UserView from(User user);

    /**
     * Default implementation of mapping between entity and DTO
     * <p>
     * For presentation purpose, the duration of the quiz attempt is calculateda and formatted, based on start and finish
     * moments saved in database
     *
     * @param takeQuiz entity from database
     * @return DTO with quiz attempt duration
     */
    default TakeQuizView from(TakeQuiz takeQuiz) {
        TakeQuizView takeQuizView = new TakeQuizView();
        takeQuizView.setId(takeQuiz.getId());
        takeQuizView.setScore(takeQuiz.getScore());
        takeQuizView.setDuration(calcAttemptTime(takeQuiz));
        takeQuizView.setQuizMaxScore(takeQuiz.getQuiz().getMaxScore());
        takeQuizView.setQuizTitle(takeQuiz.getQuiz().getTitle());
        takeQuizView.setDifficulty(takeQuiz.getQuiz().getDifficulty());
        takeQuizView.setAttemptTime(localDateTimeToStringFormatter(takeQuiz.getFinish()));
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

    private String localDateTimeToStringFormatter(final LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return localDateTime.format(formatter);
    }
}
