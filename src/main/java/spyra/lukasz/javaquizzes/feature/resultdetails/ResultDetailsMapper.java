package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.TakeQuizAnswer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
class ResultDetailsMapper {

    @Autowired
    MarkedAnswerDetailsRepository uDetailAnsRepository;

    @Autowired
    AnswerMapStructMapper aMapStructMapper;

    AttemptDetailsView quizAttemptToDetailsView(final TakeQuiz takeQuiz) {
        AttemptDetailsView attemptDetailsView = new AttemptDetailsView();
        attemptDetailsView.setAttemptId(takeQuiz.getId());
        attemptDetailsView.setScore(takeQuiz.getScore());
        attemptDetailsView.setStart(localDateTimeToStringFormatter(takeQuiz.getStart()));
        attemptDetailsView.setFinish(localDateTimeToStringFormatter(takeQuiz.getFinish()));
        attemptDetailsView.setDuration(calcDuration(takeQuiz));
        attemptDetailsView.setQuizMaxScore(takeQuiz.getQuiz().getMaxScore());
        attemptDetailsView.setQuestionsSolved(mapQuestToDetailsView(takeQuiz.getQuiz().getQuestions(), takeQuiz.getId()));
        return attemptDetailsView;
    }

    private String localDateTimeToStringFormatter(final LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    private String calcDuration(final TakeQuiz takeQuiz) {
        var start = takeQuiz.getStart();
        var finish = takeQuiz.getFinish();
        var duration = Duration.between(start, finish);
        return duration.toMinutesPart() +
                "m : " +
                duration.toSecondsPart() +
                "s";
    }

    private QuestionDetailsView mapQuestToDetailsView(Question question, long takeQuizId) {
        QuestionDetailsView questDetails = new QuestionDetailsView();
        questDetails.setQuestionId(question.getId());
        questDetails.setQuestionScore(question.getScore());
        questDetails.setPossibleAnswers(aMapStructMapper.possibleAnsToDetailsViews(question.getAnswers()));

        List<TakeQuizAnswer> ansMarkedAsTrue = uDetailAnsRepository.findTakeQuizAnswerByQuestionIdAndTakeQuizId(question.getId(), takeQuizId);
        questDetails.setMarkedAnswers(aMapStructMapper.markedAnsToDetailsView(ansMarkedAsTrue));
        return questDetails;
    }

    private List<QuestionDetailsView> mapQuestToDetailsView(List<Question> questionList, long takeQuizId) {
        return questionList.stream()
                .map(e -> mapQuestToDetailsView(e, takeQuizId))
                .collect(Collectors.toUnmodifiableList());
    }
}
