package Dao;

import Models.Notification;
import Models.Enums.NotificationType;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao {
    private final BasicDataSource dataSource;

    public NotificationDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void CreateNotification(Notification notification) {
        String sql = "INSERT INTO Notifications (usernameFrom, usernameTo, notificationType, quizLink, highScore,friendRequestId, message) VALUES (?, ?, ? , ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            prepareNotificationStatement(notification, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setStatements(PreparedStatement preparedStatement, Notification notification) throws SQLException {
        preparedStatement.setString(1, notification.getUsernameFrom());
        preparedStatement.setString(2, notification.getUsernameTo());
        preparedStatement.setInt(3, notification.getNotificationType().ordinal());

        preparedStatement.setNull(4, java.sql.Types.VARCHAR);
        preparedStatement.setNull(5, Types.INTEGER);
        preparedStatement.setNull(6, java.sql.Types.INTEGER);
        preparedStatement.setNull(7, java.sql.Types.VARCHAR);
    }

    private void prepareNotificationStatement(Notification notification, PreparedStatement preparedStatement) throws SQLException {
        setStatements(preparedStatement, notification);

        switch (notification.getNotificationType()) {
            case CHALLENGE:
                preparedStatement.setString(4, notification.getQuizLink());
                preparedStatement.setInt(5, notification.getHighScore());
                break;
            case FRIEND_REQUEST:
                preparedStatement.setInt(6, notification.getRequestId());
                break;
            case NOTE:
                preparedStatement.setString(7, notification.getMessage());
                break;
            default:
                throw new IllegalArgumentException("Unknown notification type: " + notification.getNotificationType());
        }
    }

    public Notification getNotificationById(int notificationId) throws SQLException {
        String sql = "SELECT * FROM Notifications WHERE notificationId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, notificationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Notification(
                            resultSet.getInt("notificationId"),
                            resultSet.getString("usernameFrom"),
                            resultSet.getString("usernameTo"),
                            NotificationType.values()[resultSet.getInt("notificationType")],
                            resultSet.getString("quizLink"),
                            resultSet.getInt("highScore"),
                            resultSet.getInt("friendRequestId"),
                            resultSet.getString("message")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void updateNotification(Notification notification) throws SQLException {
        String sql = "UPDATE Notifications SET usernameFrom = ?, usernameTo = ?, notificationType = ?, quizLink = ?, highScore = ? ,friendRequestId = ?, message = ? WHERE notificationId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            prepareNotificationStatement(notification, preparedStatement);
            preparedStatement.setInt(8, notification.getNotificationId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNotification(int notificationId) throws SQLException {
        String sql = "DELETE FROM Notifications WHERE notificationId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, notificationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getAllNotifications() throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                notifications.add(new Notification(
                        resultSet.getInt("notificationId"),
                        resultSet.getString("usernameFrom"),
                        resultSet.getString("usernameTo"),
                        NotificationType.values()[resultSet.getInt("notificationType")],
                        resultSet.getString("quizLink"),
                        resultSet.getInt("highScore"),
                        resultSet.getInt("friendRequestId"),
                        resultSet.getString("message")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public ArrayList<Notification> getAllNotificationsFrom(String usernameFrom) throws SQLException {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE usernameFrom = ?";
        return getNotifications(usernameFrom, notifications, sql);
    }

    public ArrayList<Notification> getAllNotificationsTo(String usernameTo) throws SQLException {
        ArrayList<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE usernameTo = ?";
        return getNotifications(usernameTo, notifications, sql);
    }

    private ArrayList<Notification> getNotifications(String username, ArrayList<Notification> notifications, String query) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                notifications.add(new Notification(
                        resultSet.getInt("notificationId"),
                        resultSet.getString("usernameFrom"),
                        resultSet.getString("usernameTo"),
                        NotificationType.values()[resultSet.getInt("notificationType")],
                        resultSet.getString("quizLink"),
                        resultSet.getInt("highScore"),
                        resultSet.getInt("friendRequestId"),
                        resultSet.getString("message")
                ));
            }
            return notifications;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}