<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">My App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="profile">Profile</a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto">
                <c:if test="${sessionScope.currentUser != null}">
                    <li class="nav-item">
                        <span class="nav-link">Welcome, ${currentUser.firstName}!</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.currentUser == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Authorisation.jsp">Login</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
