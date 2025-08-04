package Controllers;

import Models.Enums.QuestionType;
import Controllers.managers.QuestionManager;
import Controllers.managers.QuizManager;
import Models.Question;
import Models.Quiz;
import Models.QuizHistory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.TakeMultiPageQuiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Servlet responsible for displaying quiz questions and handling user responses.
 */
@WebServlet(name = "QuestionServlet", urlPatterns = {"/QuestionServlet"})
public class QuestionServlet extends HttpServlet {

    /**
     * Handles GET requests: renders single-page or multi-page quiz UIs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve managers from ServletContext
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);

        // Parse quizId parameter and store in session for later use
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        request.getSession().setAttribute("qid", quizId);

        // Fetch quiz object and UI helper
        Quiz quiz = quizManager.getQuiz(quizId);
        TakeMultiPageQuiz uiGenerator = new TakeMultiPageQuiz();

        // If quiz is single-page, generate all questions HTML at once
        if (quiz.isSinglePage()) {
            StringBuilder allQuestionsHtml = new StringBuilder();
            List<Question> questionList = quizManager.getAllQuestionsByQuiz(quizId);

            // Append each question's UI to the HTML builder
            for (Question q : questionList) {
                allQuestionsHtml.append(uiGenerator.generateUI(q.getQuestionType(), q));
            }

            // Set attributes for JSP
            request.setAttribute("questionsHtml", allQuestionsHtml.toString());
            request.setAttribute("quizId", quizId);
            request.setAttribute("questions", questionList);
            request.setAttribute("currentQuiz", quiz);

            // Forward to single-page JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("SinglePageQuiz.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Multi-page quiz: retrieve question IDs and current index from session
        List<Integer> questionIds = quiz.getQuestionIds();
        Integer currentIndex = (Integer) request.getSession().getAttribute("questionIndex");

        // Initialize index if first access
        if (currentIndex == null) {
            currentIndex = 0;
            request.getSession().setAttribute("questionIndex", currentIndex);
        }

        // If index exceeds available questions, delegate to POST to finish quiz
        if (currentIndex >= questionIds.size()) {
            doPost(request, response);
            return;
        }

        // Fetch current question and generate its HTML
        int currentQuestionId = questionIds.get(currentIndex);
        Question currentQuestion = questionManager.getQuestion(currentQuestionId);
        String questionHtml = uiGenerator.generateUI(currentQuestion.getQuestionType(), currentQuestion);

        // Set attributes for question JSP
        request.setAttribute("currentQuiz", quiz);
        request.setAttribute("questionHtml", questionHtml);
        request.setAttribute("quizId", quizId);
        request.setAttribute("questionIndex", currentIndex);

        // Retrieve or initialize quizHistory and score
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        request.getSession().setAttribute("score", quizHistory.getQuizScore());

        // Forward to multi-page question JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("SinglePageQuestion.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles POST requests: processes submitted answers for single or multi-page quizzes.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve managers and quiz metadata
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Integer questionIndex = (Integer) request.getSession().getAttribute("questionIndex");
        Quiz quiz = quizManager.getQuiz(quizId);

        // Delegate based on quiz type
        if (quiz.isSinglePage()) {
            handleSinglePageQuiz(request, response, quiz);
        } else {
            handleMultiPageQuiz(request, response, quiz, questionIndex);
        }
    }

    /**
     * Processes answers for a single-page quiz: evaluates all questions at once.
     */
    private void handleSinglePageQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        List<Integer> questionIds = quiz.getQuestionIds();

        // Retrieve quizHistory and username from session
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        String username = (String) request.getSession().getAttribute("username");

        // Loop through each question to compute score
        for (Integer qid : questionIds) {
            Question q = questionManager.getQuestion(qid);
            String singleAnswer = request.getParameter("userAnswer_" + qid);
            String answersJson = request.getParameter("userAnswers_" + qid);
            Gson gson = new Gson();

            // Prepare containers for parsed answers
            List<String> multipleAnswers = new ArrayList<>();
            HashMap<String, String> matchingAnswers = new HashMap<>();

            // Parse JSON based on question type
            if (q.getQuestionType() == QuestionType.MULTIPLE_CHOICE
                    || q.getQuestionType() == QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS
                    || q.getQuestionType() == QuestionType.MULTI_ANSWER) {
                Type listType = new TypeToken<ArrayList<String>>() {}.getType();
                multipleAnswers = gson.fromJson(answersJson, listType);
            } else if (q.getQuestionType() == QuestionType.MATCHING) {
                Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
                matchingAnswers = gson.fromJson(answersJson, mapType);
            }

            // Evaluate correctness and accumulate score
            int increment = questionManager.isAnswerCorrect(q, singleAnswer, new ArrayList<>(multipleAnswers), matchingAnswers);
            quizHistory.setQuizScore(quizHistory.getQuizScore() + increment);
        }

        // Update session and redirect to stats page
        request.getSession().setAttribute("quizHistory", quizHistory);
        request.getSession().setAttribute("username", quizHistory.getUsername());
        response.sendRedirect("/QuizStatsServlet");
    }

    /**
     * Processes answers for a multi-page quiz: evaluates one question per request.
     */
    private void handleMultiPageQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz, Integer questionIndex)
            throws ServletException, IOException {
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        int quizId = quiz.getQuizID();
        List<Integer> questionIds = quiz.getQuestionIds();
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");

        // If all questions answered, finalize quiz
        if (questionIndex >= questionIds.size()) {
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("username", quizHistory.getUsername());
            response.sendRedirect("/QuizStatsServlet");
            return;
        }

        // Process current question submission
        int currentQid = questionIds.get(questionIndex);
        Question currentQ = questionManager.getQuestion(currentQid);
        String singleAnswer = request.getParameter("userAnswer");
        String answersJson = request.getParameter("userAnswers");
        Gson gson = new Gson();

        // Parse list or map from JSON as needed
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        List<String> multipleAnswers = new ArrayList<>();
        if (currentQ.getQuestionType() == QuestionType.MULTIPLE_CHOICE
                || currentQ.getQuestionType() == QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS
                || currentQ.getQuestionType() == QuestionType.MULTI_ANSWER) {
            multipleAnswers = gson.fromJson(answersJson, listType);
        }
        Type mapType = new TypeToken<HashMap<String, String>>() {}.getType();
        HashMap<String, String> matchingAnswers = new HashMap<>();
        if (currentQ.getQuestionType() == QuestionType.MATCHING) {
            matchingAnswers = gson.fromJson(answersJson, mapType);
        }

        // Evaluate answer correctness and update score/session
        int increment = questionManager.isAnswerCorrect(currentQ, singleAnswer, new ArrayList<>(multipleAnswers), matchingAnswers);
        quizHistory.setQuizScore(quizHistory.getQuizScore() + increment);
        request.getSession().setAttribute("score", quizHistory.getQuizScore());
        request.getSession().setAttribute("questionIndex", questionIndex + 1);

        // Redirect to next question or finalize
        if (questionIndex + 1 >= questionIds.size()) {
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("username", quizHistory.getUsername());
            request.getSession().removeAttribute("score");
            response.sendRedirect("/QuizStatsServlet");
        } else {
            response.sendRedirect("/QuestionServlet?quizId=" + quizId);
        }
    }
}
