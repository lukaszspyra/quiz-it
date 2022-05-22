package spyra.lukasz.javaquizzes.feature.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import spyra.lukasz.javaquizzes.shared.Quiz;

interface DemoRepository extends JpaRepository<Quiz, Long> {

    Quiz findAllByDemoTrue();
}
