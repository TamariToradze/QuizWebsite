package Controllers;

import Controllers.managers.AccountManager;
import Controllers.managers.QuestionManager;
import Controllers.managers.QuizManager;
import Models.Account;
import Models.Question;
import Models.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Servlet for creating, editing, and deleting quizzes.
 */
@WebServlet(name = "CreateQuizServlet", urlPatterns = {"/CreateQuizServlet"})
public class CreateQuizServlet extends HttpServlet {

    /**
     * Handles GET requests: initializes a new Quiz or loads an existing one for editing,
     * then forwards to CreateQuiz.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Attempt to retrieve Quiz object from session (if user is editing)
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId;

        // If no quiz in session, create a new one
        if (quiz == null) {
            String creator = request.getSession().getAttribute("username").toString();
            quiz = new Quiz();
            quiz.setCreatorUsername(creator);

            // Persist new quiz and retrieve generated ID
            quizId = quizManager.addQuiz(quiz);
            quiz.setQuizID(quizId);
        } else {
            // Editing existing quiz: retrieve its ID
            quizId = quiz.getQuizID();
        }

        // Load current list of questions if quiz already persisted
        List<Question> questions = (quizId != 0) ? quizManager.getAllQuestionsByQuiz(quizId) : java.util.Collections.emptyList();

        // Store quiz and questions in session for the JSP
        request.getSession().setAttribute("quiz", quiz);
        request.getSession().setAttribute("quizId", quizId);
        request.getSession().setAttribute("questions", questions);

        // Forward to JSP for quiz creation/editing
        // Forward to JSP for quiz creation/editing
        request.getRequestDispatcher("CreateQuiz.jsp").forward(request, response);
    }

    /**
     * Handles POST requests: performs save or delete actions based on quizAction parameter.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Determine action: 'save' or 'delete'
        String action = request.getParameter("quizAction");
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            // Session expired or user not logged in
            response.sendRedirect("AuthorisationTryAgain.jsp");
            return;
        }

        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        boolean isNew = (quiz == null) || quiz.getQuizID() == 0;

        // If no quiz object in session (e.g., session expired), create a fresh one when action is save
        if (quiz == null) {
            if ("save".equals(action)) {
                quiz = new Quiz();
                quiz.setCreatorUsername(username);
                isNew = true;
            } else {
                response.sendRedirect("HomePageServlet#slide-4");
                return;
            }
        }

        if ("save".equals(action)) {
            // Update quiz settings from form inputs
            quiz.setSinglePage(request.getParameter("isSinglePage") != null);
            quiz.setRandomizeQuestions(request.getParameter("randomizeQuestions") != null);
            quiz.setImmediateFeedback(request.getParameter("immediateFeedback") != null);
            quiz.setQuizDescription(request.getParameter("quizDescription"));
            quiz.setQuizName(request.getParameter("quizName"));

            if (isNew) {
                // First-time save – insert
                int newId = quizManager.addQuiz(quiz);
                quiz.setQuizID(newId);
            } else {
                // Update existing quiz
                quizManager.updateQuiz(quiz);
            }

            // Handle achievement awards for first, fifth, and tenth quiz
            AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
            Account account = accountManager.getAccount(username);
            Set<Integer> achievementIds = (Set<Integer>) account.getAchievementIds();
            if (achievementIds == null) {
                achievementIds = new HashSet<>();
            }
            int totalQuizzes = quizManager.getQuizzesByUser(username).size();
            if (totalQuizzes == 1) {
                achievementIds.add(1);
            } else if (totalQuizzes == 5) {
                achievementIds.add(2);
            } else if (totalQuizzes == 10) {
                achievementIds.add(3);
            }
            account.setAchievementIds(achievementIds);
            accountManager.updateAccount(account);

            // Clean up session and redirect home
            request.getSession().removeAttribute("quiz");
            request.getSession().removeAttribute("questions");
            response.sendRedirect("HomePageServlet#slide-4");

        } else if ("delete".equals(action)) {
            // Delete all questions for this quiz, then delete the quiz itself
            QuestionManager questionManager = (QuestionManager) request.getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
            for (int qId : quiz.getQuestionIds()) {
                questionManager.deleteQuestion(qId);
            }
            quizManager.deleteQuiz(quiz.getQuizID());

            // Clean up session and redirect home
            request.getSession().removeAttribute("quiz");
            request.getSession().removeAttribute("questions");
            response.sendRedirect("HomePageServlet#slide-4");
        }
    }
}
