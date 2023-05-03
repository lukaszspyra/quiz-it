package spyra.lukasz.javaquizzes.feature.quizresults;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

import java.util.UUID;

/**
 * Gets {@link AttemptDetailsView} DTO based on required quiz attempt id
 */
@Service
@RequiredArgsConstructor
class ResultDetailsService {

    private final ResultDetailsRepository repository;

    private final ResultDetailsMapper resultDetailsMapper;

    /**
     * Searches for entity with given id and returns corresponding DTO,
     * result is cached for properties predefined time or until user is logged-out.
     * Uses repository layer and mapper
     *
     * @param attemptId to be searched for
     * @return AttemptDetailsView
     */
    @Cacheable(cacheNames = "AttemptDetails")
    public AttemptDetailsView quizAttemptDetails(UUID attemptId){
        TakeQuiz attemptById = repository.getById(attemptId);
        return resultDetailsMapper.quizAttemptToDetailsView(attemptById);
    }
}
