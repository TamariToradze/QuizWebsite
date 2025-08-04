package Controllers;

import Models.Enums.QuestionType;
import Controllers.managers.QuestionManager;
import Controllers.managers.QuizManager;
import Models.Question;
import Models.Quiz;
import utils.CreateQuestion;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Servlet for creating UI and processing data for new quiz questions.
 */
@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {

    /**
     * Handles GET requests: generates HTML for the question input form based on questionType.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve questionType parameter and trim whitespace
        String typeParam = request.getParameter("questionType");
        System.out.println("Received questionType in GET: " + typeParam);

        if (typeParam != null) {
            typeParam = typeParam.trim();
        }

        try {
            // Convert String to QuestionType enum
            QuestionType questionType = QuestionType.fromString(typeParam);
            System.out.println("Converted questionType in GET: " + questionType);

            // Generate dynamic HTML for the question form
            CreateQuestion helper = new CreateQuestion();
            String html = helper.generateUI(questionType);
            System.out.println("Generated HTML: " + html);

            // Attach HTML to request and forward to JSP
            request.setAttribute("html", html);
            request.getRequestDispatcher("CreateQuestion.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            // Handle invalid questionType with error page
            System.err.println("Error converting questionType in GET: " + e.getMessage());
            request.setAttribute("error", "Invalid question type: " + typeParam);
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    /**
     * Handles POST requests: processes form submission and creates new question record.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve and trim questionType parameter
        String typeParam = request.getParameter("questionType");
        if (typeParam != null) {
            typeParam = typeParam.trim();
        }
        System.out.println("Received questionType in POST: " + typeParam);

        try {
            // Managers and data from session/context
            ServletContext context = request.getServletContext();
            QuizManager quizManager = (QuizManager) context.getAttribute(QuizManager.ATTRIBUTE_NAME);
            QuestionManager questionManager = (QuestionManager) context.getAttribute(QuestionManager.ATTRIBUTE_NAME);

            // Convert to enum
            QuestionType questionType = QuestionType.fromString(typeParam);
            System.out.println("Converted questionType in POST: " + questionType);

            // Base question fields
            String questionText = request.getParameter("questionText").trim();
            System.out.println("Question Text: " + questionText);

            Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
            int quizId = (int) request.getSession().getAttribute("quizId");

            // Build Question object
            Question question = new Question();
            question.setQuizId(quizId);
            question.setQuestionText(questionText);
            question.setQuestionType(questionType);

            // Populate question-specific fields
            switch (questionType) {
                case QUESTION_RESPONSE:
                case FILL_IN_THE_BLANK:
                    String answerText = request.getParameter("answerText").trim();
                    question.setSingleQuestionAnswer(answerText);
                    break;
                case PICTURE_RESPONSE:
                    String questionImage = request.getParameter("questionImage").trim();
                    String singleAns = request.getParameter("answerText").trim();
                    question.setSingleQuestionAnswer(singleAns);
                    question.setQuestionImage(questionImage);
                    break;
                case MULTIPLE_CHOICE:
                case MULTIPLE_CHOICE_WITH_ANSWERS:
                    // Collect up to 4 answer options
                    ArrayList<String> options = new ArrayList<>();
                    for (int i = 1; i <= 4; i++) {
                        String opt = request.getParameter("answer" + i);
                        if (opt != null && !opt.trim().isEmpty()) {
                            options.add(opt.trim());
                        }
                    }
                    question.setMultipleChoiceAnswers(options);

                    // Parse correct indexes CSV
                    ArrayList<Integer> correctIdx = new ArrayList<>();
                    String[] idxArray = request.getParameter("correctIndexes").split(",");
                    for (String idx : idxArray) {
                        try {
                            correctIdx.add(Integer.parseInt(idx.trim()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace(); // log parse failure
                        }
                    }
                    question.setMultipleChoiceCorrectIndexes(correctIdx);
                    break;
                case MULTI_ANSWER:
                    // Collect up to 10 multi-answer fields
                    ArrayList<String> multiAns = new ArrayList<>();
                    for (int i = 1; i <= 10; i++) {
                        String ans = request.getParameter("answer" + i);
                        if (ans != null && !ans.trim().isEmpty()) {
                            multiAns.add(ans.trim());
                        }
                    }
                    question.setMultipleAnswerFields(multiAns);
                    break;
                case MATCHING:
                    // Parse matching pairs
                    HashMap<String, String> pairs = new HashMap<>();
                    int pairCount = Integer.parseInt(request.getParameter("pairCount"));
                    for (int i = 1; i <= pairCount; i++) {
                        String left = request.getParameter("question" + i);
                        String right = request.getParameter("answer" + i);
                        if (left != null && right != null) {
                            pairs.put(left.trim(), right.trim());
                        }
                    }
                    question.setMatchingPairs(pairs);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected question type: " + questionType);
            }

            // Persist question and update quiz
            int newQuestionId = questionManager.createQuestion(question);
            quiz.getQuestionIds().add(newQuestionId);
            quizManager.updateQuiz(quiz);

            // Update session and redirect back to quiz creation
            request.getSession().setAttribute("quiz", quiz);
            response.sendRedirect("CreateQuizServlet");
        } catch (IllegalArgumentException e) {
            // On error, forward to error page
            System.err.println("Error in POST CreateQuestionServlet: " + e.getMessage());
            request.setAttribute("error", "Invalid question type: " + typeParam);
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
