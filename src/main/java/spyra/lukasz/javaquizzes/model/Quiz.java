package spyra.lukasz.javaquizzes.model;

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
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private int score;

    @NotNull
    private LocalDateTime created;

    @NotNull
    private LocalDateTime updated;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @Valid
    private List<TakeQuiz> takenQuizzes = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @Valid
    private List<Question> questions = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
