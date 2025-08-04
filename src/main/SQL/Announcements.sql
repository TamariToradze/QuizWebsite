CREATE TABLE Announcements (
                               announcementId INT AUTO_INCREMENT PRIMARY KEY,
                               username VARCHAR(255) NOT NULL,
                               message TEXT NOT NULL,
                               announcementTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (username) REFERENCES Accounts(username) ON DELETE CASCADE
);