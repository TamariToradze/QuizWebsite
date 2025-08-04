
package Dao;

import Models.Enums.QuestionType;
import Models.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionDAO {

    private BasicDataSource dataSource;

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Question ReadQuestion(int questionId) {
        String query = "SELECT * FROM Question WHERE questionId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToQuestion(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int CreateQuestion(Question question) {
        String query = "INSERT INTO Question (quizId, questionType, questionText, " +
                "singleQuestionAnswer, alternativeAnswers, multipleChoiceAnswers, " +
                "multipleChoiceCorrectIndexes, questionImage, multipleAnswerFields, " +
                "matchingPairs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(question, statement);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating quiz failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setStatement(Question question, PreparedStatement statement) throws SQLException {
        Gson gson = new Gson();
        statement.setInt(1, question.getQuizId());
        statement.setInt(2, question.getQuestionType().ordinal());
        statement.setString(3, question.getQuestionText());
        statement.setString(4, question.getSingleQuestionAnswer());
        statement.setString(5, gson.toJson(question.getAlternativeAnswers()));
        statement.setString(6, gson.toJson(question.getMultipleChoiceAnswers()));
        statement.setString(7, gson.toJson(question.getMultipleChoiceCorrectIndexes()));
        statement.setString(8, question.getQuestionImage());
        statement.setString(9, gson.toJson(question.getMultipleAnswerFields()));
        statement.setString(10, gson.toJson(question.getMatchingPairs()));
    }

    public void updateQuestion(Question question) {
        String query = "UPDATE Question SET quizId = ?, questionType = ?, " +
                "questionText = ?, singleQuestionAnswer = ?, alternativeAnswers = ?, " +
                "multipleChoiceAnswers = ?, multipleChoiceCorrectIndexes = ?, " +
                "questionImage = ?, multipleAnswerFields = ?, matchingPairs = ? " +
                "WHERE questionId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            setStatement(question, statement);
            statement.setInt(11, question.getQuestionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(int questionId) {
        String query = "DELETE FROM Question WHERE questionId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Question mapRowToQuestion(ResultSet resultSet) throws SQLException {
        Gson gson = new Gson();
        Question question = new Question(resultSet.getInt("questionId"));
        question.setQuizId(resultSet.getInt("quizId"));
        question.setQuestionType(QuestionType.values()[resultSet.getInt("questionType")]);
        question.setQuestionText(resultSet.getString("questionText"));
        question.setSingleQuestionAnswer(resultSet.getString("singleQuestionAnswer"));
        question.setAlternativeAnswers(gson.fromJson(resultSet.getString("alternativeAnswers"), new TypeToken<HashSet<String>>() {}.getType()));
        question.setMultipleChoiceAnswers(gson.fromJson(resultSet.getString("multipleChoiceAnswers"), new TypeToken<ArrayList<String>>() {}.getType()));
        question.setMultipleChoiceCorrectIndexes(gson.fromJson(resultSet.getString("multipleChoiceCorrectIndexes"), new TypeToken<ArrayList<Integer>>() {}.getType()));
        question.setQuestionImage(resultSet.getString("questionImage"));
        question.setMultipleAnswerFields(gson.fromJson(resultSet.getString("multipleAnswerFields"), new TypeToken<ArrayList<String>>() {}.getType()));
        question.setMatchingPairs(gson.fromJson(resultSet.getString("matchingPairs"), new TypeToken<HashMap<String, String>>() {}.getType()));
        return question;
    }
}
