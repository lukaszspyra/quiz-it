package spyra.lukasz.javaquizzes.shared;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "take_quiz")
@Getter
@Setter
public final class TakeQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int score;

    @NotNull
    private LocalDateTime start;

    private LocalDateTime finish;

    @OneToMany(mappedBy = "takeQuiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Valid
    private List<TakeQuizAnswer> givenAnswers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @NotNull
    @Valid
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    @Valid
    private User user;

    /**
     * Updates {@link TakeQuiz} attempt with last question score and time of answer
     *
     * Score is added to existing score, whilst updated time replaces existing finish time
     *
     * @param questionScore received for the last question
     * @param questFinishTIme time of completion of the last question`
     * @return updated TakeQuiz attempt object
     */
    public TakeQuiz progressQuizAttempt(final int questionScore, final LocalDateTime questFinishTIme){
        updateScore(questionScore);
        updateFinishTime(questFinishTIme);
        return this;
    }

    /**
     * Updates finish time with next question answer time
     * @param updatedTime after each question answer
     *
     * If User decides to stop the quiz before reaching the end, finish time is set to last question answer
     */
    private void updateFinishTime(LocalDateTime updatedTime) {
        finish = updatedTime;
    }

    /**
     * {@link TakeQuiz#score} is updated by adding last question result to existing sum of results
     */
    private void updateScore(int questionScore) {
        score = score + questionScore;
    }

    /**
     * Calculates latest available quiz end time moment in epoch second
     * @param minutesForWholeQuiz minutes available for the quiz attempt
     * @return time moment in Java epoch, when the quiz must end
     */
    public long calcTimeForQuizInEpochSeconds(int minutesForWholeQuiz) {
        return start.atZone(ZoneId.systemDefault()).plusMinutes(minutesForWholeQuiz).toInstant().getEpochSecond();
    }

    /**
     * Calculates total time used for quiz
     *
     * @return difference between start and finish quiz times
     */
    public Duration calcAttemptTime() {
        return Duration.between(start, finish);
    }

    /**
     * Shows questions to answer in given attempt
     * @return list of questions for given quiz attempt
     */
    public List<Question> questionsToAnswer(){
        return quiz.getQuestions();
    }
}