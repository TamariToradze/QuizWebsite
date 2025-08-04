document.addEventListener('DOMContentLoaded', function() {
    const questionTemplate = document.querySelector('.question-item');
    const questionsContainer = document.getElementById('questionsContainer');
    const addQuestionBtn = document.getElementById('addQuestion');
    let questionCount = 1;

    // Function to create new question
    function createNewQuestion() {
        questionCount++;
        const newQuestion = questionTemplate.cloneNode(true);
        newQuestion.id = `question${questionCount}`;
        
        // Update input names for new question
        newQuestion.querySelectorAll('input').forEach(input => {
            const name = input.name;
            const newName = name.replace(/\d+/, questionCount);
            input.name = newName;
            input.value = '';
        });

        return newQuestion;
    }

    // Add question button click handler
    addQuestionBtn.addEventListener('click', function() {
        const newQuestion = createNewQuestion();
        questionsContainer.appendChild(newQuestion);
    });
});
