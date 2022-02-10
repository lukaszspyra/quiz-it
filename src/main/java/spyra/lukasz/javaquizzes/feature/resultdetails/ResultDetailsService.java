package spyra.lukasz.javaquizzes.feature.resultdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;

@Service
class ResultDetailsService {

    @Autowired
    private ResultDetailsRepository repository;

    @Autowired
    private ResultDetailsMapper resultDetailsMapper;

    AttemptDetailsView quizAttemptDetails(long attemptId){
        TakeQuiz attemptById = repository.getById(attemptId);
        return resultDetailsMapper.quizAttemptToDetailsView(attemptById);
    }
}
