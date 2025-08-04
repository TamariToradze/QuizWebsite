<%@ page import="java.util.*" %>
<%@ page import="Models.Question" %>
<%@ page import="Models.Enums.QuestionType" %>
<%@ page import="utils.TakeSinglePageQuiz" %>

<jsp:useBean id="takeQuiz" class="utils.TakeSinglePageQuiz" />

<html>announcements
<head>
    <title>Single Page Quiz</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/SinglePageQuiz.css">
</head>
<body>
<div class="quiz-container">
    <h1><%= ((Models.Quiz) request.getAttribute("currentQuiz")).getQuizName() %></h1>
    <form action="QuestionServlet" method="post" onsubmit="prepareUserAnswers(event)">
        <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>">

        <%
            List<Question> questions = (List<Question>) request.getAttribute("questions");
            for (Question question : questions) {
                String questionHtml = takeQuiz.generateUI(question.getQuestionType(), question, false);
        %>
        <div class="singleKitxva">
            <h2>Question <%= question.getQuestionId() %></h2>
            <div class="question-html"><%= questionHtml %></div>
            <ul class="answers">
                <%-- Generate the answer fields here based on the question type --%>
            </ul>
            <input type="hidden" name="questionId_<%= question.getQuestionId() %>" value="<%= question.getQuestionId() %>">
        </div>
        <%
            }
        %>

        <button type="submit" class="btn">Submit</button>
        <input type="hidden" id="userAnswers" name="userAnswers">
    </form>
</div>

<script>
    let selectedQuestion = null;
    let selectedAnswer = null;
    let colorIndex = 0;

    const colors = [
        "#007138", "#722c61", "#327D7D", "#de7136", "#42B191",
        "#c29c59", "#30aba7", "#9cea65", "#b64a68", "#F79FF8",
        "#3075C1", "#6c04c2", "#409CD6", "rgba(206,51,23,0.81)", "#60B6E1",
        "rgba(26,96,67,0.69)", "#99D2BA", "#56f106", "#6348ab", "#B77CD2",
        "rgba(41,115,57,0.8)", "#2E5FE8", "#45B8E8", "#bd2b49", "#72D7F5",
        "#ea3d27", "#3b93ad", "#042498", "#a26648", "#409126",
        "#fc6916", "#fad20b", "#FC9240", "rgba(133,220,181,0.71)", "#FCA0A9",
        "rgba(52,67,232,0.8)", "#2E5FE8", "#45B8E8", "#3d348b", "#72D7F5",
        "#ea3d27", "#3b93ad", "#042498", "rgba(119,128,28,0.6)", "#FED8F1",
        "#e6af2e", "#fad20b", "#00b295", "rgba(33,104,128,0.49)", "#FCA0A9"
    ];

    let matchingPairs = {};

    function selectQuestion(id) {
        selectedQuestion = document.getElementById(id);
        checkMatch();
    }

    function selectAnswer(id) {
        selectedAnswer = document.getElementById(id);
        checkMatch();
    }

    function checkMatch() {
        if (selectedQuestion && selectedAnswer) {
            colorIndex = Math.ceil(Math.random() * colors.length);
            selectedQuestion.style.backgroundColor = colors[colorIndex];
            selectedAnswer.style.backgroundColor = colors[colorIndex];
            selectedQuestion.style.borderColor = colors[colorIndex];
            selectedAnswer.style.borderColor = colors[colorIndex];
            // Get question and answer texts
            let questionText = selectedQuestion.innerText;
            let answerText = selectedAnswer.innerText;
            // Add to matchingPairs hashmap
            matchingPairs[questionText] = answerText;
            let matchingPairsJson = JSON.stringify(matchingPairs);
            console.log(matchingPairs);
            document.getElementById('userAnswers').value = matchingPairsJson;
            localStorage.setItem("json", matchingPairsJson)
            selectedQuestion = null;
            selectedAnswer = null;
        }
    }

    function prepareUserAnswers(event) {
        const form = event.target;
        <%
            for (Question question : questions) {
                String questionType = question.getQuestionType().toString();
                int questionId = question.getQuestionId();
        %>
        if ("<%= questionType %>" === 'MULTIPLE_CHOICE' || "<%= questionType %>" === 'MULTIPLE_CHOICE_WITH_ANSWERS') {
            const selectedAnswers = [];
            const checkboxes = form.querySelectorAll('input[name="userAnswer_<%= questionId %>"]:checked');
            checkboxes.forEach((checkbox) => { selectedAnswers.push(checkbox.value); });
            form.querySelector('input[name="userAnswers_<%= questionId %>"]').value = JSON.stringify(selectedAnswers);
        } else if ("<%= questionType %>" === 'MATCHING') {
            form.querySelector('input[name="userAnswers_<%= questionId %>"]').value = localStorage.getItem("json");
        } else if ("<%= questionType %>" === 'MULTI_ANSWER') {
            const answers = [];
            const inputs = form.querySelectorAll('input[name="userAnswer_<%= questionId %>"]');
            inputs.forEach((input) => { answers.push(input.value); });
            console.log(answers);
            form.querySelector('input[name="userAnswers_<%= questionId %>"]').value = JSON.stringify(answers);
        }
        <%
            }
        %>
    }
</script>
</body>
</html>