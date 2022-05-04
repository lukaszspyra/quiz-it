package spyra.lukasz.javaquizzes.feature.quizattempt;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import spyra.lukasz.javaquizzes.shared.Answer;

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

}