package Dao;

import Models.Question;
import Models.Quiz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for managing Quiz entities in the database.
 */
public class QuizDao {

    private final BasicDataSource connectionPool;

    public QuizDao(BasicDataSource dataSource) {
        this.connectionPool = dataSource;
    }

    /**
     * Creates a new quiz record in the database.
     *
     * @param quiz the Quiz object to be created
     */
    public int createQuiz(Quiz quiz) {
        String query = "INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, " +
                "randomizeQuestions, immediateFeedback, createTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            populateStatementParameters(quiz, statement);
            statement.setTimestamp(9, quiz.getCreateTime());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating quiz failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating quiz: " + e.getMessage(), e);
        }
    }

    private void populateStatementParameters(Quiz quiz, PreparedStatement statement) throws SQLException {
        statement.setString(1, quiz.getCreatorUsername());
        statement.setString(2, quiz.getQuizName());
        statement.setString(3, quiz.getQuizDescription());
        statement.setInt(4, quiz.getQuizScore());
        statement.setString(5, new Gson().toJson(quiz.getQuestionIds()));
        statement.setBoolean(6, quiz.isSinglePage());
        statement.setBoolean(7, quiz.isRandomizeQuestions());
        statement.setBoolean(8, quiz.isImmediateFeedback());
    }

    /**
     * Retrieves a quiz by its ID.
     *
     * @param quizID the ID of the quiz to be retrieved
     * @return the Quiz object, or null if not found
     */
    public Quiz readQuiz(int quizID) {
        String query = "SELECT * FROM Quiz WHERE quizID = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, quizID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return buildQuizFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading quiz: " + e.getMessage(), e);
        }
        return null;
    }

    private Quiz buildQuizFromResultSet(ResultSet rs) throws SQLException {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();

        ArrayList<Integer> questionIds = gson.fromJson(rs.getString("questionIds"), listType);

        return new Quiz(
                rs.getInt("quizID"),
                rs.getString("username"),
                rs.getString("quizName"),
                rs.getString("quizDescription"),
                rs.getInt("quizScore"),
                questionIds,
                rs.getBoolean("isSinglePage"),
                rs.getBoolean("randomizeQuestions"),
                rs.getBoolean("immediateFeedback"),
                rs.getTimestamp("createTime")
        );
    }


    /**
     * Updates an existing quiz record in the database.
     *
     * @param quiz the Quiz object with updated values
     */
    public void updateQuiz(Quiz quiz) {
        String query = "UPDATE Quiz SET username = ?, quizName = ?, quizDescription = ?, quizScore = ?, " +
                "questionIds = ?, isSinglePage = ?, randomizeQuestions = ?, immediateFeedback = ? WHERE quizID = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            populateStatementParameters(quiz, statement);
            statement.setInt(9, quiz.getQuizID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating quiz: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param quizID the ID of the quiz to be deleted
     */
    public void deleteQuiz(int quizID) {
        String query = "DELETE FROM Quiz WHERE quizID = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, quizID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting quiz: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all quizzes from the database.
     *
     * @return a list of all Quiz objects
     */
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM Quiz";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                quizzes.add(buildQuizFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all quizzes: " + e.getMessage(), e);
        }
        return quizzes;
    }

    public List<Question> getAllQuestionsByQuizId(int quizID) {
        List<Question> questions = new ArrayList<>();
        Quiz quiz = readQuiz(quizID);
        ArrayList<Integer> questionIds = quiz.getQuestionIds();
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.setDataSource(this.connectionPool);
        for (Integer questionId : questionIds) {
            questions.add(questionDAO.ReadQuestion(questionId));
        }
        return questions;
    }

    public List<Quiz> getAllQuizzesByCreationTime() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT quizID " +
                "FROM Quiz " +
                "ORDER BY DATE_FORMAT(createTime, '%Y-%m-%d %H:%i:%s') DESC";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                quizzes.add(readQuiz(resultSet.getInt("quizID")));
            }
        }
        return quizzes;
    }

    /**
     * Retrieves all quizzes from the database.
     *
     * @return a list of all Quiz objects
     */
    public List<Quiz> getAllQuizzesByUser(String username) {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM Quiz WHERE username = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            System.out.println("by user: "+ statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    quizzes.add(buildQuizFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all quizzes: " + e.getMessage(), e);
        }
        return quizzes;
    }

}