package Test;

import Models.Enums.QuestionType;
import Models.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class QuestionTest {

    private Question question;

    @Before
    public void setup() {
        question = new Question(1);
    }

    @Test
    public void getQuestionIdTest() {
        assertEquals(1, question.getQuestionId());
    }

    @Test
    public void getQuizIdTest() {
        assertEquals(0, question.getQuizId());
    }

    @Test
    public void getQuestionTypeTest() {
        assertNull(question.getQuestionType());
    }

    @Test
    public void getQuestionTextTest() {
        assertNull(question.getQuestionText());
    }

    @Test
    public void getSingleQuestionAnswerTest() {
        assertNull(question.getSingleQuestionAnswer());
    }

    @Test
    public void getAlternativeAnswerTest() {
        assertTrue(question.getAlternativeAnswers().isEmpty());
    }

    @Test
    public void getMultipleChoiceAnswersTest() {
        assertTrue(question.getMultipleChoiceAnswers().isEmpty());
    }

    @Test
    public void getMultipleChoiceCorrectIndexesTest() {
        assertTrue(question.getMultipleChoiceCorrectIndexes().isEmpty());
    }

    @Test
    public void getQuestionImageTest() {
        assertNull(question.getQuestionImage());
    }

    @Test
    public void getMultipleAnswerFieldsTest() {
        assertTrue(question.getMultipleAnswerFields().isEmpty());
    }

    @Test
    public void getMatchingPairsTest() {
        assertTrue(question.getMatchingPairs().isEmpty());
    }

    @Test
    public void setQuestionTextWithNullTest() {
        question.setQuestionText(null);
        assertNull(question.getQuestionText());
    }

    @Test
    public void setQuestionTextWithEmptyStringTest() {
        question.setQuestionText("");
        assertEquals("", question.getQuestionText());
    }

    @Test
    public void setAlternativeAnswersWithNullTest() {
        question.setAlternativeAnswers(null);
        assertNull(question.getAlternativeAnswers());
    }

    @Test
    public void setAlternativeAnswersWithEmptySetTest() {
        HashSet<String> emptySet = new HashSet<>();
        question.setAlternativeAnswers(emptySet);
        assertTrue(question.getAlternativeAnswers().isEmpty());
    }

    @Test
    public void setMultipleChoiceAnswersWithNullTest() {
        question.setMultipleChoiceAnswers(null);
        assertNull(question.getMultipleChoiceAnswers());
    }

    @Test
    public void setMultipleChoiceCorrectIndexesWithEmptyListTest() {
        ArrayList<Integer> emptyList = new ArrayList<>();
        question.setMultipleChoiceCorrectIndexes(emptyList);
        assertTrue(question.getMultipleChoiceCorrectIndexes().isEmpty());
    }

    @Test
    public void setMatchingPairsWithEmptyMapTest() {
        HashMap<String, String> emptyMap = new HashMap<>();
        question.setMatchingPairs(emptyMap);
        assertTrue(question.getMatchingPairs().isEmpty());
    }

    @Test
    public void addAlternativeAnswerTest() {
        question.addAlternativeAnswer("Four");
        question.addAlternativeAnswer("4");
        assertEquals(2, question.getAlternativeAnswers().size());
    }

    @Test
    public void addMultipleChoiceAnswerTest() {
        question.addMultipleChoiceAnswer("1", false);
        question.addMultipleChoiceAnswer("2", false);
        question.addMultipleChoiceAnswer("3", false);
        question.addMultipleChoiceAnswer("4", true);
        assertEquals(4, question.getMultipleChoiceAnswers().size());
        assertEquals(1, question.getMultipleChoiceCorrectIndexes().size());
    }

    @Test
    public void addMatchingPairsTest() {
        question.addMatchingPair("A", "1");
        question.addMatchingPair("B", "2");
        assertEquals(2, question.getMatchingPairs().size());
    }

    @Test
    public void toStringTest() {
        String expectedToString = "Question{" +
                "questionId=1, quizId=0, questionType=null, questionText='null', " +
                "singleQuestionAnswer='null', alternativeAnswers=[], multipleChoiceAnswers=[], " +
                "multipleChoiceCorrectIndexes=[], questionImage='null', multipleAnswerFields=[], " +
                "matchingPairs={}}";
        assertEquals(expectedToString, question.toString());
    }

    @Test
    public void isMultipleChoiceTest() {
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        assertTrue(question.isMultipleChoice());
    }
}