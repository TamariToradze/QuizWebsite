package Controllers;

import Controllers.managers.*;
import Models.Account;
import Models.LeaderboardEntry;
import Models.QuizHistory;
import Dao.QuizHistoryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet that handles quiz statistics and results display.
 * Processes completed quiz data, updates user achievements, calculates statistics,
 * and forwards to the quiz results page.
 */
@WebServlet(name = "QuizStatsServlet", urlPatterns = {"/QuizStatsServlet"})
public class QuizStatsServlet extends HttpServlet {

    // Manager for handling quiz history database operations
    private QuizHistoryManager quizHistoryManager;

    /**
     * Initialize the servlet by retrieving the QuizHistoryManager from servlet context
     */
    @Override
    public void init() throws ServletException {
        super.init();
        quizHistoryManager = (QuizHistoryManager) getServletContext().getAttribute(QuizHistoryManager.ATTRIBUTE_NAME);
    }

    /**
     * Handles GET requests to display quiz statistics and results
     *
     * @param request  HTTP request containing quiz session data
     * @param response HTTP response for forwarding to results page
     * @throws ServletException if servlet processing fails
     * @throws IOException if I/O operations fail
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve quiz completion data from session
        QuizHistory currentQuizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        String loggedInUsername = (String) request.getSession().getAttribute("username");

        // Get required managers from servlet context
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        AchievementManager achievementManager = (AchievementManager) getServletContext().getAttribute(AchievementManager.ATTRIBUTE_NAME);

        // Get user account and quiz information
        Account userAccount = accountManager.getAccount(loggedInUsername);
        int completedQuizId = currentQuizHistory.getQuizId();

        try {
            // Award achievements based on total quiz completion count
            int totalQuizzesCompleted = quizHistoryManager.getAllQuizHistoryByUsername(loggedInUsername).size();

            // Award achievement for completing 5 quizzes
            if (totalQuizzesCompleted == 5) {
                userAccount.getAchievementIds().add(4);
            }
            // Award achievement for completing 10 quizzes
            else if (totalQuizzesCompleted == 10) {
                userAccount.getAchievementIds().add(6);
            }

            // Update account with new achievements
            accountManager.updateAccount(userAccount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Store quiz ID in session for future reference
        request.getSession().setAttribute("quizId", completedQuizId);

        // Get quiz details for display
        String completedQuizName = quizManager.getQuiz(completedQuizId).getQuizName();
        int finalScore = currentQuizHistory.getQuizScore();

        // Calculate and set quiz completion time
        currentQuizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
        long startTimeMillis = currentQuizHistory.getStartTime().getTime();
        long endTimeMillis = currentQuizHistory.getEndTime().getTime();
        long totalTimeTakenSeconds = (endTimeMillis - startTimeMillis) / 1000;
        currentQuizHistory.setElapsedTime(totalTimeTakenSeconds);

        String quizCompletionUsername = currentQuizHistory.getUsername();

        try {
            // Save the completed quiz history to database
            quizHistoryManager.createQuizHistory(currentQuizHistory);

            // Award achievement if user achieved highest score on this quiz
            if (quizHistoryManager.getHighestScoreUserNameByQuizId(completedQuizId).equals(userAccount.getUserName())
                    && currentQuizHistory.getQuizScore() > 0) {
                userAccount.getAchievementIds().add(5);
            }

            // Update account with potential new achievement
            accountManager.updateAccount(userAccount);

            // Set flag to indicate quiz history has been successfully stored
            request.getSession().setAttribute("quizHistoryStored", true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to store quiz history in database", e);
        }

        // Retrieve user's personal history for this specific quiz
        List<QuizHistory> userPersonalHistory = new ArrayList<>();
        userPersonalHistory = quizHistoryManager.getAllQuizHistoryByUsername(quizCompletionUsername, completedQuizId);
        // Note: Alternative approach to get all user's quiz history (currently commented out)
        // userPersonalHistory = quizHistoryManager.getAllQuizHistoryByUsername(loggedInUsername);

        // Get leaderboard data for this quiz
        LeaderboardManager leaderboardManager = (LeaderboardManager) getServletContext().getAttribute(LeaderboardManager.ATTRIBUTE_NAME);
        try {
            List<LeaderboardEntry> quizLeaderboard = leaderboardManager.getLeaderboard(completedQuizId);
            request.setAttribute("leaderboard", quizLeaderboard);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to retrieve leaderboard data", e);
        }

        // Note: Additional statistics calculation method (currently commented out)
        // quizStatsCounter(userPersonalHistory, request);

        try {
            // Get average score and time statistics for this quiz
            QuizHistoryDao.Pair<Long, Long> averageStats = quizHistoryManager.getAverageScoreAndTimeByQuizId(completedQuizId);
            request.setAttribute("avgScore", averageStats.getLeft());
            // Note: There appears to be a bug here - should be averageStats.getValue() for average time
            request.setAttribute("avgTime", averageStats.getRight());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Set all attributes for the JSP page to display
        request.setAttribute("quizId", completedQuizId);
        request.setAttribute("personalHistory", userPersonalHistory);
        request.setAttribute("quizName", completedQuizName);
        request.setAttribute("username", loggedInUsername);
        request.setAttribute("score", finalScore);
        request.setAttribute("timeTakenSeconds", currentQuizHistory.getElapsedTime());
        request.setAttribute("quizHistory", currentQuizHistory);

        // Forward to the quiz statistics JSP page for display
        RequestDispatcher dispatcher = request.getRequestDispatcher("QuizStats.jsp");
        dispatcher.forward(request, response);
    }
}