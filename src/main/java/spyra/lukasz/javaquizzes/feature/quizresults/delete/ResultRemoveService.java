package spyra.lukasz.javaquizzes.feature.quizresults.delete;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
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
     * <p>
     * Prior removal, database is searched for entity of given id. If it does not exist, delete will not be initialized.
     * Clears caches with UserProfile and AttemptDetails
     *
     * @param attemptId of entity for removal
     */
    @Caching(evict = {
            @CacheEvict(cacheNames = "AttemptDetails", key = "#attemptId"),
            @CacheEvict(cacheNames = "UserProfile", allEntries = true)
    })
    public void deleteAttemptResult(UUID attemptId) {
        final Optional<TakeQuiz> attemptById = repository.findById(attemptId);
        attemptById.ifPresent(repository::delete);
    }
}
