<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${quiz.title}</title>
    <style>
        .quiz-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .category-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .question {
            display: none;
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .question.current {
            display: block;
        }
        .question-number {
            font-weight: bold;
            margin-bottom: 10px;
        }
        .question-text {
            font-size: 1.2em;
            margin-bottom: 15px;
        }
        .options {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .option {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .option:hover {
            background-color: #f5f5f5;
        }
        .option.selected {
            background-color: #e3f2fd;
        }
        .next-btn {
            padding: 10px 20px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
        }
        .next-btn:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
<div class="quiz-container">
    <div class="category-header">
        <h1>${quiz.title}</h1>
        <p>${quiz.description}</p>
    </div>

    <div class="question current">
        <div class="question-number">Question ${questionNumber}</div>
        <div class="question-text">${question.text}</div>
        <div class="options">
            <c:forEach var="option" items="${question.options}" varStatus="status">
                <div class="option" onclick="selectOption(this, ${status.index})">${option}</div>
            </c:forEach>
        </div>
        <button class="next-btn" onclick="nextQuestion()" disabled>Next Question</button>
    </div>
</div>

<script>
    let selectedOption = null;

    function selectOption(element, index) {
        // Remove previous selection
        if (selectedOption) {
            selectedOption.classList.remove('selected');
        }
        
        // Update new selection
        selectedOption = element;
        selectedOption.classList.add('selected');
        
        // Enable next button
        const nextBtn = document.querySelector('.next-btn');
        nextBtn.disabled = false;
        
        // Send answer to server
        fetch('/quiz', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'action=answer&option=' + index
        });
    }

    function nextQuestion() {
        // Send next request to server
        fetch('/quiz', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'action=next'
        })
        .then(response => response.text())
        .then(html => {
            document.querySelector('.quiz-container').innerHTML = html;
        });
    }
</script>
</body>
</html>
