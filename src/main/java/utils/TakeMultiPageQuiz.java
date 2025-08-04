package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;


public class TakeMultiPageQuiz {

    public String generateUI(QuestionType questionType, Question question) {

        switch (questionType) {
            case QUESTION_RESPONSE:
                return generateQuestionResponse(question);
            case FILL_IN_THE_BLANK:
                return generateFillInTheBlank(question);
            case MULTIPLE_CHOICE:
                return generateMultipleChoice(question);
            case PICTURE_RESPONSE:
                return generatePictureResponse(question);
            case MULTI_ANSWER:
                return generateMultiAnswer(question);
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                return generateMultipleChoiceWithAnswers(question);
            case MATCHING:
                return generateMatchingQuestion(question);
            default:
                System.out.println("Invalid question type");
                return "";
        }
    }

    private String generateQuestionResponse(Question question) {
        return "<form action=\"QuestionServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">"
                +"<input type=\"hidden\" name=\"questionId\" value=\"" + question.getQuestionId() + "\">"
                + "<div class=\"question\">" + question.getQuestionText() + "</div>"
                + "<div class=\"response\">" + "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" + "</div>"
                + "<button class=\"btn\" type=\"submit\">Submit</button>" + "</form>";
    }

    private String generateFillInTheBlank(Question question) {
        return "<form action=\"QuestionServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
                "<input type=\"hidden\" name=\"questionId\" value=\"" + question.getQuestionId() + "\">" +
                "<div class=\"question\">" + question.getQuestionText() + "</div>" +
                "<div class=\"response\">" + "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" + "</div>"
                + "<button class=\"btn\" type=\"submit\">Submit</button>" + "</form>";
    }

    private String generateMultipleChoice(Question question) {
        StringBuilder html = new StringBuilder();
        html.append("<form action=\"QuestionServlet\" method=\"post\" onsubmit=\"collectAnswers(event)\">").append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<input type=\"hidden\" name=\"questionId\" value=\"").append(question.getQuestionId()).append("\">").append("<input type=\"hidden\" name=\"userAnswers\" id=\"userAnswers\">").append("<div class=\"question\">").append(question.getQuestionText()).append("</div>").append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        boolean isMultipleAnswers = question.getQuestionType() == QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS;

        for (int i = 0; i < answers.size(); i++) {
            String answerId = "answer" + (char) ('A' + i);
            String answerLabel = answers.get(i);
            if (isMultipleAnswers) {
                html.append("<li><input type=\"checkbox\" id=\"").append(answerId).append("\" name=\"userAnswer\" value=\"").append(answerLabel).append("\">").append("<label for=\"").append(answerId).append("\">").append(answerLabel).append("</label></li>");
            } else {
                html.append("<li><input type=\"radio\" id=\"").append(answerId).append("\" name=\"userAnswer\" value=\"").append(answerLabel).append("\">").append("<label for=\"").append(answerId).append("\">").append(answerLabel).append("</label></li>");
            }
        }

        html.append("</ul>")
                .append("<button class=\"btn\" type=\"submit\">Submit</button>")
                .append("</form>").append("<script>")
                .append("function collectAnswers(event) {")
                .append("event.preventDefault();")
                .append("const selectedAnswers = [];")
                .append("const checkboxes = document.querySelectorAll('input[name=\"userAnswer\"]:checked');")
                .append("checkboxes.forEach((checkbox) => { selectedAnswers.push(checkbox.value); });")
                .append("document.getElementById('userAnswers').value = JSON.stringify(selectedAnswers);")
                .append("event.target.submit();").append("}").append("</script>");
        return html.toString();
    }


    private String generatePictureResponse(Question question) {
        return "<form action=\"QuestionServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
                "<input type=\"hidden\" name=\"questionId\" value=\"" + question.getQuestionId() + "\">"
                + "<div class=\"image-question\">" + "<img src=\"" + question.getQuestionImage() + "\" alt=\"Question Image\">" + "</div>"
                + "<div class=\"question\">\"" + question.getQuestionText() + "\"</div>"
                + "<div class=\"response\">" + "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" + "</div>"
                + "<button class=\"btn\" type=\"submit\">Submit</button>" + "</form>";
    }

    private String generateMultiAnswer(Question question) {
        List<String> correctAnswers = question.getMultipleAnswerFields();
        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append("<form id=\"multiAnsForm\" action=\"QuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<input type=\"hidden\" name=\"questionId\" value=\"").append(question.getQuestionId()).append("\">")
                .append("<input type=\"hidden\" name=\"userAnswers\" id=\"userAnswers\">")
                .append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\">");

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (i % 4 == 0) {
                formBuilder.append("<div class=\"row\">");
            }

            formBuilder.append("<input type=\"text\" id=\"userAnswer").append(i + 1).append("\" name=\"userAnswer").append(i + 1).append("\" placeholder=\"Answer Here\">");

            if ((i + 1) % 4 == 0 || i == correctAnswers.size() - 1) {
                formBuilder.append("</div>");
            }
        }

        formBuilder.append("</div>")
                .append("<button class=\"btn\" type=\"submit\" onclick=\"prepareUserAnswers()\">Submit</button>")
                .append("</form>")
                .append("<script>")
                .append("function prepareUserAnswers() {")
                .append("  var answers = [];")
                .append("  for (var i = 1; i <= ").append(correctAnswers.size()).append("; i++) {")
                .append("    var answer = document.getElementById('userAnswer' + i).value;")
                .append("    answers.push(answer);")
                .append("  }")
                .append("  document.getElementById('userAnswers').value = JSON.stringify(answers);")
                .append("}")
                .append("</script>");

        return formBuilder.toString();
    }


    private String generateMultipleChoiceWithAnswers(Question question) {
        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append("<form id=\"multiChoiceForm\" action=\"QuestionServlet\" method=\"post\" onsubmit=\"collectAnswers(event)\">")
                .append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<input type=\"hidden\" name=\"questionId\" value=\"").append(question.getQuestionId()).append("\">")
                .append("<input type=\"hidden\" name=\"userAnswers\" id=\"userAnswers\">")
                .append("<div class=\"question\"><h3>").append(question.getQuestionText()).append("</h3></div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        for (int i = 0; i < answers.size(); i++) {
            char answerId = (char) ('A' + i);
            formBuilder.append("<li>")
                    .append("<input type=\"checkbox\" id=\"answer").append(answerId).append("\" name=\"userAnswer\" value=\"").append(answerId).append("\">")
                    .append("<label for=\"answer").append(answerId).append("\">").append(answers.get(i)).append("</label>")
                    .append("</li>");
        }

        formBuilder.append("</ul>")
                .append("<button class=\"btn\" type=\"submit\">Submit</button>")
                .append("</form>")
                .append("<script>")
                .append("function collectAnswers(event) {")
                .append("  event.preventDefault();")
                .append("  const selectedAnswers = [];")
                .append("  const checkboxes = document.querySelectorAll('input[name=\"userAnswer\"]:checked');")
                .append("  checkboxes.forEach((checkbox) => { selectedAnswers.push(checkbox.value); });")
                .append("  document.getElementById('userAnswers').value = JSON.stringify(selectedAnswers);")
                .append("  event.target.submit();")
                .append("}")
                .append("</script>");

        return formBuilder.toString();
    }

    private String generateMatchingQuestion(Question question) {
        HashMap<String, String> matchingPairs = question.getMatchingPairs();

        List<String> questions = new ArrayList<>(matchingPairs.keySet());
        List<String> answers = new ArrayList<>(matchingPairs.values());

        Collections.shuffle(questions);
        Collections.shuffle(answers);

        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append("<form action=\"QuestionServlet\" method=\"post\" >").append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<input type=\"hidden\" name=\"questionId\" value=\"").append(question.getQuestionId()).append("\">")
                .append("<h1> \"" + question.getQuestionText() + "\"</h1>").append("<div class=\"quiz\">").append("<div class=\"questions\">");

        for (int i = 0; i < questions.size(); i++) {
            formBuilder.append("<div class=\"marcxena\" id=\"question").append(i + 1).append("\" onclick=\"selectQuestion('question").append(i + 1).append("')\">").append(questions.get(i)).append("</div>");
        }

        formBuilder.append("</div>").append("<div class=\"answers\">");

        for (int i = 0; i < answers.size(); i++) {
            formBuilder.append("<div class=\"answer\" id=\"answer").append(i + 1).append("\" onclick=\"selectAnswer('answer").append(i + 1).append("')\">").append(answers.get(i)).append("</div>");
        }

        formBuilder.append("</div>").append("</div>")
                .append("<input type=\"hidden\" name=\"userAnswers\" id=\"userAnswers\" value=\"\">\n")
                .append("<button class=\"btn\" type=\"submit\">Submit</button>")
                .append("</form>")
                .append("<script src=\"javascript/MatchingQuestion.js\">")
                .append("</script>");


        return formBuilder.toString();
    }
}