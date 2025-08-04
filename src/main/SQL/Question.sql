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