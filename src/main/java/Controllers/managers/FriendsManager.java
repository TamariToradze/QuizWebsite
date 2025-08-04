package Controllers.managers;

import Dao.FriendsDao;
import Models.FriendRequest;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendsManager {
    public static final String ATTRIBUTE_NAME = "FriendManager";
    private FriendsDao friendsDao;
    private final SQLConnector sqlConnector;

    public FriendsManager() {
        sqlConnector = new SQLConnector();
        friendsDao = new FriendsDao(sqlConnector.dataSource);
    }

    public void sendFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        FriendRequest friendRequest = new FriendRequest(usernameFrom, usernameTo);
        friendsDao.addFriend(friendRequest);
    }

    public void acceptFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        friendsDao.updateFriend(usernameFrom, usernameTo, true);
    }

    public void rejectFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        friendsDao.updateFriend(usernameFrom, usernameTo, false);
    }

    public FriendRequest viewFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        return friendsDao.getFriendRequest(usernameFrom, usernameTo);
    }

    public ArrayList<FriendRequest> getAllFriendRequestsFrom(String usernameFrom) throws SQLException {
        return friendsDao.allFriendRequestsFrom(usernameFrom);
    }

    public ArrayList<FriendRequest> getAllFriendRequestsTo(String usernameTo) throws SQLException {
        return friendsDao.allFriendRequestsTo(usernameTo);
    }

    public int getFriendRequestID(String usernameFrom, String usernameTo) throws SQLException {
        return friendsDao.friendRequestID(usernameFrom, usernameTo);
    }

    public ArrayList<String> getAcceptedFriendRequests(String usernameFrom) throws SQLException {
        return (ArrayList<String>) friendsDao.acceptedFriendRequests(usernameFrom);
    }
}