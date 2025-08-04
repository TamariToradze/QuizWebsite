<%--
<%@ page import="Models.Account"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="./assets/Logo.png">

    <link rel="stylesheet" href="./css/HomePage.css">
    <link rel="stylesheet" href="./css/StartPage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <link rel="stylesheet" href="./css/SearchResultPage.css">


    <meta charset="UTF-8">
    <title>Search Results</title>

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
            <li><a href="/HomePageServlet">Quizzies</a></li>
            <li><a href="/ProfileServlet">Profile</a></li>
            <li><a href="/LogoutServlet">Logout</a></li>
        </ul>
    </div>
</header>

<h1>Search Results</h1>
<%
    // Get the query parameter from the request
    String query = request.getParameter("query");

    // Get the search results from the request scope
    List<Account> results = (List<Account>) request.getAttribute("results");

    // Check if query is not null and not empty
    if (query != null && !query.trim().isEmpty()) {
        out.println("<p>You searched for: " + query + "</p>");
        if (results != null && !results.isEmpty()) {
            out.println("<ul>");
            for (Account account : results) {
                out.println("<li class='each-account'>");
                out.println("<img src='" + account.getImageUrl() + "' alt='Image of " + account.getFirstName() + " " + account.getLastName() + "'> ");
                out.println("<div class='account-info'>");
                out.println(account.getFirstName() + " " + account.getLastName() + " (" + account.getUserName() + ")");
                out.println("</div>");
                out.println("<input type=\"hidden\" name=\"username\" value="+account.getUserName()+"\">");
                out.println("<a href='ProfileServlet?username=" + account.getUserName() + "'><button class='profile-button'>See Profile</button></a>");
                out.println("</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No results found for your query.</p>");
        }
    } else {
        out.println("<p>Please enter a search query.</p>");
    }
%>
</body>
</html>