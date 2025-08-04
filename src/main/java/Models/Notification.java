package Models;

import Models.Enums.NotificationType;

public class Notification {
    private int notificationId;
    private String usernameFrom;
    private String usernameTo;
    private NotificationType notificationType;
    private String quizLink;
    private int highScore;
    private int requestId;
    private String message;
    private FriendRequest friendRequest;

    public Notification(int notificationId, String usernameFrom, String usernameTo,
                        NotificationType notificationType, String quizLink, int highScore, int requestId, String message) {
        this.notificationId = notificationId;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.notificationType = notificationType;
        this.quizLink = quizLink;
        this.requestId = requestId;
        this.message = message;
        this.highScore = highScore;
    }

    public Notification(String usernameFrom, String usernameTo,
                        NotificationType notificationType, String quizLink, int highScore, int requestId, String message) {
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.notificationType = notificationType;
        this.quizLink = quizLink;
        this.requestId = requestId;
        this.message = message;
        this.highScore = highScore;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getQuizLink() {
        return quizLink;
    }

    public void setQuizLink(String quizLink) {
        this.quizLink = quizLink;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
