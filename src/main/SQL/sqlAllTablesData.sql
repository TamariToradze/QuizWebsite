-- Create the talala database
CREATE DATABASE IF NOT EXISTS tralala;

-- Use the tralala database
USE tralala;

-- Create the Accounts table
CREATE TABLE IF NOT EXISTS Accounts (
                                        userId INT AUTO_INCREMENT PRIMARY KEY,
                                        userName VARCHAR(50) NOT NULL UNIQUE,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    password VARCHAR(255),
    email VARCHAR(100) UNIQUE,
    imageUrl VARCHAR(255),
    salt VARCHAR(16),
    achievementIds TEXT not null,
    isAdmin BOOLEAN
    );

CREATE TABLE IF NOT EXISTS Friends (
                                       friendRequestId INT NOT NULL AUTO_INCREMENT,
                                       usernameFrom VARCHAR(255) NOT NULL,
    usernameTo VARCHAR(255) NOT NULL,
    isAccepted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (friendRequestId),
    FOREIGN KEY (usernameFrom) REFERENCES Accounts(userName) ON DELETE CASCADE,
    FOREIGN KEY (usernameTo) REFERENCES Accounts(userName) ON DELETE CASCADE,
    UNIQUE (usernameFrom, usernameTo)
    );

CREATE TABLE Announcements (
                               announcementId INT AUTO_INCREMENT PRIMARY KEY,
                               username VARCHAR(255) NOT NULL,
                               message TEXT NOT NULL,
                               announcementTime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (username) REFERENCES Accounts(username) ON DELETE CASCADE
);


CREATE TABLE Quiz (
                      quizID INT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(255) NOT NULL,
                      quizName VARCHAR(255) NOT NULL,
                      quizDescription TEXT,
                      quizScore INT DEFAULT 0,
                      questionIds TEXT,
                      isSinglePage BOOLEAN DEFAULT FALSE,
                      randomizeQuestions BOOLEAN DEFAULT FALSE,
                      immediateFeedback BOOLEAN DEFAULT FALSE,
                      createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (quizID),
                      FOREIGN KEY (username) references Accounts(username) ON DELETE CASCADE
);

CREATE TABLE Question (
                          questionId INT NOT NULL AUTO_INCREMENT,
                          quizId INT NOT NULL,
                          questionType INT NOT NULL,
                          questionText TEXT NOT NULL,
                          singleQuestionAnswer TEXT,
                          alternativeAnswers TEXT,
                          multipleChoiceAnswers TEXT,
                          multipleChoiceCorrectIndexes TEXT,
                          questionImage TEXT,
                          multipleAnswerFields TEXT,
                          matchingPairs TEXT,
                          PRIMARY KEY (questionId),
                          FOREIGN KEY (quizId) REFERENCES Quiz(quizID) ON DELETE CASCADE
);



CREATE TABLE Achievement (
                             achievementId INT NOT NULL AUTO_INCREMENT,
                             achievementName VARCHAR(255) NOT NULL,
                             achievementUrl VARCHAR(255),
                             achievementDescription TEXT,
                             PRIMARY KEY (achievementId)
);



CREATE TABLE QuizHistory (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             quizId INT NOT NULL,
                             username VARCHAR(255) NOT NULL,
                             quizScore INT DEFAULT 0,
                             startTime TIME,
                             endTime TIME,
                             endDate DATE,
                             elapsedTime BIGINT DEFAULT 0,
                             FOREIGN KEY (quizId) REFERENCES Quiz(quizId) ON DELETE CASCADE,
                             FOREIGN KEY (username) REFERENCES Accounts(username) ON DELETE CASCADE
);



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

