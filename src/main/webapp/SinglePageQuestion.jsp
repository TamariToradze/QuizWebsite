<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Quiz" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Single Page Quiz</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="./css/SinglePageQuestion.css">
</head>
<body>

<div class="quiz-container">
    <% Quiz currentQuiz = (Quiz) request.getAttribute("currentQuiz");
        Integer score = (Integer) request.getSession().getAttribute("score");
        if (currentQuiz != null) { %>
    <h1>Quiz: <%= currentQuiz.getQuizName() %></h1>
    <h2>Question <%= ((Integer) request.getAttribute("questionIndex")) + 1 %></h2>
    <% if(currentQuiz.isImmediateFeedback()) {%>
    <h>Score: <%= score %></h>
    <% } %>
    <div class="question-html">
        <%= (String) request.getAttribute("questionHtml") %>
    </div>
    <% } else { %>
    <h1>Quiz Not Found</h1>
    <% } %>
</div>

</body>
</html>
