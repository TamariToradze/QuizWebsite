<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title><c:out value="${quiz.title}" /> - QuizMaster</title>
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,700|Playfair+Display:700" rel="stylesheet">
    <style>
        :root {
            --blur-color: rgba(0, 0, 0, 0.8);
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

        .quiz-container {
            max-width: 800px;
            margin: 0 auto;
            background-color: rgba(30, 30, 30, 0.95);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.4);
            width: 100%;
        }

        .category-header {
            text-align: center;
            margin-bottom: 40px;
            color: #f1f1f1;
        }

        .category-header h1 {
            font-family: "Playfair Display";
            font-size: 36px;
            color: #f1f1f1;
            margin: 0;
        }

        .category-header p {
            color: #cccccc;
            font-size: 18px;
            margin: 10px 0 0;
        }

        .question {
            margin-bottom: 30px;
            padding: 20px;
            border-radius: 8px;
            transition: all 0.3s ease;
            background-color: rgba(30, 30, 30, 0.9);
            color: #f1f1f1;
            opacity: 0.7;
            filter: blur(2px);
            pointer-events: none;
        }

        .question.current {
            background-color: rgba(30, 30, 30, 0.95);
            transform: scale(1.02);
            opacity: 1;
            filter: none;
            pointer-events: all;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .question-number {
            font-size: 18px;
            color: #cccccc;
            margin-bottom: 10px;
        }

        .question-text {
            font-size: 24px;
            font-weight: 600;
            color: #f1f1f1;
            margin-bottom: 20px;
        }

        .options {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .option {
            padding: 15px 20px;
            border: 2px solid #444;
            border-radius: 6px;
            cursor: pointer;
            transition: all 0.3s ease;
            color: #f1f1f1;
        }

        .option:hover {
            background: rgba(255, 255, 255, 0.1);
            border-color: #666;
        }

        .option.selected {
            background: #2196F3;
            border-color: #2196F3;
            color: white;
        }

        .next-btn {
            display: none;
            padding: 15px 30px;
            background: #2196F3;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
            width: 100%;
            transition: all 0.3s ease;
        }

        .next-btn:hover {
            background: #1976D2;
        }

        .next-btn.show {
            display: block;
        }

        .full-quiz-button {
            margin-top: 30px;
            text-align: center;
        }

        .full-quiz-button a {
            display: inline-block;
            padding: 15px 30px;
            border-radius: 25px;
            background-color: #888;
            color: white;
            text-decoration: none;
            font-weight: 500;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .full-quiz-button a:hover {
            background-color: #666;
            transform: translateY(-3px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
        }

        .error-message {
            color: #ff4444;
            text-align: center;
            padding: 20px;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="quiz-container">
        <div class="category-header">
            <h1><c:out value="${quiz.title}" /></h1>
            <p><c:out value="${quiz.description}" /></p>
        </div>

        <form action="quiz" method="post">
            <input type="hidden" name="category" value="${quiz.category}">
            
            <c:forEach var="question" items="${quiz.questions}" varStatus="status">
                <div class="question ${status.index == 0 ? 'current' : ''}">
                    <div class="question-number">Question ${status.count}</div>
                    <div class="question-text">${question.text}</div>
                    <div class="options">
                        <c:forEach var="option" items="${question.options}" varStatus="optionStatus">
                            <div class="option" onclick="selectOption(this, ${status.index})">
                                ${option}
                            </div>
                        </c:forEach>
                    </div>
                    <button class="next-btn" onclick="nextQuestion()">Next Question</button>
                </div>
            </c:forEach>
            <c:if test="${empty quiz.questions}">
                <div class="error-message">
                    <p>No questions available for this category</p>
                </div>
            </c:if>

        <div class="full-quiz-button">
            <a href="Authorisation.jsp">Log In to Access Full Quiz</a>
        </div>
    </div>

    <script>
        let currentQuestion = 0;
        let selectedOptions = [];
        const questions = document.querySelectorAll('.question');

        function selectOption(optionElement, questionIndex) {
            if (!questions[questionIndex].classList.contains('current')) return;
            
            const currentQuestionElement = questions[questionIndex];
            const options = currentQuestionElement.querySelectorAll('.option');
            options.forEach(option => option.classList.remove('selected'));
            optionElement.classList.add('selected');
            
            selectedOptions[questionIndex] = Array.from(options).indexOf(optionElement);
            
            const nextBtn = currentQuestionElement.querySelector('.next-btn');
            nextBtn.classList.add('show');
        }

        function nextQuestion() {
            const currentQuestionElement = questions[currentQuestion];
            currentQuestionElement.classList.remove('current');

            currentQuestion++;
            if (currentQuestion < questions.length) {
                const nextQuestionElement = questions[currentQuestion];
                nextQuestionElement.classList.add('current');
                
                const nextBtn = nextQuestionElement.querySelector('.next-btn');
                nextBtn.classList.remove('show');

                if (selectedOptions[currentQuestion] !== null) {
                    const options = nextQuestionElement.querySelectorAll('.option');
                    options[selectedOptions[currentQuestion]].classList.add('selected');
                    nextBtn.classList.add('show');
                }
            } else {
                alert('Quiz completed!');
                window.location.href = 'Authorisation.jsp';
            }
        }

        window.addEventListener('DOMContentLoaded', () => {
            if (questions.length > 0) {
                questions[0].classList.add('current');
                if (selectedOptions[0] !== null) {
                    const options = questions[0].querySelectorAll('.option');
                    options[selectedOptions[0]].classList.add('selected');
                    questions[0].querySelector('.next-btn').classList.add('show');
                }
            }
        });
    </script>
</body>
</html>
