package spyra.lukasz.javaquizzes.userstatistics;

import spyra.lukasz.javaquizzes.model.TakeQuizAnswer;
import spyra.lukasz.javaquizzes.quizselector.Quiz;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Table(name = "take_quiz")
public class TakeQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int score;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime finish;

    @OneToMany(mappedBy = "takeQuiz", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
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

}