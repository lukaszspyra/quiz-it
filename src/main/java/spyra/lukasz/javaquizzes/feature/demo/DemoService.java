package spyra.lukasz.javaquizzes.feature.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spyra.lukasz.javaquizzes.shared.Question;
import spyra.lukasz.javaquizzes.shared.Quiz;

import java.util.List;
import java.util.UUID;

/**
 * Delivers quiz demo instances mapped to DTO.
 */
@Service
@RequiredArgsConstructor
class DemoService {

    private final DemoRepository demoRepository;

    private final DemoQuestionRepository questionRepository;

    private final DemoToViewMapper mapper;

    /**
     * Demo mapped to view, result is cached for properties predefined time
     *
     * @param title of demo to be looked for in database
     * @return found demo instance mapped to view
     */
    @Cacheable(cacheNames = "DemosByTitle")
    public List<DemoView> findDemosByTitle(String title) {
        return mapper.toView(demoRepository.findAllByDemoTrueAndTitleEqualsIgnoreCase(title));
    }

    /**
     * Marked @Transactional to keep the same references to entities, Hibernate cache will combine fetched dependencies
     * in two queries
     *
     * @param id
     * @return demo with fetched questions/answers
     */
    @Transactional
    public Quiz getDemoById(UUID id) {
        Quiz demo = demoRepository.getDemoWithQuestionsById(id);
        List<Question> demoQuestionsByQuiz = questionRepository.findDemoQuestionsByQuiz(demo);
        return demo;
    }
}
