package Controllers.managers;

import Dao.NotificationDao;
import Models.Notification;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.List;

public class NotificationManager {
    public static final String ATTRIBUTE_NAME = "NotificationManager";

    private final NotificationDao notificationDao;
    private final SQLConnector sqlConnector;


    public NotificationManager() {
        sqlConnector = new SQLConnector();
        notificationDao = new NotificationDao(sqlConnector.dataSource);
    }


    public void createNotification(Notification notification) throws SQLException {
        notificationDao.CreateNotification(notification);
    }


    public Notification getNotificationById(int notificationId) throws SQLException {
        return notificationDao.getNotificationById(notificationId);
    }


    public void updateNotification(Notification notification) throws SQLException {
        notificationDao.updateNotification(notification);
    }


    public void deleteNotification(int notificationId) throws SQLException {
        notificationDao.deleteNotification(notificationId);
    }


    public List<Notification> getAllNotifications() throws SQLException {
        return notificationDao.getAllNotifications();
    }


    public List<Notification> getNotificationsToUser(String username) throws SQLException {
        return notificationDao.getAllNotificationsTo(username);
    }


    public List<Notification> getNotificationsFromUser(String username) throws SQLException {
        return notificationDao.getAllNotificationsFrom(username);
    }
}