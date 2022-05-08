package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.Test;
import spyra.lukasz.javaquizzes.shared.Question;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test(groups = "Mapper")
public class QuestionMapperTest {

    private final QuestionMapper questionMapper = new QuestionMapper(new AnswerMapper());

    @Test(description = "Shall return proper DTO, but it did not", dataProvider = "questionsWithCorrespondingDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallMapQuestionToCorrectView(Question question, QuestionView dto) {
        //given

        //when
        final QuestionView result = questionMapper.toView(question);

        //then
        assertEquals(result, dto);
    }

    @Test(description = "Shall fail to return proper DTO, but it did not.", dataProvider = "questionsWithRandomDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallNotMapQuestionToRandomView(Question question, QuestionView dto) {
        //given

        //when
        final QuestionView result = questionMapper.toView(question);

        //then
        assertNotEquals(result, dto);
    }
}
