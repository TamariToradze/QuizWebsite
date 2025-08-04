<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Account" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/ProfilePage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <title>Edit Profile</title>
    <link rel="icon" href="./assets/Logo.png">
    <%--    <link rel="stylesheet" href="./css/StartPage.css">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<header class="animate__animated">
    <div class="logo-area">
        <a href="/HomePageServlet">
            <img src="./assets/Logo.png" alt="Logo">
        </a>
        <span class="website-name">Q<span class="u">u</span><span class="i">i</span>zzz</span>
    </div>
    <div class="nav-bar">
        <ul>
            <li><a href="/HomePageServlet">Quizzes</a></li>
            <li><a href="/ProfileServlet">Profile</a></li>
            <li><a href="/LogoutServlet">Logout</a></li>
        </ul>
    </div>
</header>

<div class="container">
    <form class="form" action="/ProfileServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_method" value="put">
        <div class="form-inner animate__animated animate__zoomIn">

            <div class="profile-picture-container">
                <img src="<%= ((Account) request.getAttribute("account")).getImageUrl() %>" alt="Profile Picture"
                     class="profile-picture">
                <div class="file-input-container">
                    <span>Upload Image</span>
                    <input type="file" name="image" accept="image/*">
                </div>
            </div>
            <div class="profile-info">
                <h2>Edit Profile</h2>
                <p><strong>Username:</strong> <%= ((Account) request.getAttribute("account")).getUserName() %>
                </p>
                <div class="card-input">
                    <label for="firstName">First name</label>
                    <input type="text" id="firstName" name="firstName"
                           value="<%= ((Account) request.getAttribute("account")).getFirstName() %>"/>
                </div>
                <div class="card-input">
                    <label for="lastName">Last Name</label>
                    <input type="text" name="lastName" id="lastName"
                           value="<%= ((Account) request.getAttribute("account")).getLastName() %>"/>
                </div>
                <div class="card-input">
                    <label for="email"> Email </label>
                    <input type="email" id="email" name="email"
                           value="<%= ((Account) request.getAttribute("account")).getEmail() %>"/>
                </div>
            </div>
        </div>

        <button type="submit">Update Profile</button>
    </form>
</div>

</body>
</html>
