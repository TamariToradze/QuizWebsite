package Controllers;

import Controllers.managers.QuestionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to handle deletion of a question by its ID.
 * Expects a POST request with parameter "questionId".
 */
@WebServlet("/deleteQuestion")
public class DeleteQuestionServlet extends HttpServlet {

    /**
     * Handles POST requests to delete a question.
     * Responds with HTTP 200 if deletion succeeds,
     * or HTTP 400 on invalid input.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the "questionId" parameter from the request
        String questionIdParam = request.getParameter("questionId");

        // Get the QuestionManager from the servlet context
        QuestionManager questionManager =
                (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);

        // Validate the parameter
        if (questionIdParam != null) {
            try {
                // Parse the string ID to integer
                int questionId = Integer.parseInt(questionIdParam);

                // Perform the deletion
                questionManager.deleteQuestion(questionId);

                // Respond with 200 OK on success
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (NumberFormatException e) {
                // If ID is not a valid integer, respond with 400 Bad Request
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // Missing parameter: respond with 400 Bad Request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}