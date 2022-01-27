package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import spyra.lukasz.javaquizzes.feature.quizselector.QuizRepository;
import spyra.lukasz.javaquizzes.shared.Quiz;
import spyra.lukasz.javaquizzes.shared.TakeQuiz;
import spyra.lukasz.javaquizzes.shared.User;

import java.time.LocalDateTime;

@Component
class QuizStarter {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TakeQuizRepository takeQuizRepository;

    TakeQuiz takeQuiz(long quizId, String userEmail) {
        Quiz presentQuiz = quizRepository.getById(quizId);
        User activeUser = takeQuizRepository.findUserByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuiz(presentQuiz);
        takeQuiz.setStart(LocalDateTime.now());
        takeQuiz.setUser(activeUser);
        return takeQuizRepository.save(takeQuiz);
    }
}
