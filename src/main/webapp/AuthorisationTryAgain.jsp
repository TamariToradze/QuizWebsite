
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please Try Again</title>
    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #121212;
            background-image: url('https://wallpaper.dog/large/20419572.jpg');
            background-size: cover;
            background-position: center;
        }

        .container {
            background-color: rgba(30, 30, 30, 0.95);
            padding: 20px 25px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 340px;
            border: 1px solid rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
        }

        .form-container {
            display: inline-block;
            text-align: left;
            margin-top: 20px;
        }

        h1 {
            text-align: center;
            color: #f1f1f1;
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 12px;
        }

        .error-message {
            color: #ff4c4c;
            margin-bottom: 20px;
            font-size: 14px;
        }
        .create-link {
            color: #b497ff;
            font-size: 13px;
            text-decoration: none;
            display: inline-block;
            margin-top: 15px;
        }

        .create-link:hover {
            color: #d0bcff;
            text-decoration: underline;
        }

        .password-container {
            position: relative;
            width: 100%;
        }

        input[type="text"],
        input[type="username"],
        input[type="password"] {
            width: 100%;
            padding: 14px;
            border: 1px solid #666;
            border-radius: 6px;
            font-size: 16px;
            background-color: #222;
            color: #f1f1f1;
            box-sizing: border-box;
            margin-bottom: 18px;
        }

        .password-container input[type="password"],
        .password-container input[type="text"] {
            padding-right: 35px;
            margin-bottom: 15px;
        }

        .eye-icon {
            position: absolute;
            right: 10px;
            top: 38%;
            transform: translateY(-50%);
            cursor: pointer;
            user-select: none;
            transition: all 0.2s ease;
            line-height: 1;
            height: 16px;
            width: 16px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .eye-icon svg {
            width: 16px;
            height: 16px;
            fill: none;
            stroke: #999;
            stroke-width: 2;
            stroke-linecap: round;
            stroke-linejoin: round;
            transition: stroke 0.2s ease;
        }

        .eye-icon:hover svg {
            stroke: #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #f14444, #c73333);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background: linear-gradient(135deg, #ff5555, #e04444);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(241, 68, 68, 0.4);
        }

        label {
            display: block;
            margin-bottom: 6px;
            color: #cccccc;
            font-size: 14px;
            font-weight: 500;
        }
    </style>
</head>
<body>

<div class="container">

    <h1>Please Try Again</h1>

    <p class="error-message">Either your username or password is incorrect.</p>

    <div class="form-container">
        <form action="Login" method="post">
            <label>Username:</label>
            <input type="text" name="username"/>

            <label>Password:</label>
            <div class="password-container">
                <input type="password" name="password" id="password"/>
                <span class="eye-icon" onclick="togglePassword()">
                    <svg viewBox="0 0 24 24">
                        <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                        <line x1="1" y1="1" x2="23" y2="23"/>
                    </svg>
                </span>
            </div>

            <input type="submit" value="Login"/>
        </form>
    </div>

    <br>
    <a class="create-link" href="Registration.jsp">Create New Account</a>
</div>

<script>
    function togglePassword() {
        const passwordInput = document.getElementById('password');
        const eyeIcon = document.querySelector('.eye-icon');

        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            eyeIcon.innerHTML = `
            <svg viewBox="0 0 24 24">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                <circle cx="12" cy="12" r="3"/>
            </svg>
        `;
        } else {
            passwordInput.type = 'password';
            eyeIcon.innerHTML = `
            <svg viewBox="0 0 24 24">
                <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                <line x1="1" y1="1" x2="23" y2="23"/>
            </svg>
        `;
        }
    }
</script>

</body>
</html>