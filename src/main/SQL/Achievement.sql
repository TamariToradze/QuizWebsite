CREATE TABLE Achievement (
                             achievementId INT NOT NULL AUTO_INCREMENT,
                             achievementName VARCHAR(255) NOT NULL,
                             achievementUrl VARCHAR(255),
                             achievementDescription TEXT,
                             PRIMARY KEY (achievementId)
);

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (1, 'Amateur Author', 'https://img.icons8.com/?size=100&id=PDVbwX9qe2CX&format=png&color=000000', 'The user created a quiz');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (2, 'Prolific Author', 'https://img.icons8.com/?size=100&id=68706&format=png&color=000000', 'The user created five quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (3, 'Prodigious Author', 'https://img.icons8.com/?size=100&id=yrPIS24okymU&format=png&color=000000', 'The user created ten quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (4, 'Quiz Machine', 'https://img.icons8.com/?size=100&id=114898&format=png&color=000000', 'The user took five quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (5, 'I am the Greatest', 'https://img.icons8.com/?size=100&id=80747&format=png&color=000000', 'The user had the highest score on a quiz. ');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (6, 'Practice Makes Perfect', 'https://img.icons8.com/?size=100&id=114206&format=png&color=000000', 'The user took ten quizzes.');