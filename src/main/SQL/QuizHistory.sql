CREATE TABLE QuizHistoryData (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 quizID INT NOT NULL,
                                 userName VARCHAR(255) NOT NULL,
                                 quizScore INT DEFAULT 0,
                                 startTime TIME,
                                 endTime TIME,
                                 endDate DATE,
                                 elapsedTime BIGINT DEFAULT 0,
                                 FOREIGN KEY (quizID) REFERENCES Quiz(quizID) ON DELETE CASCADE,
                                 FOREIGN KEY (userName) REFERENCES Accounts(userName) ON DELETE CASCADE
);