package Controllers;

import Controllers.managers.*;
import Models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomePageServlet", urlPatterns = {"/HomePageServlet"})
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("quizId");
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        //    AnnouncementManager announcementManager = (AnnouncementManager) getServletContext().getAttribute(AnnouncementManager.ATTRIBUTE_NAME);
        //      QuizHistoryManager quizHistoryManager = (QuizHistoryManager) getServletContext().getAttribute(QuizHistoryManager.ATTRIBUTE_NAME);
//        AchievementManager achievementManager = (AchievementManager) getServletContext().getAttribute(AchievementManager.ATTRIBUTE_NAME);
        //       NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            // Redirect to login if user not logged in
            response.sendRedirect("Authorisation.jsp");
            return;
        }
        Account account = null;
        account = (Account) accountManager.getAccount(username);
        System.out.println("account = " + account);
        //    List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) accountManager.getLeaderboard();
        //      List<Quiz> quizzes = quizManager.getAllQuizzes();

        //    try {
        //   List<Announcement> announcements = announcementManager.getAnnouncements();
        //  request.setAttribute("announcements", announcements);

        //  List<Quiz> popularQuizzes = quizHistoryManager.getPopularQuizzes();
        //  request.setAttribute("popularQuizzes", popularQuizzes);

        List<Quiz> recentQuizzes;
        try {
            recentQuizzes = quizManager.getNewlyAddedQuizzes();
        } catch (SQLException e) {
            throw new ServletException("Failed to load recent quizzes", e);
        }
        request.setAttribute("recentQuizzes", recentQuizzes);
        //    request.setAttribute("recentQuizzes", recentQuizzes);
        //     System.out.println("rec: " + recentQuizzes);

        //    List<Quiz> recentlyTakenQuizzes = quizHistoryManager.getQuizzesForUserByTakingTime(account.getUserName());
        //    request.setAttribute("recentQuizHistory", recentlyTakenQuizzes);
        //    System.out.println("taken: " + recentlyTakenQuizzes);

        //   List<Quiz> userRecent = quizManager.getQuizzesByUser(account.getUserName());
        //  request.setAttribute("userRecent", userRecent);

//            List<Achievement> achievements = achievementManager.getAchievementsByUsername(username);
//            request.setAttribute("achievements", achievements);

        //   List<Notification> notifications = notificationManager.getNotificationsToUser(username);
        //   request.setAttribute("notifications", notifications);

        //  List<QuizHistory> friendsActivities = quizHistoryManager.getUsersFriendsRecentActivities(username);
        //  request.setAttribute("friendsActivities", friendsActivities);


        request.setAttribute("username", username);
        request.setAttribute("account", account);
        // request.setAttribute("quizzes", quizzes);
        //  request.setAttribute("leaderboard", leaderboard);
        //  request.setAttribute("quizzes", quizzes);
        request.getSession().setAttribute("account", account);
        request.getSession().removeAttribute("quiz");
        request.getRequestDispatcher("/HomePage.jsp").forward(request, response);

        //    } catch (SQLException e) {
        //        e.printStackTrace();
        //       throw new RuntimeException(e);
        //    }
    }
}
