package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;

@Service
@RequiredArgsConstructor
class DemoService {

    private final DemoRepository repository;

    private final DemoToViewMapper mapper;

    List<DemoView> findDemosByTitle(String title) {
        return mapper.toView(repository.findAllByDemoTrueAndTitleEqualsIgnoreCase(title));
    }

    Quiz getDemoById(long id) {
        return repository.getDemoById(id);
    }
}
