<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #121212;
            background-image: url('https://wallpaper.dog/large/20419572.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background-color: rgba(30, 30, 30, 0.95);
            padding: 20px 25px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 300px;
        }

        h1 {
            text-align: center;
            color: #f1f1f1;
            font-size: 20px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 12px;
        }

        label {
            display: block;
            margin-bottom: 4px;
            color: #cccccc;
            font-size: 13px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #666;
            border-radius: 4px;
            font-size: 13px;
            background-color: #222;
            color: #f1f1f1;
            box-sizing: border-box;
        }

        input:focus {
            border-color: #888;
            outline: none;
        }

        button {
            background-color: #888;
            color: white;
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            cursor: pointer;
        }

        button:hover {
            background-color: #666;
        }

        .error-message {
            color: #ff4d4d;
            font-size: 11px;
            margin-top: 4px;
        }

        .link {
            display: block;
            text-align: center;
            margin-top: 12px;
            font-size: 13px;
            color: #bbbbff;
            text-decoration: none;
        }

        .link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Create Account</h1>
    <form action="RegistrationServlet" method="post">
        <div class="form-group">
            <label for="first_name">First Name</label>
            <input type="text" id="first_name" name="first_name" required>
        </div>
        <div class="form-group">
            <label for="last_name">Surname</label>
            <input type="text" id="last_name" name="last_name" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            <div class="error-message">Minimum 8 characters</div>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>
        <button type="submit">Create</button>
    </form>
    <a href="StartPage.html" class="link">Back to Home</a>
</div>
</body>
</html>
