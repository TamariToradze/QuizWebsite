package Test;

import Models.Achievement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AchievementTest {

    private Achievement achievement;

    @Before
    public void setUp() {
        achievement = new Achievement(
                1,
                "Mastered",
                "http://example.com/achievements/Mastered",
                "awarded for taking the highest scores"
        );
    }

    @Test
    public void getAchievementIdTest() {
        int expected = 1;
        assertEquals(expected, achievement.getAchievementId());
    }

    @Test
    public void setAchievementIdTest() {
        achievement.setAchievementId(2);
        int expected = 2;
        assertEquals(expected, achievement.getAchievementId());
    }

    @Test
    public void getAchievementNameTest() {
        String expected = "Mastered";
        assertEquals(expected, achievement.getAchievementName());
    }

    @Test
    public void setAchievementNameTest() {
        achievement.setAchievementName("Achievement");
        String expected = "Achievement";
        assertEquals(expected, achievement.getAchievementName());
    }

    @Test
    public void getAchievementUrlTest() {
        String expected = "http://example.com/achievements/Mastered";
        assertEquals(expected, achievement.getAchievementUrl());
    }

    @Test
    public void setAchievementUrlTest() {
        achievement.setAchievementUrl("http://example.com/achievements/Achievement");
        String expected = "http://example.com/achievements/Achievement";
        assertEquals(expected, achievement.getAchievementUrl());
    }

    @Test
    public void getAchievementDescriptionTest() {
        String expected = "awarded for taking the highest scores";
        assertEquals(expected, achievement.getAchievementDescription());
    }

    @Test
    public void setAchievementDescriptionTest() {
        achievement.setAchievementDescription("Awarded for creating a quiz");
        String expected = "Awarded for creating a quiz";
        assertEquals(expected, achievement.getAchievementDescription());
    }
}