package Dao;

import Dao.QuizDao;
import Models.Quiz;
import Models.QuizHistory;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Data Access Object for managing quiz history data in the database.
 * Provides CRUD operations and additional queries for quiz-related statistics.
 */
public class QuizHistoryDao {

    private BasicDataSource dataSource;

    public QuizHistoryDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Inserts a new quiz history record into the database.
     */
    public void createQuizHistory(QuizHistory quizHistory) throws SQLException {
        String query = "INSERT INTO QuizHistory (quizId, username, quizScore, startTime, endTime, endDate, elapsedTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizHistory.getQuizId());
            stmt.setString(2, quizHistory.getUsername());
            stmt.setInt(3, quizHistory.getQuizScore());
            stmt.setTime(4, quizHistory.getStartTime());
            stmt.setTime(5, quizHistory.getEndTime());
            stmt.setDate(6, (Date) quizHistory.getEndDate());
            stmt.setLong(7, quizHistory.getElapsedTime());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a single quiz history record by quizId.
     */
    public QuizHistory getQuizHistoryByQuizId(int quizId) throws SQLException {
        String query = "SELECT * FROM QuizHistory WHERE quizId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizId);
            QuizHistory quizHistory = getQuizHistory(stmt);
            if (quizHistory != null) return quizHistory;
        }
        return null;
    }

    /**
     * Executes a prepared statement and returns a QuizHistory object (if found).
     */
    private QuizHistory getQuizHistory(PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                QuizHistory quizHistory = new QuizHistory(
                        rs.getInt("quizId"),
                        rs.getString("username"),
                        rs.getInt("quizScore"),
                        rs.getTime("startTime"),
                        rs.getTime("endTime"),
                        rs.getLong("elapsedTime")
                );
                Calendar calendar = Calendar.getInstance();
                quizHistory.setEndDate(calendar.getTime());
                return quizHistory;
            }
        }
        return null;
    }

    /**
     * Retrieves all quiz history records for a specific username.
     */
    public List<QuizHistory> getAllQuizHistoryByUsername(String username) throws SQLException {
        String query = "SELECT * FROM QuizHistory WHERE username = ?";
        List<QuizHistory> quizHistories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            System.out.println(stmt);  // Debugging
            try (ResultSet rs = stmt.executeQuery()) {
                getHistoryWithWhile(quizHistories, rs);
            }
        }
        return quizHistories;
    }

    /**
     * Retrieves one quiz history record by username.
     */
    public QuizHistory getQuizHistoryByUsername(String username) throws SQLException {
        String query = "SELECT * FROM QuizHistory WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            QuizHistory quizHistory = getQuizHistory(stmt);
            if (quizHistory != null) return quizHistory;
        }
        return null;
    }

    /**
     * Retrieves the username of the player with the highest score for a given quiz.
     */
    public String getHighestScoreUserNameByQuizId(int quizId) throws SQLException {
        String query = "SELECT username FROM quizHistory WHERE quizId = ? ORDER BY quizScore DESC LIMIT 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all quiz history records for a specific username and quizId.
     */
    public List<QuizHistory> getAllQuizHistoryByUsername(String username, int quizId) {
        String query = "SELECT * FROM QuizHistory WHERE username = ? AND quizId = ?";
        List<QuizHistory> quizHistories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setInt(2, quizId);
            try (ResultSet rs = stmt.executeQuery()) {
                getHistoryWithWhile(quizHistories, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizHistories;
    }

    /**
     * Retrieves all quiz history records for a username.
     * (Duplicate of getAllQuizHistoryByUsername but does not throw SQLException.)
     */
    public List<QuizHistory> getAllQuizHistoryByUsernameOnly(String username) {
        String query = "SELECT * FROM QuizHistory WHERE username = ?";
        List<QuizHistory> quizHistories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                getHistoryWithWhile(quizHistories, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizHistories;
    }

    /**
     * Updates quiz score and timing fields in a quiz history record.
     */
    public void updateQuizHistory(QuizHistory quizHistory) throws SQLException {
        String query = "UPDATE QuizHistory SET quizScore = ?, startTime = ?, endTime = ?, elapsedTime = ? WHERE quizId = ? AND username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizHistory.getQuizScore());
            stmt.setTime(2, quizHistory.getStartTime());
            stmt.setTime(3, quizHistory.getEndTime());
            stmt.setLong(4, quizHistory.getElapsedTime());
            stmt.setInt(5, quizHistory.getQuizId());
            stmt.setString(6, quizHistory.getUsername());
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a quiz history record.
     */
    public void deleteQuizHistory(int quizId, String username) throws SQLException {
        String query = "DELETE FROM QuizHistory WHERE quizId = ? AND username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizId);
            stmt.setString(2, username);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all quiz history records in the database.
     */
    public List<QuizHistory> getAllQuizHistories() throws SQLException {
        String query = "SELECT * FROM QuizHistory";
        List<QuizHistory> quizHistories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            getHistoryWithWhile(quizHistories, rs);
        }
        return quizHistories;
    }

    /**
     * Retrieves quizzes ordered by how many times they've been taken (popularity).
     */
    public List<Quiz> getAllQuizzesByPopularity() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT quizId FROM QuizHistory GROUP BY quizId ORDER BY COUNT(quizId) DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            QuizDao quizDAO = new QuizDao(dataSource);
            while (rs.next()) {
                quizzes.add(quizDAO.readQuiz(rs.getInt("quizId")));
            }
        }
        return quizzes;
    }

    /**
     * Retrieves quizzes a user has taken, sorted by the start time.
     */
    public List<Quiz> getQuizzesForUserByTakingTime(String username) throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT quizId FROM quizHistory WHERE username = ? ORDER BY DATE_FORMAT(startTime, '%Y-%m-%d %H:%i:%s') DESC";

        QuizDao quizDAO = new QuizDao(dataSource);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            System.out.println(stmt);  // Debugging
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    quizzes.add(quizDAO.readQuiz(rs.getInt("quizId")));
                }
            }
        }
        return quizzes;
    }

    /**
     * Deletes all quiz history records by quizId.
     */
    public void deleteAllQuizzesById(int quizId) throws SQLException {
        String query = "DELETE FROM QuizHistory WHERE quizId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizId);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves recent quiz activities of the user's accepted friends.
     */
    public List<QuizHistory> getUsersFriendsRecentActivities(String username) throws SQLException {
        List<QuizHistory> activities = new ArrayList<>();
        String query = "SELECT * FROM QuizHistory qh " +
                "WHERE qh.username IN ( " +
                "    SELECT a.userName FROM Accounts a " +
                "    JOIN Friends f ON (a.userName = f.usernameFrom OR a.userName = f.usernameTo) " +
                "    WHERE (f.usernameFrom = ? OR f.usernameTo = ?) " +
                "      AND f.isAccepted = TRUE " +
                "      AND a.userName != ? " +
                ") " +
                "ORDER BY DATE_FORMAT(startTime, '%Y-%m-%d %H:%i:%s') DESC;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, username);
            stmt.setString(3, username);
            try (ResultSet rs = stmt.executeQuery()) {
                getHistoryWithWhile(activities, rs);
            }
            return activities;
        }
    }

    /**
     * Helper method to parse multiple quiz history results into a list.
     */
    private void getHistoryWithWhile(List<QuizHistory> quizHistories, ResultSet rs) throws SQLException {
        while (rs.next()) {
            QuizHistory quizHistory = new QuizHistory(
                    rs.getInt("quizId"),
                    rs.getString("username"),
                    rs.getInt("quizScore"),
                    rs.getTime("startTime"),
                    rs.getTime("endTime"),
                    rs.getLong("elapsedTime")
            );
            Calendar calendar = Calendar.getInstance();
            quizHistory.setEndDate(calendar.getTime());
            quizHistories.add(quizHistory);
        }
    }

    /**
     * Retrieves the average quiz score and elapsed time for a given quiz.
     */
    public Pair<Long, Long> getAverageScoreAndTimeByQuizId(int quizId) throws SQLException {
        String query = "SELECT avg(quizScore), avg(elapsedTime) FROM QuizHistory WHERE quizId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, quizId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return new Pair<>(rs.getLong("avg(quizScore)"), rs.getLong("avg(elapsedTime)"));
                else
                    return null;
            }
        }
    }
    public static class Pair<L, R> {
        private final L left;
        private final R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }
    }
}
