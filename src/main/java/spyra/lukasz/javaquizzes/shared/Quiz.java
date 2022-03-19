package spyra.lukasz.javaquizzes.shared;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Transactional
@Entity
@Table(name = "quiz")
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    private int maxScore;

    @NotNull
    private LocalDateTime created;

    @NotNull
    private LocalDateTime updated;

    private boolean restricted;

    @ManyToMany(mappedBy = "quizzes", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @Valid
    private List<Question> questions = new LinkedList<>();
}
