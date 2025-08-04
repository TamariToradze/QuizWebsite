package Test;

import Models.LeaderboardEntry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardEntryTest {

    private LeaderboardEntry entry;

    @Before
    public void setup() {
        entry = new LeaderboardEntry("Nini", 100, 5000);
    }

    @Test
    public void getUsernameTest() {
        String expected = "Nini";
        assertEquals(expected, entry.getUsername());
    }

    @Test
    public void getScoreTest() {
        int expected = 100;
        assertEquals(expected, entry.getScore());
    }

    @Test
    public void getElapsedTimeTest() {
        int expected = 5000;
        assertEquals(expected, entry.getElapsedTime());
    }

    @Test
    public void setUsernameTest() {
        entry.setUsername("Marta");
        String expected = "Marta";
        assertEquals(expected, entry.getUsername());
    }

    @Test
    public void setScoreTest() {
        entry.setScore(99);
        int expected = 99;
        assertEquals(expected, entry.getScore());
    }

    @Test
    public void setElapsedTimeTest() {
        entry.setElapsedTime(10000);
        int expected = 10000;
        assertEquals(expected, entry.getElapsedTime());
    }
}