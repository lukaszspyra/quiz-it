package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import spyra.lukasz.javaquizzes.shared.Question;

import static org.testng.Assert.*;

@Test(groups = "Mapper")
public class QuestionMapperTest {

    private final QuestionMapper questionMapper = new QuestionMapper(new AnswerMapper());

    @Test(description = "DTOs shall be equal, but are not.", dataProvider = "questionsWithCorrespondingDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallMapQuestionToCorrectView(Question question, QuestionView dto) {
        //given

        //when
        final QuestionView result = questionMapper.toView(question);

        //then
        assertEquals(result, dto);
    }

}