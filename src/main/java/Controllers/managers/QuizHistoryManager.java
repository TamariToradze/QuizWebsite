package Controllers.managers;

import Dao.QuizHistoryDao;
import Models.Quiz;
import Models.QuizHistory;
import Dao.QuizHistoryDao.Pair;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.List;

/**
 * Manager class that delegates quiz history operations to QuizHistoryDAO.
 * Provides higher-level methods for controllers to interact with quiz history data.
 */
public class QuizHistoryManager {
    // DAO instance responsible for direct database interactions
    private final QuizHistoryDao quizHistoryDAO;

    // Attribute name for ServletContext lookup
    public static final String ATTRIBUTE_NAME = "QuizHistoryManager";

    /**
     * Constructor initializes the DAO using a SQLConnector.
     */
    public QuizHistoryManager() {
        SQLConnector connector = new SQLConnector();
        this.quizHistoryDAO = new QuizHistoryDao(connector.dataSource);
    }

    /**
     * Creates a new quiz history record in the database.
     */
    public void createQuizHistory(QuizHistory record) throws SQLException {
        quizHistoryDAO.createQuizHistory(record);
    }

    /**
     * Retrieves a quiz history entry by quiz ID.
     */
    public QuizHistory getQuizHistoryByQuizId(int quizId) throws SQLException {
        return quizHistoryDAO.getQuizHistoryByQuizId(quizId);
    }

    /**
     * Retrieves the most recent quiz history for a given username.
     */
    public QuizHistory getQuizHistoryByUsername(String username) throws SQLException {
        return quizHistoryDAO.getQuizHistoryByUsername(username);
    }

    /**
     * Updates an existing quiz history record.
     */
    public void updateQuizHistory(QuizHistory record) throws SQLException {
        quizHistoryDAO.updateQuizHistory(record);
    }

    /**
     * Deletes a specific quiz history record by quiz ID and username.
     */
    public void deleteQuizHistory(int quizId, String username) throws SQLException {
        quizHistoryDAO.deleteQuizHistory(quizId, username);
    }

    /**
     * Retrieves all quiz history records in the system.
     */
    public List<QuizHistory> getAllQuizHistories() throws SQLException {
        return quizHistoryDAO.getAllQuizHistories();
    }

    /**
     * Retrieves all quiz history for a specific user.
     */
    public List<QuizHistory> getAllQuizHistoryByUsername(String username) throws SQLException {
        return quizHistoryDAO.getAllQuizHistoryByUsername(username);
    }

    /**
     * Retrieves quizzes ranked by popularity (number of times taken).
     */
    public List<Quiz> getPopularQuizzes() throws SQLException {
        return quizHistoryDAO.getAllQuizzesByPopularity();
    }

    /**
     * Retrieves quizzes taken by a user, ordered by most recent start time.
     */
    public List<Quiz> getQuizzesForUserByTakingTime(String username) throws SQLException {
        return quizHistoryDAO.getQuizzesForUserByTakingTime(username);
    }

    /**
     * Retrieves recent quiz history activities for a user's accepted friends.
     */
    public List<QuizHistory> getUsersFriendsRecentActivities(String username) throws SQLException {
        return quizHistoryDAO.getUsersFriendsRecentActivities(username);
    }

    /**
     * Calculates average score and elapsed time for a quiz, returned as a Pair(score, time).
     */
    public QuizHistoryDao.Pair<Long, Long> getAverageScoreAndTimeByQuizId(int quizId) throws SQLException {
        return quizHistoryDAO.getAverageScoreAndTimeByQuizId(quizId);
    }

    /**
     * Retrieves all quiz history entries for a user filtered by quiz ID.
     */
    public List<QuizHistory> getAllQuizHistoryByUsername(String username, int quizId) {
        return quizHistoryDAO.getAllQuizHistoryByUsername(username, quizId);
    }

    /**
     * Retrieves the username of the highest scoring user for a given quiz.
     */
    public String getHighestScoreUserNameByQuizId(int quizId) throws SQLException {
        return quizHistoryDAO.getHighestScoreUserNameByQuizId(quizId);
    }
}
