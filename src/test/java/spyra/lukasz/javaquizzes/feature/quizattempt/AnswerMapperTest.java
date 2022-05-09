package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import spyra.lukasz.javaquizzes.shared.Answer;

import static org.testng.Assert.assertNotEquals;

@Test(groups = "Mapper")
public class AnswerMapperTest {

    @Test(dataProvider = "answersWithCorrespondingDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallMapAnswerToCorrectView(Answer answer, AnswerView dto) {
        //given
        SoftAssert softAssert = new SoftAssert();
        AnswerMapper mapper = new AnswerMapper();

        //when
        final AnswerView resultView = mapper.toView(answer);

        //then
        softAssert.assertEquals(resultView.getId(), dto.getId());
        softAssert.assertEquals(resultView.isCorrect(), dto.isCorrect());
        softAssert.assertEquals(resultView.getContent(), dto.getContent());
        softAssert.assertAll("Shall have the same id, correctness and contents,");
    }

    @Test(description = "Shall fail to return proper DTO, but it did not.", dataProvider = "answersWithRandomDTOs", dataProviderClass = AttemptDataProvider.class)
    public void shallNotMapAnswerToRandomView(Answer answer, AnswerView dto) {
        //given
        AnswerMapper mapper = new AnswerMapper();

        //when
        final AnswerView result = mapper.toView(answer);

        //then
        assertNotEquals(result, dto);
    }

}