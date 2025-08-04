package Controllers.managers;

import Dao.QuizDao;
import Models.Question;
import Models.Quiz;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class QuizManager {
    public static final String ATTRIBUTE_NAME = "QuizManager";
    private final QuizDao quizDataAccess;

    public QuizManager( ) {
        SQLConnector sqlConnector = new SQLConnector();
        this.quizDataAccess = new QuizDao(sqlConnector.dataSource);
    }
    /**
     * Retrieves a quiz by its ID.
     *
     * @param quizId the ID of the quiz
     * @return the quiz with the specified ID
     */
    public Quiz getQuiz(int quizId)  {
        Quiz quiz = quizDataAccess.readQuiz(quizId);
        if(quiz.isRandomizeQuestions()) Collections.shuffle(quiz.getQuestionIds());
        return quiz;
    }

    /**
     * Retrieves all quizzes.
     *
     * @return a list of all quizzes
     */
    public List<Quiz> getAllQuizzes(){
        return quizDataAccess.getAllQuizzes();
    }

    /**
     * Adds a new quiz.
     *
     * @param quiz the quiz to add
     */
    public int addQuiz(Quiz quiz){
        return quizDataAccess.createQuiz(quiz);
    }

    /**
     * Updates an existing quiz.
     *
     * @param quiz the quiz with updated information
     */
    public void updateQuiz(Quiz quiz) {
        quizDataAccess.updateQuiz(quiz);
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param quizId the ID of the quiz to delete
     */
    public void deleteQuiz(int quizId) {
        quizDataAccess.deleteQuiz(quizId);
    }


    public List<Question> getAllQuestionsByQuiz(int quizId) {
        return quizDataAccess.getAllQuestionsByQuizId(quizId);
    }

    public List<Quiz> getNewlyAddedQuizzes() throws SQLException {
        return quizDataAccess.getAllQuizzesByCreationTime();
    }

    public List<Quiz> getQuizzesByUser(String username){
        return quizDataAccess.getAllQuizzesByUser(username);
    }
}