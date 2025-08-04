package Test;

import Models.QuizHistory;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class QuizHistoryTest {

    private QuizHistory quizHistory;

    @Before
    public void setup() {
        quizHistory = new QuizHistory(
                1,
                "user"
        );
    }

    @Test
    public void getQuizIdTest() {
        assertEquals(1, quizHistory.getQuizId());
    }

    @Test
    public void getUsernameTest() {
        assertEquals("user", quizHistory.getUsername());
    }

    @Test
    public void getQuizScoreTest() {
        assertEquals(0, quizHistory.getQuizScore());
    }

    @Test
    public void getStartTimeTest() {
        assertNull(quizHistory.getStartTime());
    }

    @Test
    public void getEndTimeTest() {
        assertNull(quizHistory.getEndTime());
    }

    @Test
    public void getElapsedTimeTest() {
        assertEquals(0, quizHistory.getElapsedTime());
    }

    @Test
    public void setQuizIdTest() {
        quizHistory.setQuizId(2);
        assertEquals(2, quizHistory.getQuizId());
    }

    @Test
    public void setUsernameTest() {
        quizHistory.setUsername("admin");
        assertEquals("admin", quizHistory.getUsername());
    }

    @Test
    public void setQuizScoreTest() {
        quizHistory.setQuizScore(50);
        assertEquals(50, quizHistory.getQuizScore());
    }

    @Test
    public void setStartTimeTest() {
        Time startTime = new Time(System.currentTimeMillis());
        quizHistory.setStartTime(startTime);
        assertEquals(startTime, quizHistory.getStartTime());
    }

    @Test
    public void setEndTimeTest() {
        Time endTime = new Time(System.currentTimeMillis() + 10000);
        quizHistory.setEndTime(endTime);
        assertEquals(endTime, quizHistory.getEndTime());
    }

    @Test
    public void setElapsedTimeTest() {
        quizHistory.setElapsedTime(15000);
        assertEquals(15000, quizHistory.getElapsedTime());
    }
}