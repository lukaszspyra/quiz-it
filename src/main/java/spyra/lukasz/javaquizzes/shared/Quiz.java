package spyra.lukasz.javaquizzes.shared;

import lombok.Getter;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Table(name = "quiz")
@Getter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private int maxScore;

    @NotNull
    private LocalDateTime created;

    @NotNull
    private LocalDateTime updated;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @Valid
    private List<Question> questions = new ArrayList<>();
}
