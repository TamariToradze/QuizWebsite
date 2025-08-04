package Dao;

import Models.LeaderboardEntry;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LeaderboardDAO {
    private BasicDataSource ds;


    public LeaderboardDAO(BasicDataSource dataSource) {
        this.ds = dataSource;
    }

  //  Get leaderboard entries for a quiz
    public List<LeaderboardEntry> readLeaderBoard(int quizId) throws SQLException {
        List<LeaderboardEntry> br = new ArrayList<>();
        String table = "SELECT qh.username, qh.quizScore, qh.elapsedTime " +
                "FROM QUIZ q " +
                "LEFT JOIN QuizHistory qh ON qh.quizId = q.quizId " +
                "WHERE q.quizId = ? " +
                "ORDER BY qh.quizScore DESC, qh.elapsedTime ASC;";

        return getLeaderboardEntries(quizId, br, table);
    }
//Get recent leaderboard entries
    //join logics
    public List<LeaderboardEntry> readLeaderBoardForLastFixedTime(int quizId) throws SQLException {
        List<LeaderboardEntry> br = new ArrayList<>();
        String table = "SELECT qh.username, qh.quizScore, qh.elapsedTime " +
                "FROM QUIZ q " +
                "LEFT JOIN QuizHistory qh ON qh.quizId = q.quizId " +
                "WHERE q.quizId = ? " +
                "AND DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 15 MINUTE), '%Y-%m-%d %H:%i:%s') < DATE_FORMAT(q.endDate, '%Y-%m-%d %H:%i:%s') " +
                "ORDER BY qh.quizScore DESC, qh.elapsedTime ASC";

        return getLeaderboardEntries(quizId, br, table);
    }

    //add data in leaderboard table
    private List<LeaderboardEntry> getLeaderboardEntries(int quizId, List<LeaderboardEntry> leaderBoard, String query) throws SQLException {
        try (Connection cn = ds.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setInt(1, quizId);

            try (ResultSet fs = st.executeQuery()) {
                while (fs.next()) {
                    String nm = fs.getString("username");
                    int result = fs.getInt("quizScore");
                    long time = fs.getLong("elapsedTime");
                    leaderBoard.add(new LeaderboardEntry(nm, result, time));
                }
            }
        }
        return leaderBoard;
    }
}