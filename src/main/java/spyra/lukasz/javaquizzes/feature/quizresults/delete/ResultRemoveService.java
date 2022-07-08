package spyra.lukasz.javaquizzes.feature.quizresults.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import java.util.Optional;
import java.util.UUID;

/**
 * Service for removal of {@link TakeQuiz}
 */
@Service
@RequiredArgsConstructor
class ResultRemoveService {

    private final ResultDeleteRepository repository;

    /**
     * Delete {@link TakeQuiz} by id
     *
     * Prior removal, database is searched for entity of given id. If it does not exist, delete will not be initialized.
     *
     * @param attemptId of entity for removal
     */
    void deleteAttemptResult(UUID attemptId) {
        final Optional<TakeQuiz> attemptById = repository.findById(attemptId);
        attemptById.ifPresent(repository::delete);
    }
}
