package spyra.lukasz.javaquizzes.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
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

    private boolean predefined;

    private String difficulty;

    @ManyToMany(mappedBy = "quizzes", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @Valid
    private List<Question> questions = new LinkedList<>();

}
