<%@ page import="Controllers.managers.AccountManager" %>
<%@ page import="Models.Account" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accounts List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Accounts List</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Admin</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        AccountManager accountManager = (AccountManager) application.getAttribute(AccountManager.ATTRIBUTE_NAME);
                        if (accountManager != null) {
                            List<Account> accounts = accountManager.getAllAccounts();
                            if (accounts != null) {
                                for (Account account : accounts) {
                    %>
                    <tr>
                        <td><%= account.getUserId() %></td>
                        <td><%= account.getUserName() %></td>
                        <td><%= account.getFirstName() %></td>
                        <td><%= account.getLastName() %></td>
                        <td><%= account.getEmail() %></td>
                        <td><%= account.isAdmin() %></td>
                    </tr>
                    <%
                                }
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <a href="Registration.jsp" class="btn btn-primary">Create New Account</a>
    </div>
</body>
</html>
