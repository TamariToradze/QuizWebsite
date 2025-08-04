<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="Models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Achievement" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/NavBar.css?v=2.0">
    <link rel="stylesheet" href="./css/ProfilePage.css">
    <title>User Profile</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #121212;
            background-image: url('https://wallpaper.dog/large/20419572.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            min-height: 100vh;
            color: #f1f1f1;
        }

        .container {
            background-color: rgba(30, 30, 30, 0.95);
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 900px;
            max-width: 95%;
            margin: 20px auto;
        }

        .top {
            display: grid;
            grid-template-columns: 300px 1fr;
            gap: 30px;
            margin-bottom: 30px;
        }

        .profile-picture-container {
            text-align: center;
        }

        .profile-picture {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            border: 3px solid #888;
            object-fit: cover;
        }

        .profile-info {
            background-color: rgba(40, 40, 40, 0.8);
            padding: 20px;
            border-radius: 6px;
        }

        .profile-info h2 {
            color: #f1f1f1;
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }

        .profile-info p {
            color: #cccccc;
            margin: 10px 0;
        }

        .profile-info .val {
            color: #f1f1f1;
            margin-left: 10px;
        }

        .submit {
            background-color: #cc4444;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
            margin-top: 10px;
            width: 100%;
        }

        .submit:hover {
            background-color: #aa3333;
        }

        .achievements {
            background-color: rgba(40, 40, 40, 0.8);
            padding: 20px;
            border-radius: 6px;
            margin-top: 20px;
        }

        .achievements h3 {
            color: #f1f1f1;
            text-align: center;
            margin-bottom: 15px;
        }

        .inner {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
            gap: 15px;
        }

        .achievement {
            text-align: center;
        }

        .achievementImg {
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }

        .bottom {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 30px;
        }

        .quizzes, .friends {
            background-color: rgba(40, 40, 40, 0.8);
            padding: 20px;
            border-radius: 6px;
        }

        .quizzes h3, .friends h3 {
            color: #f1f1f1;
            text-align: center;
            margin-bottom: 15px;
        }

        .quizzes ul, .friends ul {
            list-style: none;
            padding: 0;
        }

        .quizzes a, .friends a {
            color: #bbbbff;
            text-decoration: none;
            display: block;
            padding: 8px 0;
            border-bottom: 1px solid #555;
        }

        .quizzes a:hover, .friends a:hover {
            text-decoration: underline;
        }

        .modal-content {
            background-color: rgba(40, 40, 40, 0.95);
            color: #f1f1f1;
            border-radius: 6px;
        }

        @media (max-width: 768px) {
            .top {
                grid-template-columns: 1fr;
            }

            .bottom {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<%
    Account account = (Account) request.getAttribute("account");
    String currentUsername = (String) request.getSession().getAttribute("username");
    boolean isSelf = (Boolean) request.getAttribute("isSelf");
    boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
    List<Quiz> quizList = (List<Quiz>) request.getAttribute("quizList");
    List<String> friendsList = (List<String>) request.getAttribute("friendsList");
    boolean isFriend = friendsList.contains(currentUsername);
    Set<Achievement> achievementList = (Set<Achievement>) request.getAttribute("achievementList");
%>
<header class="animate__animated">
    <div class="nav-bar">
        <ul>
            <li><a href="/LogoutServlet">Logout</a></li>
        </ul>
    </div>
</header>

<div class="container">
    <div class="top">
        <div class="profile-picture-container animate__animated animate__jackInTheBox">
            <img src="<%= ((Account) request.getAttribute("account")).getImageUrl() %>" alt="Profile Picture"
                 class="profile-picture">
        </div>
        <div class="profile-info selected animate__animated animate__fadeIn">
            <input type="hidden" id="receiverId"
                   value="<%= ((Account) request.getAttribute("account")).getUserName() %>">
            <h2>User Profile</h2>
            <p>
                <strong>Name:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getFirstName() %></span>
            </p>
            <p>
                <strong>Last name:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getLastName() %></span>
            </p>
            <p>
                <strong>Username:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getUserName() %></span>
            </p>
            <p>
                <strong>Email:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getEmail() %></span>
            </p>
            <% if (isSelf) { %>
            <form action="ProfileServlet" method="post">
                <button type="submit" name="action" value="editProfile" class="submit">Edit Profile</button>
            </form>
            <% } else { %>
            <button type="button" class="submit" id="sendNote">Send Note</button>
            <button type="button" class="submit" id="challenge">Challenge</button>
            <% if (!isFriend) { %>
            <button type="submit" class="submit" id="addFriend">Add Friend</button>
            <% } %>

            <div id="challengeModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <label for="quizInput">Quiz Link</label><textarea id="quizInput" rows="2" cols="50"></textarea>
                    <div></div>
                    <label for="scoreInput">High Score</label><textarea id="scoreInput" rows="2" cols="50"></textarea>
                    <div></div>
                    <button type="submit" id="sendQuizBtn">Send</button>
                </div>
            </div>

            <div id="noteModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <label for="messageInput">Write your message: </label><textarea id="messageInput" rows="4"
                                                                                    cols="50"></textarea>
                    <div></div>
                    <button type="submit" id="sendNoteBtn">Send</button>
                </div>
            </div>
            <% } %>
            <% if (isAdmin && !isSelf) { %>
            <form action="ProfileServlet" method="post" id="deleteProfileForm">
                <input type="hidden" name="action" value="deleteProfile">
                <input type="hidden" name="username" value="<%= account.getUserName() %>">
                <!-- Make sure you have 'user' object with the 'username' field -->
                <button type="submit" class="submit" id="deleteProfileButton">Delete User</button>
            </form>

            <form action="ProfileServlet" method="post" id="makeAdmin">
                <input type="hidden" name="action" value="makeAdmin">
                <input type="hidden" name="username" value="<%= account.getUserName() %>">
                <!-- Make sure you have 'user' object with the 'username' field -->
                <button type="submit" class="submit" id="makeAdminButton">Make Admin</button>
            </form>

            <% } %>


        </div>

        <div class="achievements animate__animated animate__fadeInRight">
            <h3>Achievements</h3>
            <div class="inner">
                <%--
                    for (Achievement achievement : achievementList) {
                %>
                <div class="achievement tooltip">
                    <img class="achievementImg" src="<%=achievement.getAchievementUrl()%>" alt="">
                    <p><%=achievement.getAchievementName()%>
                    </p>
                    <span class="tooltiptext"><%=achievement.getAchievementDescription()%></span>
                </div>
                <%
                    }
                --%>
            </div>
        </div>
    </div>
    <div class="bottom animate__animated animate__fadeInUp">
        <div class="quizzes">
            <h3>Quizzes Created</h3>
            <ul>
                <%
                    for (Quiz quiz : quizList) {
                %>
                <li><a href="QuizServlet?quizId=<%= quiz.getQuizID()%>"><%= quiz.getQuizName() %>
                </a></li>
                <%
                    }
                %>
            </ul>

        </div>

        <div class="friends">
            <h3>Friends</h3>
            <ul>
                <%
                    for (String friend : friendsList) {
                %>
                <li><a href="ProfileServlet?username=<%= friend %>"><%= friend %>
                </a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<script src="javascript/SendNotification.js" defer></script>

<script>
    document.getElementById('deleteProfileButton').onclick = function (event) {
        event.preventDefault(); // Prevent the form from submitting the traditional way

        fetch('ProfileServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                action: 'deleteProfile',
                username: '<%= account.getUserName() %>' // Assuming you have the username available in JSP
            })
        })
            .then(response => {
                console.log(response)
                if (response.ok) {
                    // Redirect to HomePageServlet
                    window.location.href = 'HomePageServlet';
                } else {
                    alert('Failed to delete profile. Please try again.');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('An error occurred while deleting the profile. Please try again.');
            });
    }
</script>
</body>
</html>