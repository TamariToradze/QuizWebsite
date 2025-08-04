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
