<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QuizMaster</title>
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Playfair+Display:700" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: "Raleway";
            font-size: 14px;
            font-weight: 500;
            background-color: #BCAAA4;
            -webkit-font-smoothing: antialiased;
        }

        .container {
            padding: 40px 80px;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .card-wrap {
            margin: 10px;
            transform: perspective(800px);
            transform-style: preserve-3d;
            cursor: pointer;
        }

        .card {
            position: relative;
            flex: 0 0 240px;
            width: 240px;
            height: 320px;
            background-color: #333;
            overflow: hidden;
            border-radius: 10px;
        }

        .card-button {
            position: absolute;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
            padding: 15px 30px;
            border: none;
            border-radius: 25px;
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
            font-weight: 500;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .card-button:hover {
            background-color: rgba(255, 255, 255, 0.2);
            transform: translateX(-50%) translateY(-3px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .card-bg {
            opacity: 0.5;
            position: absolute;
            top: -20px; left: -20px;
            width: 100%;
            height: 100%;
            padding: 20px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            pointer-events: none;
        }

        .card-info {
            padding: 20px;
            position: absolute;
            bottom: 0;
            color: #fff;
            transform: translateY(40%);
        }

        .card-info h1 {
            font-family: "Playfair Display";
            font-size: 36px;
            font-weight: 700;
            text-shadow: rgba(0, 0, 0, 0.5) 0 10px 10px;
        }

        .card-info p {
            opacity: 0;
            text-shadow: rgba(0, 0, 0, 1) 0 2px 3px;
        }

        .card-info:after {
            content: '';
            position: absolute;
            top: 0; left: 0;
            width: 100%;
            height: 100%;
            background-image: linear-gradient(to bottom, transparent 0%, rgba(0, 0, 0, 0.6) 100%);
            background-blend-mode: overlay;
            opacity: 0;
            transform: translateY(100%);
        }

        .card-wrap:hover .card-info {
            transform: translateY(0);
        }

        .card-wrap:hover .card-info p {
            opacity: 1;
        }

        .card-wrap:hover .card-info, .card-wrap:hover .card-info p {
            transition: 0.6s cubic-bezier(0.23, 1, 0.32, 1);
        }

        .card-wrap:hover .card-info:after {
            transition: 5s cubic-bezier(0.23, 1, 0.32, 1);
            opacity: 1;
            transform: translateY(0);
        }

        .card-wrap:hover .card-bg {
            transition: 0.6s cubic-bezier(0.23, 1, 0.32, 1), opacity 5s cubic-bezier(0.23, 1, 0.32, 1);
            opacity: 0.8;
        }

        .card-wrap:hover .card {
            transition: 0.6s cubic-bezier(0.23, 1, 0.32, 1), box-shadow 2s cubic-bezier(0.23, 1, 0.32, 1);
            box-shadow:
                rgba(255, 255, 255, 0.2) 0 0 40px 5px,
                rgba(255, 255, 255, 1) 0 0 0 1px,
                rgba(0, 0, 0, 0.66) 0 30px 60px 0,
                inset #333 0 0 0 5px,
                inset #fff 0 0 0 6px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #fff;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: rgba(255, 255, 255, 0.8);
            color: #333;
            font-size: 14px;
        }

        .form-group input:focus {
            outline: none;
            box-shadow: 0 0 0 2px rgba(255, 255, 255, 0.5);
        }

        .form-group button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #fff;
            color: #333;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .form-group button:hover {
            background-color: #f0f0f0;
            transform: translateY(-2px);
        }

        .top-right-buttons {
            position: fixed;
            top: 20px;
            right: 20px;
            display: flex;
            gap: 10px;
        }

        .top-right-buttons button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .top-right-buttons button:hover {
            background-color: #f0f0f0;
        }

        .category-buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
            position: absolute;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%);
        }

        .category-button {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 10px;
            border-radius: 10px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .category-button:hover {
            transform: translateY(-5px);
        }

        .category-button button {
            padding: 20px 40px;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }

        .category-button button span {
            font-size: 24px;
        }
    </style>
</head>
<body>
    <div class="top-right-buttons">
        <button onclick="window.location.href='Authorisation.jsp'">Login</button>
        <button onclick="window.location.href='Registration.jsp'">Create Account</button>
    </div>

    <div class="nav-buttons">
        <a href="Authorisation.jsp" class="nav-button">Login</a>
        <a href="Registration.jsp" class="nav-button">Create Account</a>
    </div>

    <div class="container">
        <div class="content">
            <h1>Welcome to QuizMaster</h1>
            <p>Test your knowledge, challenge your mind</p>
            
            <div class="category-buttons">
            <div class="category-button" style="background-color: #f44336;">
                <button onclick="window.location.href='quiz.jsp?category=art'">
                    <span>🎨</span>
                    <span>Art</span>
                </button>
                <button class="card-button" onclick="window.location.href='quiz.jsp?category=art'">
                    Start Art Quiz
                </button>
            </div>
            <div class="category-button" style="background-color: #2196F3;">
                <button onclick="window.location.href='quiz.jsp?category=sports'">
                    <span>⚽</span>
                    <span>Sports</span>
                </button>
                <button class="card-button" onclick="window.location.href='quiz.jsp?category=sports'">
                    Start Sports Quiz
                </button>
            </div>
            <div class="category-button" style="background-color: #4CAF50;">
                <button onclick="window.location.href='quiz.jsp?category=science'">
                    <span>🔬</span>
                    <span>Science</span>
                </button>
                <button class="card-button" onclick="window.location.href='quiz.jsp?category=science'">
                    Start Science Quiz
                </button>
            </div>
            <div class="category-button" style="background-color: #9C27B0;">
                <button onclick="window.location.href='quiz.jsp?category=movies'">
                    <span>🎬</span>
                    <span>Movies</span>
                </button>
                <button class="card-button" onclick="window.location.href='quiz.jsp?category=movies'">
                    Start Movies Quiz
                </button>
            </div>
        </div>
        </div>
    </div>
</body>
</html>
