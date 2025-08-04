<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.Question" %>
<%@ page import="utils.CreateQuestion" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Question</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="css/CreateQuestion.css">
</head>
<body>
<div class="container">
    <h1>Create Question</h1>
    <div class="question-form">
        <%
            String generatedUI = (String) request.getAttribute("html");
        %>
        <%= generatedUI %>
    </div>
</div>
</body>
</html>