package Test;

import Models.FriendRequest;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.*;

public class FriendRequestTest {

    private FriendRequest friendRequest;

    @Before
    public void setUp() throws SQLException {
        friendRequest = new FriendRequest(
                1,
                "Nini",
                "Marta",
                true
        );
    }

    @Test
    public void getUsernameFromTest() {
        String expected = "Nini";
        assertEquals(expected, friendRequest.getUsernameFrom());
    }

    @Test
    public void getUsernameToTest() {
        String expected = "Marta";
        assertEquals(expected, friendRequest.getUsernameTo());
    }

    @Test
    public void getIsAcceptedTest() {
        boolean expected = true;
        assertEquals(expected, friendRequest.getIsAccepted());
    }

    @Test
    public void getFriendRequestIdTest() {
        int expected = 1;
        assertEquals(expected, friendRequest.getFriendRequestId());
    }
}
