package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;

public class TakeSinglePageQuiz {

    public String generateUI(QuestionType questionType, Question question, boolean isEditable) {
        switch (questionType) {
            case QUESTION_RESPONSE:
                return renderQuestionResponse(question, isEditable);
            case FILL_IN_THE_BLANK:
                return renderFillInBlank(question, isEditable);
            case MULTIPLE_CHOICE:
                return renderMultipleChoice(question, isEditable);
            case PICTURE_RESPONSE:
                return renderPictureResponse(question, isEditable);
            case MULTI_ANSWER:
                return renderMultiAnswer(question, isEditable);
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                return renderMultiChoiceWithAnswers(question, isEditable);
            case MATCHING:
                return renderMatchingPairs(question, isEditable);
            default:
                System.out.println("Invalid question type");
                return "";
        }
    }

    private String renderQuestionResponse(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\"><input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" placeholder=\"Type your answer here\"></div>");

        if (isEditable) {
            html.append(renderEditControls(question));
        }
        return html.toString();
    }

    private String renderFillInBlank(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\"><input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" placeholder=\"Type your answer here\"></div>");

        if (isEditable) {
            html.append(renderEditControls(question));
        }
        return html.toString();
    }

    private String renderMultipleChoice(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<ul class=\"answers\">");

        List<String> choiceOptions = question.getMultipleChoiceAnswers();
        for (int i = 0; i < choiceOptions.size(); i++) {
            String optionId = "answer" + (char) ('A' + i);
            String optionText = choiceOptions.get(i);

            html.append("<li><input type=\"radio\" id=\"").append(optionId).append("_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" value=\"").append(optionText).append("\">")
                    .append("<label for=\"").append(optionId).append("_").append(question.getQuestionId()).append("\">").append(optionText).append("</label></li>");
        }
        html.append("</ul>");

        if (isEditable) {
            html.append(renderEditControls(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String renderPictureResponse(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"image-question\"><img src=\"").append(question.getQuestionImage()).append("\" alt=\"Question Image\"></div>")
                .append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\"><input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" placeholder=\"Type your answer here\"></div>");

        if (isEditable) {
            html.append(renderEditControls(question));
        }
        return html.toString();
    }

    private String renderMultiAnswer(Question question, boolean isEditable) {
        List<String> answerFields = question.getMultipleAnswerFields();
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\">");

        for (int i = 0; i < answerFields.size(); i++) {
            html.append("<input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId())
                    .append("\" placeholder=\"Answer Here\">");
        }

        html.append("</div>");

        if (isEditable) {
            html.append(renderEditControls(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String renderMultiChoiceWithAnswers(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"question\"><h3>").append(question.getQuestionText()).append("</h3></div>")
                .append("<ul class=\"answers\">");

        List<String> options = question.getMultipleChoiceAnswers();
        for (int i = 0; i < options.size(); i++) {
            char optionId = (char) ('A' + i);
            html.append("<li><input type=\"checkbox\" id=\"answer").append(optionId).append("_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" value=\"").append(optionId).append("\">")
                    .append("<label for=\"answer").append(optionId).append("_").append(question.getQuestionId())
                    .append("\">").append(options.get(i)).append("</label></li>");
        }

        html.append("</ul>");
        if (isEditable) {
            html.append(renderEditControls(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String renderMatchingPairs(Question question, boolean isEditable) {
        HashMap<String, String> pairs = question.getMatchingPairs();

        List<String> leftItems = new ArrayList<>(pairs.keySet());
        List<String> rightItems = new ArrayList<>(pairs.values());

        Collections.shuffle(leftItems);
        Collections.shuffle(rightItems);

        StringBuilder html = new StringBuilder();
        html.append("<h1>").append(question.getQuestionText()).append("</h1>")
                .append("<div class=\"quiz\"><div class=\"questions\">");

        for (int i = 0; i < leftItems.size(); i++) {
            html.append("<div class=\"match-question\" id=\"question").append(i + 1).append("_").append(question.getQuestionId())
                    .append("\" onclick=\"selectQuestion('question").append(i + 1).append("_").append(question.getQuestionId()).append("')\">")
                    .append(leftItems.get(i)).append("</div>");
        }

        html.append("</div><div class=\"answers\">");

        for (int i = 0; i < rightItems.size(); i++) {
            html.append("<div class=\"match-answer\" id=\"answer").append(i + 1).append("_").append(question.getQuestionId())
                    .append("\" onclick=\"selectAnswer('answer").append(i + 1).append("_").append(question.getQuestionId()).append("')\">")
                    .append(rightItems.get(i)).append("</div>");
        }

        html.append("</div></div>");

        if (isEditable) {
            html.append(renderEditControls(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String renderEditControls(Question question) {
        return "<div class=\"edit-buttons\" id=\"question-" + question.getQuestionId() + "\">"
                + "<button type=\"button\" class=\"deleteBtn\" onclick=\"deleteQuestion('" + question.getQuestionId() + "')\">Delete</button>"
                + "<input type=\"hidden\" name=\"questionId\" value=\"" + question.getQuestionId() + "\">"
                + "</div>";
    }
}
