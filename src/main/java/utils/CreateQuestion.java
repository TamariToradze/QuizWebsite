package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;

public class CreateQuestion {

    public String generateUI(QuestionType questionType) {
        switch (questionType) {
            case QUESTION_RESPONSE:
                return generateQuesRes();
            case FILL_IN_THE_BLANK:
                return generateFillBlank();
            case MULTIPLE_CHOICE:
                return generateMultiChoice();
            case PICTURE_RESPONSE:
                return generatePictRes();
            case MULTI_ANSWER:
                return generateMultiAns();
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                return generateMultiChoiceAns();
            case MATCHING:
                return generateMatching();
            default:
                System.out.println("Invalid question type");
                return "";
        }
    }

    private String generateQuesRes() {
        return "<form action=\"CreateQuestionServlet\" method=\"post\">"
                + "<input type=\"hidden\" name=\"questionType\" value=\"QUESTION_RESPONSE\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">"
                + "<input type=\"text\" name=\"answerText\" placeholder=\"Type correct answer here\">"
                + "<button class=\"btn\" type=\"submit\">Save Question</button>"
                + "</form>";
    }

    private String generateFillBlank() {
        return "<form action=\"CreateQuestionServlet\" method=\"post\">"
                + "<input type=\"hidden\" name=\"questionType\" value=\"FILL_IN_THE_BLANK\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">"
                + "<input type=\"text\" name=\"answerText\" placeholder=\"Type correct answer here\">"
                + "<button class=\"btn\" type=\"submit\">Save Question</button>"
                + "</form>";
    }

    private String generateMultiChoice() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTIPLE_CHOICE\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">")
                .append("<input type=\"text\" name=\"answer1\" placeholder=\"Type answer 1 here\">")
                .append("<input type=\"text\" name=\"answer2\" placeholder=\"Type answer 2 here\">")
                .append("<input type=\"text\" name=\"answer3\" placeholder=\"Type answer 3 here\">")
                .append("<input type=\"text\" name=\"answer4\" placeholder=\"Type answer 4 here\">")
                .append("<input type=\"text\" name=\"correctIndexes\" placeholder=\"Type the index of the correct answer(s) separated by commas\">")
                .append("<button type=\"submit\">Save Question</button>")
                .append("</form>");

        return formBuilder.toString();
    }

    private String generatePictRes() {
        return "<form action=\"CreateQuestionServlet\" method=\"post\">"
                + "<input type=\"hidden\" name=\"questionType\" value=\"PICTURE_RESPONSE\">"
                + "<input type=\"text\" name=\"questionImage\" placeholder=\"Type your Image URL here\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">"
                + "<input type=\"text\" name=\"answerText\" placeholder=\"Type correct answer here\">"
                + "<button class=\"btn\" type=\"submit\">Save Question</button>"
                + "</form>";
    }

//    private String generateMultiAns() {
//        StringBuilder formBuilder = new StringBuilder();
//        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
//                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTI_ANSWER\">")
//                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">");
//
//        int maxAnswers = 10;
//        for (int i = 1; i <= maxAnswers; i++) {
//            formBuilder.append("<input type=\"text\" name=\"answer").append(i).append("\" placeholder=\"Type correct answer ").append(i).append(" here\">");
//        }
//
//        formBuilder.append("<button type=\"submit\">Save Question</button>")
//                .append("</form>");
//
//        return formBuilder.toString();
//    }

    private String generateMultiAns() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form id=\"multiAnswerForm\" action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTI_ANSWER\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\" required>");

        // Initial input fields for answers
        formBuilder.append("<div id=\"answerFields\">");
        formBuilder.append("<div><input type=\"text\" name=\"answer1\" placeholder=\"Type correct answer 1 here\" required></div>");
        formBuilder.append("</div>");

        // Button to add new answer input fields dynamically
        formBuilder.append("<button type=\"button\" onclick=\"addAnswerField()\">Add Answer Field</button>");

        // Submit button
        formBuilder.append("<button type=\"submit\">Save Question</button>")
                .append("</form>");

        // JavaScript function to add new answer fields dynamically
        formBuilder.append("<script>");
        formBuilder.append("function addAnswerField() {");
        formBuilder.append("var answerFields = document.getElementById('answerFields');");
        formBuilder.append("var newInput = document.createElement('div');");
        formBuilder.append("var index = answerFields.children.length + 1;");
        formBuilder.append("newInput.innerHTML = '<input type=\"text\" name=\"answer' + index + '\" placeholder=\"Type correct answer ' + index + ' here\" required>';")
                .append("answerFields.appendChild(newInput);");
        formBuilder.append("}");
        formBuilder.append("</script>");

        return formBuilder.toString();
    }


    private String generateMultiChoiceAns() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTIPLE_CHOICE_WITH_ANSWERS\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">")
                .append("<input type=\"text\" name=\"answer1\" placeholder=\"Type answer 1 here\">")
                .append("<input type=\"text\" name=\"answer2\" placeholder=\"Type answer 2 here\">")
                .append("<input type=\"text\" name=\"answer3\" placeholder=\"Type answer 3 here\">")
                .append("<input type=\"text\" name=\"answer4\" placeholder=\"Type answer 4 here\">")
                .append("<input type=\"text\" name=\"correctIndexes\" placeholder=\"Type the index of the correct answer(s) separated by commas\">")
                .append("<button type=\"submit\">Save Question</button>")
                .append("</form>");

        return formBuilder.toString();
    }
    private String generateMatching() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MATCHING\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">")
                .append("<div class=\"quiz\">")
                .append("<div class=\"questions\" id=\"questions\">")
                .append("<div class=\"pair\">")
                .append("<input type=\"text\" name=\"question1\" placeholder=\"Question 1\">")
                .append("<input type=\"text\" name=\"answer1\" placeholder=\"Answer 1\">")
                .append("</div>")
                .append("</div>")
                .append("<button type=\"button\" class=\"add-pair-btn\" onclick=\"addPair()\">Add Pair</button>")
                .append("</div>")
                .append("<input type=\"hidden\" name=\"pairCount\" id=\"pairCount\" value=\"1\">") // Added pairCount field
                .append("<button class=\"btn\" type=\"submit\">Save Question</button>")
                .append("</form>")
                .append("<script>")
                .append("let pairCount = 1;")
                .append("function addPair() {")
                .append("  pairCount++;")
                .append("  const questionsDiv = document.getElementById('questions');")
                .append("  const newPairDiv = document.createElement('div');")
                .append("  newPairDiv.classList.add('pair');")
                .append("  newPairDiv.innerHTML = `<input type='text' name='question${pairCount}' placeholder='Question ${pairCount}'>")
                .append("  <input type='text' name='answer${pairCount}' placeholder='Answer ${pairCount}'>`;")
                .append("  questionsDiv.appendChild(newPairDiv);")
                .append("  document.getElementById('pairCount').value = pairCount;") // Update pairCount field
                .append("}")
                .append("</script>");
        return formBuilder.toString();
    }


    public Question createQuestion(QuestionType questionType, Map<String, String> formData) {
        Question question = new Question();
        question.setQuestionType(questionType);
        question.setQuestionText(formData.get("questionText"));

        switch (questionType) {
            case QUESTION_RESPONSE:
                question.setSingleQuestionAnswer(formData.get("answerText"));
                break;
            case FILL_IN_THE_BLANK:
                question.setSingleQuestionAnswer(formData.get("answerText"));
                break;
            case MULTIPLE_CHOICE:
                ArrayList<String> choices = new ArrayList<>();
                choices.add(formData.get("answer1"));
                choices.add(formData.get("answer2"));
                choices.add(formData.get("answer3"));
                choices.add(formData.get("answer4"));
                question.setMultipleChoiceAnswers(choices);

                ArrayList<Integer> correctIndexes = new ArrayList<>();
                String[] indexes = formData.get("correctIndexes").split(",");
                for (String index : indexes) {
                    correctIndexes.add(Integer.parseInt(index.trim()));
                }
                question.setMultipleChoiceCorrectIndexes(correctIndexes);
                break;
            case PICTURE_RESPONSE:
                question.setQuestionImage(formData.get("questionImage"));
                question.setSingleQuestionAnswer(formData.get("answerText"));
                break;
            case MULTI_ANSWER:
                HashSet<String> answers = new HashSet<>();
                for (int i = 1; i <= 10; i++) {
                    String answer = formData.get("answer" + i);
                    if (answer != null && !answer.isEmpty()) {
                        answers.add(answer);
                    }
                }
                question.setAlternativeAnswers(answers);
                break;
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                choices = new ArrayList<>();
                choices.add(formData.get("answer1"));
                choices.add(formData.get("answer2"));
                choices.add(formData.get("answer3"));
                choices.add(formData.get("answer4"));
                question.setMultipleChoiceAnswers(choices);

                correctIndexes = new ArrayList<>();
                indexes = formData.get("correctIndexes").split(",");
                for (String index : indexes) {
                    correctIndexes.add(Integer.parseInt(index.trim()));
                }
                question.setMultipleChoiceCorrectIndexes(correctIndexes);
                break;
            case MATCHING:
                HashMap<String, String> pairs = new HashMap<>();
                for (int i = 1; ; i++) {
                    String questionKey = formData.get("question" + i);
                    String answerValue = formData.get("answer" + i);
                    if (questionKey == null || answerValue == null) break;
                    pairs.put(questionKey, answerValue);
                }
                question.setMatchingPairs(pairs);
                break;
            default:
                throw new IllegalArgumentException("Invalid question type");
        }
        return question;
    }
}
