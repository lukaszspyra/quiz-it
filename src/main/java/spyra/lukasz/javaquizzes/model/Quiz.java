package spyra.lukasz.javaquizzes.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private int score;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
