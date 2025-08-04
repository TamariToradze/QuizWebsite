<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - QuizMaster</title>
    <style>
        :root {
            --bg-color: #121212;
            --text-color: #f1f1f1;
            --box-bg: rgba(30, 30, 30, 0.9);
            --input-bg: #222;
            --input-border: #666;
            --button-bg: #888;
            --button-hover: #666;
            --link-color: #bbbbff;
        }

        body.light-mode {
            --bg-color: #ffffff;
            --text-color: #000000;
            --box-bg: rgba(255, 255, 255, 0.3);
            --input-bg: #ffffff;
            --input-border: #cccccc;
            --button-bg: #4CAF50;
            --button-hover: #45a049;
            --link-color: #0000ff;
            background-image: url('https://i.pinimg.com/736x/77/62/47/776247f038d2bdc9b240a4d08f8670ca.jpg');
        }

        body {
            font-family: Arial, sans-serif;
            background-color: var(--bg-color);
            background-image: url('https://wallpaper.dog/large/20419572.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            -webkit-font-smoothing: antialiased;
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: var(--text-color);
            transition: background-image 0.3s ease;
        }

        .login-box {
            background-color: var(--box-bg);
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 350px;
            transition: background-color 0.3s ease;
        }

        .login-box h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #f1f1f1;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #e0e0e0;
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #666;
            border-radius: 4px;
            font-size: 14px;
            background-color: #222;
            color: #f1f1f1;
        }

        .form-group input:focus {
            border-color: #888;
            outline: none;
        }

        .login-button {
            width: 100%;
            padding: 10px;
            background-color: #888;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        .login-button:hover {
            background-color: #666;
        }

        .error-message {
            color: #ff4d4d;
            font-size: 14px;
            margin-top: 10px;
            text-align: center;
        }

        .link {
            display: block;
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
            color: #bbbbff;
            text-decoration: none;
        }

        .link:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        // Apply theme from localStorage
        window.onload = function() {
            const savedTheme = localStorage.getItem('theme');
            if (savedTheme === 'light') {
                document.body.classList.add('light-mode');
            }
        };
    </script>
</head>
<body>
<div class="login-box">

    <h2>Login to QuizMaster</h2>
    <form action="/Login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required />
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />
        </div>

        <div class="error-message">
            <% if (request.getAttribute("error") != null) { %>
            <%= request.getAttribute("error") %>
            <% } %>
        </div>

        <button type="submit" class="login-button">Login</button>
    </form>

    <a href="Registration.jsp" class="link">Create New Account</a>
    <a href="StartPage.html" class="link">Back to Home</a>
</div>
</body>
</html>
