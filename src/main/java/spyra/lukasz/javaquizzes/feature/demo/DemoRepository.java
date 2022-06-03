package spyra.lukasz.javaquizzes.feature.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spyra.lukasz.javaquizzes.shared.Quiz;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

interface DemoRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findAllByDemoTrueAndTitleEqualsIgnoreCase(@NotNull String title);

    @Query("select q from Quiz q left join fetch q.questions where q.id = ?1")
    Quiz getDemoById(@NotNull Long id);
}
