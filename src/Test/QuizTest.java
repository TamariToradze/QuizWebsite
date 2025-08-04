package Test;

import Models.Quiz;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuizTest {

    private Quiz quiz;

    @Before
    public void setup() {
        quiz = new Quiz(
                1,
                "creator",
                "Testing quiz",
                "This is a testing quiz"
        );
    }

    @Test
    public void getQuizIdTest() {
        assertEquals(1, quiz.getQuizID());
    }

    @Test
    public void setQuizIdTest() {
        quiz.setQuizID(2);
        assertEquals(2, quiz.getQuizID());
    }

    @Test
    public void getCreatorUsernameTest() {
        String expected = "creator";
        assertEquals(expected, quiz.getCreatorUsername());
    }

    @Test
    public void setCreatorUsernameTest() {
        quiz.setCreatorUsername("creator_1");
        String expected = "creator_1";
        assertEquals(expected, quiz.getCreatorUsername());
    }

    @Test
    public void getQuizNameTest() {
        String expected = "Testing quiz";
        assertEquals(expected, quiz.getQuizName());
    }

    @Test
    public void setQuizNameTest() {
        quiz.setQuizName("Test quiz");
        String expected = "Test quiz";
        assertEquals(expected, quiz.getQuizName());
    }

    @Test
    public void getQuizDescriptionTest() {
        String expected = "This is a testing quiz";
        assertEquals(expected, quiz.getQuizDescription());
    }

    @Test
    public void setQuizDescriptionTest() {
        quiz.setQuizDescription("This is a testing quiz 2");
        String expected = "This is a testing quiz 2";
        assertEquals(expected, quiz.getQuizDescription());
    }

    @Test
    public void getQuizScoreTest() {
        assertEquals(0, quiz.getQuizScore());
    }

    @Test
    public void setQuizScoreTest() {
        quiz.setQuizScore(100);
        assertEquals(100, quiz.getQuizScore());
    }

    @Test
    public void incrementQuizScoreTest() {
        quiz.incrementQuizScore(1);
        assertEquals(1, quiz.getQuizScore());
    }

    @Test
    public void resetQuizScoreTest() {
        quiz.resetQuizScore();
        assertEquals(0, quiz.getQuizScore());
    }

    @Test
    public void addRemoveQuestionIdTest() {
        quiz.addQuestionId(200);
        assertTrue(quiz.getQuestionIds().contains(200));

        quiz.removeQuestionId(200);
        assertFalse(quiz.getQuestionIds().contains(200));
    }

    @Test
    public void equalHashCodeTest() {
        Quiz quiz_1 = new Quiz(1, "user1",
                "testing Quiz",
                "this is a testing quiz"
        );

        Quiz quiz_2 = new Quiz(1,
                "user2",
                "another testing quiz",
                "this is another testing quiz");

        assertEquals(quiz_1, quiz_2);
        assertEquals(quiz_1.hashCode(), quiz_2.hashCode());
    }
}