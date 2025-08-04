package Dao;

import Models.FriendRequest;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendsDao {
    private BasicDataSource dataSource;

    public FriendsDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addFriend(FriendRequest friendRequest) throws SQLException{
        if(friendRequest != null){
            String sql = "INSERT INTO Friends (usernameFrom, usernameTo, isAccepted) VALUES (?,?,?)";
            try(Connection connection = dataSource.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, friendRequest.getUsernameFrom());
                preparedStatement.setString(2, friendRequest.getUsernameTo());
                preparedStatement.setBoolean(3, false);
                preparedStatement.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updateFriend(String usernameFrom, String usernameTo, Boolean isAccepted) throws SQLException{
        String sql = "UPDATE Friends SET Friends.isAccepted = ? WHERE usernameFrom = ? AND usernameTo = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, isAccepted);
            preparedStatement.setString(2, usernameFrom);
            preparedStatement.setString(3, usernameTo);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public FriendRequest getFriendRequest(String usernameFrom, String usernameTo) throws SQLException{
        String sql =  "SELECT * FROM Friends WHERE usernameFrom = ? AND usernameTo = ?";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usernameFrom);
            preparedStatement.setString(2, usernameTo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new FriendRequest(resultSet.getInt("friendRequestId"), resultSet.getString("usernameFrom"), resultSet.getString("usernameTo"), resultSet.getBoolean("isAccepted"));
            }else{
                return null;
            }
        }
    }

    public ArrayList<FriendRequest> allFriendRequestsFrom(String usernameFrom) throws SQLException{
        ArrayList<FriendRequest> friendRequestsList = new ArrayList<>();
        String sql = "SELECT * FROM Friends WHERE usernameFrom = ?";
        return getFriendRequests(usernameFrom, friendRequestsList, sql);
    }

    public ArrayList<FriendRequest> allFriendRequestsTo(String usernameTo) throws SQLException{
        ArrayList<FriendRequest> friendRequestsList = new ArrayList<>();
        String sql = "SELECT * FROM Friends WHERE usernameTo = ?";
        return getFriendRequests(usernameTo, friendRequestsList, sql);
    }

    private ArrayList<FriendRequest> getFriendRequests(String username, ArrayList<FriendRequest> friendRequests, String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friendRequests.add(getFriendRequest(resultSet.getString("usernameFrom"),resultSet.getString("usernameTo")));
            }
            return friendRequests;
        }
    }

    public int friendRequestID(String usernameFrom, String usernameTo) throws SQLException{
        String sql = "SELECT friendRequestId FROM Friends WHERE usernameFrom = ? AND usernameTo = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usernameFrom);
            preparedStatement.setString(2, usernameTo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("friendRequestId");
            }else{
                throw new SQLException("Friend Request ID not found");
            }
        }
    }

    public List<String> acceptedFriendRequests(String username) throws SQLException{
        List<String> friends = new  ArrayList<>();
        String sql = "SELECT usernameFrom, usernameTo FROM Friends WHERE (usernameFrom = ? OR usernameTo = ?) AND isAccepted = 1";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String usernameFrom = resultSet.getString("usernameFrom");
                    String usernameTo = resultSet.getString("usernameTo");
                    if(!usernameFrom.equals(username)){
                        friends.add(usernameFrom);
                    }else{
                        friends.add(usernameTo);
                    }
                }
            }
        return friends;
    }
}