package Models;

import Dao.FriendsDao;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class FriendRequest {

    private int friendRequestId;
    private final String usernameFrom;
    private final String usernameTo;
    private boolean isAccepted;

    public FriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.isAccepted = false;
    }

    public FriendRequest(int friendRequestId, String usernameFrom, String usernameTo, Boolean isAccepted) throws SQLException {
        this.friendRequestId = friendRequestId;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.isAccepted = isAccepted;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setFriendRequestStatus(boolean isAccepted, BasicDataSource dataSource) throws SQLException {
        this.isAccepted = isAccepted;
        updateFriendshipStatus(isAccepted, dataSource);
    }

    public void sendFriendRequest(BasicDataSource dataSource) throws SQLException {
        FriendsDao friendsDAO = new FriendsDao(dataSource);
        friendsDAO.addFriend(this);
    }

    private void updateFriendshipStatus(Boolean isAccepted, BasicDataSource dataSource) throws SQLException {
        FriendsDao friendsDAO = new FriendsDao(dataSource);
        friendsDAO.updateFriend(usernameFrom, usernameTo, isAccepted);
    }

    public int getFriendRequestId() {
        return friendRequestId;
    }
}