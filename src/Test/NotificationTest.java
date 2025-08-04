package Test;

import Models.Enums.NotificationType;
import Models.Notification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotificationTest {

    private Notification notification;

    @Before
    public void setup() {
        notification = new Notification(
                1,
                "Nini",
                "Marta",
                NotificationType.CHALLENGE,
                "http://quiz.com/abc123",
                100,
                0,
                " "
        );
    }

    @Test
    public void getUsernameFromTest() {
        String expected = "Nini";
        assertEquals(expected, notification.getUsernameFrom());
    }

    @Test
    public void getUsernameToTest() {
        String expected = "Marta";
        assertEquals(expected, notification.getUsernameTo());
    }

    @Test
    public void getNotificationIdTest() {
        int expected = 1;
        assertEquals(expected, notification.getNotificationId());
    }

    @Test
    public void setNotificationIdTest() {
        notification.setNotificationId(2);
        int expected = 2;
        assertEquals(expected, notification.getNotificationId());
    }

    @Test
    public void setUsernameFromTest() {
        notification.setUsernameFrom("Ninia");
        String expected = "Ninia";
        assertEquals(expected, notification.getUsernameFrom());
    }

    @Test
    public void setUsernameToTest() {
        notification.setUsernameTo("Marta Meshve");
        String expected = "Marta Meshve";
        assertEquals(expected, notification.getUsernameTo());
    }

    @Test
    public void getNotificationTypeTest() {
        NotificationType expected = NotificationType.CHALLENGE;
        assertEquals(expected, notification.getNotificationType());
    }

    @Test
    public void setNotificationTypeTest() {
        notification.setNotificationType(NotificationType.NOTE);
        NotificationType expected = NotificationType.NOTE;
        assertEquals(expected, notification.getNotificationType());
    }

    @Test
    public void getQuizLinkTest() {
        String expected = "http://quiz.com/abc123";
        assertEquals(expected, notification.getQuizLink());
    }

    @Test
    public void setQuizLinkTest() {
        notification.setQuizLink("http://quiz.com/quiz");
        String expected = "http://quiz.com/quiz";
        assertEquals(expected, notification.getQuizLink());
    }

    @Test
    public void getResquestIdTest() {
        int expected = 0;
        assertEquals(expected, notification.getRequestId());
    }

    @Test
    public void setResquestIdTest() {
        notification.setRequestId(10);
        int expected = 10;
        assertEquals(expected, notification.getRequestId());
    }

    @Test
    public void getMessageTest() {
        String expected = " ";
        assertEquals(expected, notification.getMessage());
    }

    @Test
    public void getHighScoreTest() {
        int expected = 100;
        assertEquals(expected, notification.getHighScore());
    }

    @Test
    public void setHighScoreTest() {
        notification.setHighScore(51);
        int expected = 51;
        assertEquals(expected, notification.getHighScore());
    }

}
