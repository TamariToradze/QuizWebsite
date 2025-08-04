CREATE TABLE IF NOT EXISTS Notifications (
    notificationId INT NOT NULL AUTO_INCREMENT,
    usernameFrom VARCHAR(255) NOT NULL,
    usernameTO VARCHAR(255) NOT NULL,
    notificationType INT NOT NULL,
    quizLink VARCHAR(255) DEFAULT NULL,
    highScore INT DEFAULT NULL,
    friendRequestId INT DEFAULT NULL,
    message TEXT,
    PRIMARY KEY (notificationId),
    FOREIGN KEY (usernameFrom) REFERENCES Accounts(userName) ON DELETE CASCADE,
    FOREIGN KEY (usernameTo) REFERENCES Accounts(userName) ON DELETE CASCADE,
    FOREIGN KEY (friendRequestId) REFERENCES Friends(friendRequestId) ON DELETE CASCADE
    );