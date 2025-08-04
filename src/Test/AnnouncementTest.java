package Test;

import Models.Announcement;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class AnnouncementTest {

    private Announcement announcement;
    private Timestamp initialTimestamp;

    @Before
    public void setUp() {
        initialTimestamp = new Timestamp(System.currentTimeMillis());
        announcement = new Announcement(
                1,
                "example",
                "This is a test announcement",
                initialTimestamp
        );
    }

    @Test
    public void getUsernameTest() {
        String expected = "example";
        assertEquals(expected, announcement.getUsername());
    }

    @Test
    public void setUsernameTest() {
        announcement.setUsername("announcement");
        String expected = "announcement";
        assertEquals(expected, announcement.getUsername());
    }

    @Test
    public void getAnnouncementTimeTest() {
        assertEquals(initialTimestamp, announcement.getAnnouncementTime());
    }

    @Test
    public void setAnnouncementTimeTest() {
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis() + 10000);
        announcement.setAnnouncementTime(newTimestamp);
        assertEquals(newTimestamp, announcement.getAnnouncementTime());
    }

    @Test
    public void getMessageTest() {
        String expected = "This is a test announcement";
        assertEquals(expected, announcement.getMessage());
    }

    @Test
    public void setMessageTest() {
        announcement.setMessage("This is a second test announcement");
        String expected = "This is a second test announcement";
        assertEquals(expected, announcement.getMessage());
    }

    @Test
    public void getAnnouncementIdTest() {
        int expected = 1;
        assertEquals(expected, announcement.getAnnouncementId());
    }

    @Test
    public void testSetAchievementDescription() {
        announcement.setAnnouncementId(2);
        int expected = 2;
        assertEquals(expected, announcement.getAnnouncementId());
    }
}