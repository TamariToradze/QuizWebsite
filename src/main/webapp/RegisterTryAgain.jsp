<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please enter another user name and password</title>
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
            width: 300px;
            border: 1px solid rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            text-align: left;
        }

        h1 {
            text-align: center;
            color: #f14444;
            font-size: 18px;
            margin-bottom: 10px;
            text-shadow: 0 0 10px rgba(241, 68, 68, 0.3);
        }

        p {
            text-align: center;
            color: #cccccc;
            font-size: 14px;
            margin-bottom: 20px;
            line-height: 1.4;
        }

        label {
            display: block;
            margin-bottom: 6px;
            color: #cccccc;
            font-size: 14px;
            font-weight: 500;
        }

        input[type="text"],
        input[type="username"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #666;
            border-radius: 4px;
            font-size: 13px;
            background-color: #222;
            color: #f1f1f1;
            box-sizing: border-box;
            margin-bottom: 15px;
        }

        input:focus {
            border-color: #888;
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

        .password-container {
            position: relative;
            width: 100%;
        }

        .eye-icon {
            position: absolute;
            right: 10px;
            top: 35%;
            transform: translateY(-50%);
            cursor: pointer;
            user-select: none;
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

    </style>
</head>
<body>

<div class="container">
    <h1>The name <%= request.getParameter("username") %> is already in use</h1>
    <p>Please enter another username and password.</p>

    <form action="AccountCreation" method="post">
        <label>Username:</label>
        <input type="text" name="username" />

        <label>Password:</label>
        <div class="password-container">
            <input type="password" name="password" id="password" />
            <span class="eye-icon" onclick="togglePassword('password', this)">
            <svg viewBox="0 0 24 24">
                <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                <line x1="1" y1="1" x2="23" y2="23"/>
            </svg>
        </span>
        </div>

        <label>Confirm Password:</label>
        <div class="password-container">
            <input type="password" name="confirmPassword" id="confirmPassword" />
            <span class="eye-icon" onclick="togglePassword('confirmPassword', this)">
            <svg viewBox="0 0 24 24">
                <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                <line x1="1" y1="1" x2="23" y2="23"/>
            </svg>
        </span>
        </div>

        <input type="submit" value="Create" />
    </form>
</div>

<script>
    function togglePassword(fieldId, iconElement) {
        const input = document.getElementById(fieldId);

        if (input.type === "password") {
            input.type = "text";
            iconElement.innerHTML = `
                <svg viewBox="0 0 24 24">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                    <circle cx="12" cy="12" r="3"/>
                </svg>
            `;
        } else {
            input.type = "password";
            iconElement.innerHTML = `
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
