package Controllers.managers;

import Dao.LeaderboardDAO;
import Models.LeaderboardEntry;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.List;

/**
 * Manages leaderboard-related operations by communicating with the DAO layer.
 * Handles data retrieval for quiz leaderboards.
 */
public class LeaderboardManager {
    private final LeaderboardDAO leaderboardDAO;    // DAO responsible for performing database operations related to the leaderboard
    public static final String ATTRIBUTE_NAME = "LeaderboarManager";

    //initializes the LeaderboardManager with a configured LeaderboardDAO instance.
    public LeaderboardManager() {
        SQLConnector sqlConnector = new SQLConnector();
        this.leaderboardDAO = new LeaderboardDAO(sqlConnector.dataSource);

    }

    //retrieves the leaderboard entries for the specified quiz.
    public List<LeaderboardEntry> getLeaderboard(int quizId) throws SQLException {
        return leaderboardDAO.readLeaderBoard(quizId);
    }
}