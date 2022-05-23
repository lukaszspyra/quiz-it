package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;

@Service
@RequiredArgsConstructor
class DemoService {

    private final DemoRepository repository;

    List<Quiz> findDemosByTitle(String title) {
        return repository.findAllByDemoTrueAndTitleEqualsIgnoreCase(title);
    }

    Quiz getById(long id) {
        return repository.getById(id);
    }
}
