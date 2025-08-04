<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Logout - QuizMaster</title>
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

        .logout-box {
            background-color: var(--box-bg);
            padding: 40px 50px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 350px;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .logout-box h2 {
            margin-bottom: 20px;
            color: #f1f1f1;
            font-size: 28px;
        }

        .success-message {
            color: #4CAF50;
            font-size: 18px;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .logout-message {
            color: var(--text-color);
            font-size: 16px;
            margin-bottom: 30px;
            line-height: 1.5;
        }

        .link {
            display: inline-block;
            margin: 10px 15px;
            padding: 12px 20px;
            background-color: var(--button-bg);
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }

        .link:hover {
            background-color: var(--button-hover);
            text-decoration: none;
        }

        .link.primary {
            background-color: #4CAF50;
        }

        .link.primary:hover {
            background-color: #45a049;
        }

        .links-container {
            display: flex;
            flex-direction: column;
            gap: 10px;
            margin-top: 20px;
        }

        .links-row {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        @media (max-width: 480px) {
            .links-row {
                flex-direction: column;
                align-items: center;
            }

            .link {
                margin: 5px 0;
                width: 200px;
            }
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
<div class="logout-box">
    <h2>QuizMaster</h2>

    <div class="success-message">
        ✓ You have been logged out successfully!
    </div>

    <div class="logout-message">
        Thank you for using QuizMaster. Your session has been ended securely.
    </div>

    <div class="links-container">
        <div class="links-row">
            <a href="Authorisation.jsp" class="link primary">Login Again</a>
            <a href="Registration.jsp" class="link">Create Account</a>
        </div>
        <div class="links-row">
            <a href="StartPage.html" class="link">Back to Home</a>
        </div>
    </div>
</div>
</body>
</html>