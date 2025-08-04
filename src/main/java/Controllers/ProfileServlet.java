package Controllers;

import Controllers.managers.AccountManager;
import Controllers.managers.AchievementManager;
import Controllers.managers.FriendsManager;
import Controllers.managers.QuizManager;
import Models.Account;
import Models.Achievement;
import Models.Quiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
@MultipartConfig
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles GET requests for viewing a profile
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve necessary managers from context
        AchievementManager achievementManager = (AchievementManager) getServletContext().getAttribute(AchievementManager.ATTRIBUTE_NAME);
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        FriendsManager friendsManager = (FriendsManager) getServletContext().getAttribute(FriendsManager.ATTRIBUTE_NAME);

        // Get logged-in and profile username
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        String profileUsername = request.getParameter("username");

        // If profile username is not provided, assume self-profile
        if (profileUsername == null) profileUsername = loggedInUsername;

        // Get account objects for profile and logged-in user
        Account profileAccount = accountManager.getAccount(profileUsername);
        Account loggedInAccount = accountManager.getAccount(loggedInUsername);

        request.setAttribute("account", profileAccount);

        // Check if user is viewing their own profile
        boolean isViewingOwnProfile = profileUsername.equals(loggedInUsername);
        request.setAttribute("isSelf", isViewingOwnProfile);

        // Check if the logged-in user is an admin
        boolean isAdmin = loggedInAccount.isAdmin();
        request.setAttribute("isAdmin", isAdmin);

        // Retrieve quizzes created by the user
        List<Quiz> userQuizzes = quizManager.getQuizzesByUser(profileUsername);
        request.setAttribute("quizList", userQuizzes);

        // Retrieve achievements earned by the user
        try {
            Set<Achievement> userAchievements = achievementManager.getAllAchievemtnsByUser(profileAccount.getAchievementIds());
            request.setAttribute("achievementList", userAchievements);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Retrieve friends of the user
        try {
            List<String> userFriends = friendsManager.getAcceptedFriendRequests(profileUsername);
            request.setAttribute("friendsList", userFriends);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Forward to the Profile JSP page
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile.jsp");
        requestDispatcher.forward(request, response);
    }

    // Handles POST requests, either redirecting to edit page, or handling delete/makeAdmin actions
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String method = request.getParameter("_method");
        String action = request.getParameter("action");

        // Handle profile deletion
        if (action != null && action.equals("deleteProfile")) {
            String username = request.getParameter("username");
            if (accountManager.deleteAccount(username)) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": false}");
            }
            return;
        }

        // Handle make admin action
        if (action != null && action.equals("makeAdmin")) {
            String username = request.getParameter("username");
            accountManager.makeAdmin(username);
            response.sendRedirect("ProfileServlet?username=" + username);
            return;
        }

        // Handle profile update (PUT request)
        if (method != null && method.equalsIgnoreCase("put")) {
            doPut(request, response);
        } else {
            // Redirect to EditProfile.jsp for editing profile information
            String loggedInUsername = (String) request.getSession().getAttribute("username");
            if (loggedInUsername != null) {
                Account account = accountManager.getAccount(loggedInUsername);
                request.setAttribute("account", account);
                request.setAttribute("isSelf", true);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProfile.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to edit your profile.");
            }
        }
    }

    // Handles PUT requests for updating profile information (including profile image)
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String loggedInUsername = (String) request.getSession().getAttribute("username");

        Account account = accountManager.getAccount(loggedInUsername);

        // Update profile details from form parameters
        account.setFirstName(request.getParameter("firstName"));
        account.setLastName(request.getParameter("lastName"));
        account.setEmail(request.getParameter("email"));

        request.setAttribute("account", account);
        request.setAttribute("isSelf", true);

        // Handle profile image upload
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getSubmittedFileName(filePart);

            // Define and create upload directory
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Save the uploaded file
            filePart.write(uploadDir + File.separator + fileName);
            account.setImageUrl("uploads/" + fileName);
        }

        // Update account in database
        accountManager.updateAccount(account);

        // Forward to EditProfile.jsp
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProfile.jsp");
        requestDispatcher.forward(request, response);
    }

    // Helper method to get submitted file name from multipart part
    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
