-- Insert achievements
INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (1, 'Rookie Quiz Creator', 'https://img.icons8.com/?size=100&id=PDVbwX9qe2CX&format=png&color=000000', 'The user created a quiz');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (2, 'Creative Contributor', 'https://img.icons8.com/?size=100&id=68706&format=png&color=000000', 'The user created five quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (3, 'Master Quiz Maker', 'https://img.icons8.com/?size=100&id=yrPIS24okymU&format=png&color=000000', 'The user created ten quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (4, 'Quiz Enthusiast', 'https://img.icons8.com/?size=100&id=114898&format=png&color=000000', 'The user took five quizzes');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (5, 'Top Scorer', 'https://img.icons8.com/?size=100&id=80747&format=png&color=000000', 'The user had the highest score on a quiz.');

INSERT INTO Achievement (achievementId, achievementName, achievementUrl, achievementDescription)
VALUES (6, 'Quiz Veteran', 'https://img.icons8.com/?size=100&id=114206&format=png&color=000000', 'The user took ten quizzes.');

-- Insert quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Tech Trivia', 'A quiz to test your basic tech knowledge.', 0, '[5, 6, 7, 8]', FALSE, FALSE, FALSE,
        CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 0, 'What does CPU stand for?', 'Central Processing Unit', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 5, 'Which of the following are operating systems?', NULL, NULL, '[\"Windows\", \"Linux\", \"Google\"]',
        '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 6, 'Match the devices with their types.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Printer":"Peripheral","Smartphone":"Mobile"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (1, 5, 'Select all networking devices.', NULL, NULL, '[\"Router\", \"Mouse\", \"Switch\"]', '[0, 2]', NULL, NULL,
        NULL);

-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Ancient History Quiz', 'Test your knowledge of ancient civilizations and famous historical events.', 0, '[5, 6, 7, 8]', FALSE, FALSE, FALSE,
        CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 0, 'Who built the Great Pyramid of Giza?', 'Pharaoh Khufu', NULL, NULL, NULL, NULL, NULL,
        NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 2, 'Which of the following were Roman Emperors?', NULL, NULL,
        '[\"Julius Caesar\", \"Augustus\", \"Alexander the Great\"]', '[1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 6, 'Match these rulers with their empires.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Hammurabi":"Babylon","Cleopatra":"Egypt"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (2, 5, 'Select all that were ancient wonders.', NULL, NULL,
        '[\"Hanging Gardens\", \"Statue of Zeus\", \"Eiffel Tower\"]', '[0, 1]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Physics & Chemistry Quiz', 'Challenge your science fundamentals.', 0, '[9, 10, 11, 12]',
        FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 0, 'What is the speed of light in vacuum?', '299,792 km/s', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 5, 'Which of the following are noble gases?', NULL, NULL,
        '[\"Helium\", \"Neon\", \"Carbon\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 6, 'Match the elements to their symbols.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Sodium":"Na","Potassium":"K"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (3, 5, 'Select all planets with rings.', NULL, NULL,
        '[\"Saturn\", \"Uranus\", \"Earth\"]', '[0, 1]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Famous Books Quiz', 'Test your knowledge of books and their authors.', 0, '[13, 14, 15, 16]',
        FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 0, 'Who wrote "To Kill a Mockingbird"?', 'Harper Lee', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 5, 'Which of these are novels by Charles Dickens?', NULL, NULL,
        '[\"Oliver Twist\", \"Great Expectations\", \"Crime and Punishment\"]', '[0, 1]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 6, 'Match the authors to their books.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Leo Tolstoy":"War and Peace","F. Scott Fitzgerald":"The Great Gatsby"}');

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (4, 5, 'Select all poetry works.', NULL, NULL,
        '[\"The Raven\", \"Ode to a Nightingale\", \"Moby Dick\"]', '[0, 1]', NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Sports Legends Quiz', 'How well do you know the greatest athletes and teams?', 0, '[17, 18, 19, 20]', FALSE,
        FALSE, FALSE, CURRENT_TIMESTAMP);

-- Insert questions for the quiz
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Who won the FIFA World Cup in 2014?', 'Germany', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Who holds the NBA record for most career points?', 'LeBron James', NULL, NULL, NULL,
        NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Who won the most Formula 1 World Championships?', 'Lewis Hamilton', NULL, NULL, NULL, NULL, NULL,
        NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (5, 0, 'Which country hosted the 2016 Summer Olympics?', 'Brazil',
        NULL, NULL, NULL, NULL, NULL, NULL);


-- Insert a quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Fun Random Quiz', 'A mix of sports, science, and general trivia.', 0, '[21, 22, 23, 24, 25, 26, 27]', FALSE, FALSE, FALSE, CURRENT_TIMESTAMP);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 0, 'Who won the UEFA Euro in 2016?', 'Portugal', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 1, 'The scientist who developed the theory of gravity is ______', 'Newton', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 2, 'Score of the 2022 FIFA Final?', NULL, NULL, '[\" 3:3 \", \" 2:1 \", \" 4:2 \"]', '[0]', NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 3, 'Who is in the image?', 'Albert Einstein', NULL, NULL, NULL,
        'https://upload.wikimedia.org/wikipedia/commons/d/d3/Albert_Einstein_Head.jpg',
        NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 4, 'Select all prime numbers below:', NULL, NULL, NULL, NULL, NULL,
        '[\" 11 \", \" 22 \", \" 31 \", \" 44 \", \" 53 \", \" 66 \", \" 71 \", \" 82 \", \" 97 \", \" 110 \"]', NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 5, 'Mark all correct scientific facts:', NULL, NULL, '[\"Water boils at 100°C\", \"The moon is a planet\", \"The Earth orbits the Sun\"]', '[0, 2]',
        NULL, NULL, NULL);

INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (6, 6, 'Match the inventors with their inventions.', NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        '{"Thomas Edison": "Light Bulb", "Alexander Graham Bell": "Telephone"}');

-- ------------------------- new quiz
INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, randomizeQuestions,
                  immediateFeedback, createTime)
VALUES ('quizMaster', 'Geography & Science Quiz', 'Test your basic knowledge of geography and science', 0, '[28, 29, 30, 31, 32, 33, 34]', TRUE, FALSE, FALSE, CURRENT_TIMESTAMP);

-- Question 1: Single Question Answer
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 0, 'What is the capital of Italy?', 'Rome', NULL, NULL, NULL, NULL, NULL, NULL);

-- Question 2: Fill-in-the-Blank
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 1, 'The powerhouse of the cell is the ______', 'Mitochondria', NULL, NULL, NULL, NULL, NULL, NULL);

-- Question 3: Multiple Choice Single Answer
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 2, 'Which planet is closest to the Sun?', NULL, NULL, '["Earth", "Mercury", "Venus"]', '[1]', NULL, NULL, NULL);

-- Question 4: Image Question
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 3, 'Identify the animal shown in the image', 'Panda', NULL, NULL, NULL,
        'https://upload.wikimedia.org/wikipedia/commons/0/0f/Grosser_Panda.JPG', NULL, NULL);

-- Question 5: Multiple Answer Fields
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 4, 'List the first five Fibonacci numbers', NULL, NULL, NULL, NULL, NULL,
        '["0", "1", "1", "2", "3"]', NULL);

-- Question 6: Multiple Choice Multiple Answers
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 5, 'Select the colors of the rainbow', NULL, NULL, '["Red", "Orange", "Pink", "Blue", "Indigo"]', '[0, 1, 3, 4]', NULL, NULL, NULL);

-- Question 7: Matching Pairs
INSERT INTO Question (quizId, questionType, questionText, singleQuestionAnswer, alternativeAnswers,
                      multipleChoiceAnswers, multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields,
                      matchingPairs)
VALUES (7, 6, 'Match the following mountains with their continents.', NULL, NULL, NULL, NULL, NULL, NULL,
        '{"Everest": "Asia", "Kilimanjaro": "Africa", "Elbrus": "Europe"}');
