package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.Test;
import spyra.lukasz.javaquizzes.shared.Quiz;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test(groups = "Mapper")
public class AttemptMapperTest {

    @Test(description = "Shall return proper DTO, but it did not", dataProvider = "quizzesWithCorrespondingAttemptDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallMapUserToAuthorityChangeDTO(Quiz quiz, AttemptView dto) {
        //given
        AnswerMapper answerMapper = new AnswerMapper();
        QuestionMapper questionMapper = new QuestionMapper(answerMapper);
        AttemptMapper mapper = new AttemptMapper(questionMapper);

        //when
        final AttemptView result = mapper.toView(quiz);

        //then
        assertEquals(dto, result);
    }

    @Test(description = "Shall NOT return proper DTO, but it did", dataProvider = "quizzesWithRandomAttemptDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallNotMapUserToAuthorityChangeDTO(Quiz quiz, AttemptView dto) {
        //given
        AnswerMapper answerMapper = new AnswerMapper();
        QuestionMapper questionMapper = new QuestionMapper(answerMapper);
        AttemptMapper mapper = new AttemptMapper(questionMapper);

        //when
        final AttemptView result = mapper.toView(quiz);

        //then
        assertNotEquals(dto, result);
    }
}