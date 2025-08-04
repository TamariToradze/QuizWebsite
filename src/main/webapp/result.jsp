<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Results - QuizMaster</title>
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Playfair+Display:700" rel="stylesheet">
    <style>
        :root {
            --primary-color: #2196F3;
        }

        body {
            font-family: "Raleway";
            margin: 0;
            padding: 20px;
            background-color: #121212;
            background-image: url('https://wallpaper.dog/large/20419572.jpg');
            background-size: cover;
            background-position: center;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .result-container {
            max-width: 800px;
            margin: 0 auto;
            background-color: rgba(30, 30, 30, 0.95);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 100%;
            text-align: center;
        }

        .result-header {
            margin-bottom: 40px;
            color: #f1f1f1;
        }

        .result-header h1 {
            font-family: "Playfair Display";
            font-size: 48px;
            color: #f1f1f1;
            margin: 0;
        }

        .score {
            font-size: 64px;
            font-weight: 700;
            color: var(--primary-color);
            margin: 20px 0;
        }

        .score-label {
            font-size: 24px;
            color: #cccccc;
            margin-bottom: 40px;
        }

        .result-button {
            display: inline-block;
            padding: 15px 40px;
            background-color: var(--primary-color);
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 18px;
            transition: all 0.3s ease;
            margin: 10px;
        }

        .result-button:hover {
            background-color: #1976D2;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
        }

        .progress-bar {
            width: 100%;
            height: 20px;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            overflow: hidden;
            margin: 20px 0;
        }

        .progress {
            height: 100%;
            background-color: var(--primary-color);
            width: ${score * 100 / totalQuestions}%;
            transition: width 0.5s ease;
        }

        .progress-label {
            color: #cccccc;
            font-size: 18px;
            margin-top: 10px;
        }

        .retry-button {
            background-color: #4CAF50;
        }

        .retry-button:hover {
            background-color: #45a049;
        }

        .home-button {
            background-color: #f44336;
        }

        .home-button:hover {
            background-color: #da190b;
        }

        .details-section {
            margin-top: 40px;
            padding-top: 20px;
            border-top: 1px solid rgba(255, 255, 255, 0.1);
        }

        .question-detail {
            margin-bottom: 20px;
            padding: 15px;
            background-color: rgba(30, 30, 30, 0.8);
            border-radius: 8px;
        }

        .question-number {
            color: #cccccc;
            font-size: 16px;
        }

        .question-text {
            color: #f1f1f1;
            font-size: 18px;
            margin: 10px 0;
        }

        .answer {
            display: flex;
            align-items: center;
            margin: 10px 0;
        }

        .answer-icon {
            width: 20px;
            height: 20px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .correct {
            background-color: #4CAF50;
        }

        .incorrect {
            background-color: #f44336;
        }

        .answer-text {
            color: #f1f1f1;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <div class="result-header">
            <h1>Quiz Results</h1>
            <p>${quiz.title}</p>
        </div>

        <div class="score-container">
            <div class="score">${score}</div>
            <div class="score-label">out of ${totalQuestions} questions correct</div>
        </div>

        <div class="progress-bar">
            <div class="progress"></div>
        </div>
        <div class="progress-label">${score * 100 / totalQuestions}% accuracy</div>

        <div class="button-container">
            <a href="/quiz?category=${quiz.category}" class="result-button retry-button">
                Retry Quiz
            </a>
            <a href="/" class="result-button home-button">
                Home
            </a>
        </div>

        <div class="details-section">
            <h2>Question Details</h2>
            <c:forEach var="question" items="${quiz.questions}" varStatus="status">
                <div class="question-detail">
                    <div class="question-number">Question ${status.count}</div>
                    <div class="question-text">${question.text}</div>
                    <c:forEach var="option" items="${question.options}" varStatus="optStatus">
                        <div class="answer">
                            <div class="answer-icon ${selectedOptions[status.index] == optStatus.index && optStatus.index == question.correctAnswerIndex ? 'correct' : selectedOptions[status.index] == optStatus.index ? 'incorrect' : ''}"></div>
                            <div class="answer-text">${option}</div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
