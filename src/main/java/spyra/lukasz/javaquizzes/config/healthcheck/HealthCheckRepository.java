package spyra.lukasz.javaquizzes.config.healthcheck;

import org.springframework.data.jpa.repository.JpaRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.UUID;

/**
 * Repository to check database response
 */
interface HealthCheckRepository extends JpaRepository<Quiz, UUID> {

    long count();
}
