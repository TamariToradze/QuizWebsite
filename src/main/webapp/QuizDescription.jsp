<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.LeaderboardEntry" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Account" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://cdn.dribbble.com/users/3975278/screenshots/17581195/media/2bd56e0a2606bfdf3c11edb74875658c.png?resize=800x600&vertical=center">
    <title>Quiz Description</title>
    <link rel="stylesheet" href="./css/QuizDescription.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="icon" href="./assets/Logo.png">

    <style>
        .start-btn{
            margin: 10px;
        }
    </style>
</head>
<body>
<% Quiz quiz = (Quiz) request.getAttribute("currentQuiz");
    List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) request.getAttribute("leaderboard");
    Account account = (Account) request.getAttribute("account");
    boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
%>
<header class="animate__animated animate__fadeInDown">
    <h1><%= quiz.getQuizName() %></h1>
</header>

<div class="container">
    <div class="description animate__animated animate__fadeInUp">
        <h2>Quiz Description</h2>
        <p><span>Description:</span> <%= quiz.getQuizDescription() %></p>
        <p><span>Question number:</span> <%= quiz.getQuestionIds().size() %></p>
        <p><span>Author:</span> <%= quiz.getCreatorUsername() %></p>
        <p><span>Page Type:</span> <%= quiz.isSinglePage() ? "Single page" : "Multiple pages" %></p>
        <p><span>Randomize Questions:</span> <%= quiz.isRandomizeQuestions() %></p>
        <p><span>Immediate Feedback:</span> <%= quiz.isImmediateFeedback() %></p>
        <p><span>Create Time:</span> <%= quiz.getCreateTime() %></p>
        <p><span class="time-taken">Time taken (global average): </span> : <%= request.getAttribute("avgTime") %> seconds</p>
        <p><span class="time-taken">Score (global average): </span> : <%= request.getAttribute("avgTime") %></p>
    </div>
    <div class="leaderboard">
        <h2>Leaderboard</h2>
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Score</th>
                <th>Time Taken</th>
            </tr>
            </thead>
            <tbody>
            <%
                int rank = 1;
                for (LeaderboardEntry entry : leaderboard) {
            %>
            <tr>
                <td><%= rank %></td>
                <td><%= entry.getUsername() %></td>
                <td><%= entry.getScore() %></td>
                <td><%= entry.getElapsedTime() %> seconds</td>
            </tr>
            <%
                    rank++;
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<form id="startQuizForm" action="QuizServlet" method="post">
    <input type="hidden" name="quizId" value="<%= quiz.getQuizID() %>">
    <input type="hidden" name="userId" value="<%= request.getSession().getAttribute("userId") %>">
    <button type="submit" class="start-btn">Start Quiz</button>
</form>

<% if (isAdmin) { %>
<form action="QuizServlet" method="post">
    <input type="hidden" name="quizId" value="<%= quiz.getQuizID() %>">
    <input type="hidden" name="action" value="deleteQuiz">
    <button type="submit" class="start-btn">Delete Quiz</button>
</form>
<% } %>

<% if (account.getUserName().equals(quiz.getCreatorUsername())) { %>
<form action="CreateQuizServlet" method="get">
    <input type="hidden" name="quizId" value="<%=quiz.getQuizID()%>">
    <input type="hidden" name="action" value="editQuiz">
    <button type="submit" class="start-btn">Edit Quiz</button>
</form>
<% } %>
</body>
</html>