<%@ page import="java.util.List" %>
<%@ page import="Models.LeaderboardEntry" %>
<%@ page import="Models.QuizHistory" %>
<%@ page import="Models.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz Statistics</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="./css/QuizStats.css">
    <link rel="stylesheet" href="./css/StartPage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <main>

        <div class="results">
            <h1>Quiz Statistics</h1>

            <h2>Your Results</h2>
            <ul>
                <li><span class="quiz">Quiz</span> : <%= request.getAttribute("quizName") %>
                </li>
                <li><span class="user">User</span> : <%= request.getAttribute("username") %>
                </li>
                <li><span class="score">Score</span> : <%= request.getAttribute("score") %>
                </li>
                <li><span class="time-taken">Time Taken</span> : <%= request.getAttribute("timeTakenSeconds") %> seconds</li>
                <li><span class="time-taken">Time taken (global average): </span> : <%= request.getAttribute("avgTime") %> seconds</li>
                <li><span class="time-taken">Score (global average): </span> : <%= request.getAttribute("avgTime") %></li>
            </ul>
        </div>

        <div class="stats">

            <div class="quiz-history">
                <h2>Personal Quiz History</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Score</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Elapsed Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<QuizHistory> quizHistory = (List<QuizHistory>) request.getAttribute("personalHistory");
                        if (quizHistory != null) {
                            for (QuizHistory history : quizHistory) { %>
                    <tr>
                        <td><%= history.getQuizScore() %>
                        </td>
                        <td><%= history.getStartTime() %>
                        </td>
                        <td><%= history.getEndTime() %>
                        </td>
                        <td><%= history.getElapsedTime() %>
                        </td>
                    </tr>
                    <% }
                    } else { %>
                    <tr>
                        <td colspan="5">No quiz history available.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <div class="leaderboard">
                <h2>Leaderboard</h2>
                <table class="leaderboard">
                    <thead>
                    <tr>
                        <th>Rank</th>
                        <th>Username</th>
                        <th>Score</th>
                        <th>Time Taken</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) request.getAttribute("leaderboard");
                        if (leaderboard != null) {
                            int rank = 1;
                            for (LeaderboardEntry entry : leaderboard) { %>
                    <tr>
                        <td><%= rank %>
                        </td>
                        <td><%= entry.getUsername() %>
                        </td>
                        <td><%= entry.getScore() %>
                        </td>
                        <td><%= entry.getElapsedTime() %> seconds</td>
                    </tr>
                    <% rank++;
                    }
                    } else { %>
                    <tr>
                        <td colspan="4">No leaderboard data available.</td>
                    </tr>
                    <% }
                        Question question = new Question();%>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    <div class="actions">
        <a href="/HomePageServlet">Go Home</a>
        <a href="QuizServlet?quizId=<%= request.getAttribute("quizId") %>"> Retake Quiz</a>
        <%--        <form action="QuizServlet" method="post">--%>
        <%--            <button class="retake" type="submit">Retake Quiz</button>--%>
        <%--            <input type="hidden" name="quizId" value="<%= question.getQuizId() %>">--%>
        <%--        </form>--%>
    </div>
</div>
</body>
</html>