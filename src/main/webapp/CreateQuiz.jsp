<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Question" %>
<%@ page import="utils.TakeSinglePageQuiz" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Quiz" %>
<%@ page import="Controllers.managers.QuestionManager" %>
<%@ page import="Controllers.managers.QuizManager" %>

<jsp:useBean id="takeQuiz" class="utils.CreateQuiz"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Your Own Quiz</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="css/CreateQuiz.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body style="background-image: url('https://wallpaper.dog/large/20419572.jpg'); background-size: cover; background-position: center; background-attachment: fixed; background-repeat: no-repeat;">
<div class="container">
    <h1 style="color: white;">Create Your Own Quiz</h1>

    <% Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");%>
    <form id="quizActionsForm" action="CreateQuizServlet" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="quizAction" id="quizAction">
        <div class="textboxes">
            <div class="row">
                <div class="quiz-name">
                    <label for="quizName">Quiz Name:</label>
                    <input type="text" id="quizName" name="quizName" value="<%= quiz.getQuizName()%>" required>
                </div>
                <div class="booleans">
                    <label>
                        <input type="checkbox" name="isSinglePage"
                               <% if (quiz != null && quiz.isSinglePage()) { %>checked<% } %> /> Single Page Quiz
                    </label>
                    <label>
                        <input type="checkbox" name="randomizeQuestions"
                               <% if (quiz != null && quiz.isRandomizeQuestions()) { %>checked<% } %> /> Randomize
                        Questions
                    </label>
                    <label>
                        <input type="checkbox" name="immediateFeedback"
                               <% if (quiz != null && quiz.isImmediateFeedback()) { %>checked<% } %> /> Immediate
                        Feedback
                    </label>
                </div>
            </div>
            <div class="quiz-desc">
                <label for="quizDescription">Quiz Description:</label>
                <textarea id="quizDescription" name="quizDescription" value="<%= quiz.getQuizDescription()%>" required></textarea>
            </div>
        </div>

        <div class="buttons">
            <button type="button" id="saveQuiz" onclick="submitForm('save')">Save Quiz</button>
            <button type="button" id="deleteQuiz" onclick="submitForm('delete')">Delete Quiz</button>
        </div>
    </form>

    <form id="createQuizForm" action="CreateQuestionServlet" method="get">
        <div class="questions-list">
            <%
                List<Question> questions = (List<Question>) session.getAttribute("questions");
                if (questions != null) {
                    for (int i = 0; i < questions.size(); i++) {
                        Question question = questions.get(i);
                        String questionHtml = "<div class=\"question\"><h2>Question " + (i + 1) + "</h2>";
//                                "<div class=\"question_" + question.getQuestionId() + "\">" +
//                                "<h2>Question " + (i + 1) + "</h2>";
                        questionHtml += takeQuiz.generateUI(question.getQuestionType(), question, true);
                        questionHtml += "</div>";
            %>
            <div>
                <%= questionHtml %>
                <input type="hidden" name="questionId_<%= question.getQuestionId() %>"
                       value="<%= question.getQuestionId() %>">
            </div>
            <%
                    }
                }
            %>
        </div>
        <div class="buttons">
            <label for="questionType">Select QuestionType</label>
            <select id="questionType" name="questionType">
                <option value="QUESTION_RESPONSE">Select Question Style</option>
                <option value="FILL_IN_THE_BLANK">Fill in the Blank</option>
                <option value="MULTIPLE_CHOICE">Multiple Choice</option>
                <option value="PICTURE_RESPONSE">Picture Response</option>
                <option value="MULTI_ANSWER">Multi Answer</option>
                <option value="MULTIPLE_CHOICE_WITH_ANSWERS">Multiple Choice with Answers</option>
                <option value="MATCHING">Matching</option>
            </select>
            <input type="hidden" id="hiddenQuestionType" name="hiddenQuestionType">
            <button type="submit" id="addQuestionBtn">Add Question</button>
        </div>
    </form>
</div>

<script>
    document.getElementById('addQuestionBtn').addEventListener('click', function () {
        const questionType = document.getElementById('questionType').value;
        document.getElementById('hiddenQuestionType').value = questionType;
    });

    function submitForm(action) {
        console.log("aq");
        if (action === 'save' && !validateForm()) {
            return;
        }
        document.getElementById('quizAction').value = action;
        document.getElementById('quizActionsForm').submit();
    }

    function validateForm() {
        const quizName = document.getElementById('quizName').value;
        if (!quizName) {
            alert('Quiz Name is required.');
            return false;
        }
        return true;
    }



    <%--function deleteQuestion(questionId) {--%>
    <%--    // Send an AJAX request to the server to delete the question--%>
    <%--    var xhr = new XMLHttpRequest();--%>
    <%--    xhr.open("POST", "<%= request.getContextPath() %>/deleteQuestion", true);--%>
    <%--    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");--%>
    <%--    xhr.onreadystatechange = function () {--%>
    <%--        if (xhr.readyState === XMLHttpRequest.DONE) {--%>
    <%--            if (xhr.status === 200) {--%>
    <%--                // On success, hide the question div--%>
    <%--                var questionDiv = document.querySelector(".question_" + questionId);--%>
    <%--                if (questionDiv) {--%>
    <%--                    questionDiv.style.display = "none";--%>
    <%--                }--%>
    <%--            } else {--%>
    <%--                alert("Failed to delete question.");--%>
    <%--            }--%>
    <%--        }--%>
    <%--    };--%>
    <%--    xhr.send("questionId=" + encodeURIComponent(questionId));--%>
    <%--}--%>

    <%--function deleteQuestion(questionId) {--%>
    <%--    fetch('/deleteQuestion', {--%>
    <%--        method: 'POST',--%>
    <%--        headers: {--%>
    <%--            'Content-Type': 'application/x-www-form-urlencoded'--%>
    <%--        },--%>
    <%--        body: new URLSearchParams({--%>
    <%--            'questionId': questionId--%>
    <%--        })--%>
    <%--    })--%>
    <%--        .then(response => {--%>
    <%--            if (response.ok) {--%>
    <%--                return response.text();--%>
    <%--            } else {--%>
    <%--                throw new Error('Failed to delete question');--%>
    <%--            }--%>
    <%--        })--%>
    <%--        .then(message => {--%>
    <%--            console.log(message);--%>
    <%--            // Optionally remove the question from the DOM--%>
    <%--            document.getElementById(`question-<%= request.getAttribute("questionId") %>>`).remove();--%>
    <%--        })--%>
    <%--        .catch(error => {--%>
    <%--            console.error('Error:', error);--%>
    <%--        });--%>
    // }

</script>
</body>
</html>