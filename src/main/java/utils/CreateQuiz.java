package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;

public class CreateQuiz {

    private static final String QUESTION_DIV_CLASS = "questiontxt";
    private static final String CORRECT_RESPONSE_CLASS = "correct-response";
    private static final String ANSWERS_UL_CLASS = "answers";
    private static final String USER_ANSWER_PREFIX = "userAnswer_";
    private static final String USER_ANSWERS_PREFIX = "userAnswers_";

    public String generateUI(QuestionType questionType, Question question, boolean isEditable) {
        QuestionRenderer renderer = getRenderer(questionType);
        if (renderer == null) {
            System.err.println("Unsupported question type: " + questionType);
            return "";
        }
        return renderer.render(question, isEditable);
    }

    private QuestionRenderer getRenderer(QuestionType questionType) {
        switch (questionType) {
            case QUESTION_RESPONSE:
                return (question, isEditable) -> generateQuesRes(question);
            case FILL_IN_THE_BLANK:
                return (question, isEditable) -> generateFillBlank(question);
            case MULTIPLE_CHOICE:
                return this::generateMultiChoice;
            case PICTURE_RESPONSE:
                return (question, isEditable) -> generatePictRes(question);
            case MULTI_ANSWER:
                return this::generateMultiAns;
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                return this::generateMultiChoiceAns;
            case MATCHING:
                return this::generateMatching;
            default:
                return null;
        }
    }

    @FunctionalInterface
    private interface QuestionRenderer {
        String render(Question question, boolean isEditable);
    }

    private String generateQuesRes(Question question) {
        return buildBasicQuestionHtml(question, question.getSingleQuestionAnswer());
    }

    private String generateFillBlank(Question question) {
        return buildBasicQuestionHtml(question, question.getSingleQuestionAnswer());
    }

    private String buildBasicQuestionHtml(Question question, String correctAnswer) {
        StringBuilder html = new StringBuilder();
        html.append(createQuestionDiv(question.getQuestionText()));
        html.append(createCorrectAnswerDiv(correctAnswer));
        return html.toString();
    }

    private String generateMultiChoice(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append(createQuestionDiv(question.getQuestionText()));
        html.append(createMultipleChoiceOptions(question, "radio"));
        html.append(createHiddenInput(question.getQuestionId()));
        return html.toString();
    }

    private String generatePictRes(Question question) {
        StringBuilder html = new StringBuilder();
        html.append(createImageDiv(question.getQuestionImage()));
        html.append(createQuestionDiv(question.getQuestionText()));
        html.append(createCorrectAnswerDiv(question.getSingleQuestionAnswer()));
        return html.toString();
    }

    private String generateMultiAns(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append(createQuestionDiv(question.getQuestionText()));
        html.append("<div class=\"response\"></div>");
        html.append(createMultipleCorrectAnswersDiv(question.getMultipleAnswerFields()));
        html.append(createHiddenInput(question.getQuestionId()));
        return html.toString();
    }

    private String generateMultiChoiceAns(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append(String.format("<div class=\"%s\"><h3>%s</h3></div>",
                QUESTION_DIV_CLASS, question.getQuestionText()));
        html.append(createMultipleChoiceOptions(question, "checkbox"));
        html.append(createHiddenInput(question.getQuestionId()));
        return html.toString();
    }

    private String generateMatching(Question question, boolean isEditable) {
        HashMap<String, String> matchingPairs = question.getMatchingPairs();
        List<String> questions = new ArrayList<>(matchingPairs.keySet());
        List<String> answers = new ArrayList<>(matchingPairs.values());

        StringBuilder html = new StringBuilder();
        html.append("<div class=\"matching-container\">");
        html.append(String.format("<h1>%s</h1>", question.getQuestionText()));
        html.append("<div class=\"matching-content\">");
        html.append(createMatchingItems(questions, "question", question.getQuestionId()));
        html.append(createMatchingItems(answers, "answer", question.getQuestionId()));
        html.append("</div>");
        html.append(createCorrectAnswerDiv(matchingPairs.toString()));
        html.append(createHiddenInput(question.getQuestionId()));
        html.append("<br></div>");
        return html.toString();
    }

    // Helper methods for building HTML components
    private String createQuestionDiv(String questionText) {
        return String.format("<div class=\"%s\">%s</div>", QUESTION_DIV_CLASS, questionText);
    }

    private String createCorrectAnswerDiv(String correctAnswer) {
        return String.format("<div class=\"%s\">Correct Answer: <p>%s</p></div>",
                CORRECT_RESPONSE_CLASS, correctAnswer);
    }

    private String createMultipleCorrectAnswersDiv(List<String> answers) {
        StringBuilder html = new StringBuilder();
        html.append(String.format("<div class=\"%s\">Correct Answer: ", CORRECT_RESPONSE_CLASS));
        for (String answer : answers) {
            html.append(String.format("<p>%s</p>", answer));
        }
        html.append("</div>");
        return html.toString();
    }

    private String createImageDiv(String imageSrc) {
        return String.format("<div class=\"image-question\"><img src=\"%s\" alt=\"Question Image\"></div>", imageSrc);
    }

    private String createHiddenInput(int questionId) {
        return String.format("<input type=\"hidden\" name=\"%s%d\" value=\"\">",
                USER_ANSWERS_PREFIX, questionId);
    }

    private String createMultipleChoiceOptions(Question question, String inputType) {
        StringBuilder html = new StringBuilder();
        html.append(String.format("<ul class=\"%s\">", ANSWERS_UL_CLASS));

        List<String> answers = question.getMultipleChoiceAnswers();
        List<Integer> correctIndexes = question.getMultipleChoiceCorrectIndexes();

        for (int i = 0; i < answers.size(); i++) {
            String optionId = generateOptionId(i, question.getQuestionId());
            String optionValue = inputType.equals("radio") ? answers.get(i) : String.valueOf((char) ('A' + i));
            boolean isCorrect = correctIndexes.contains(i);

            html.append(createOptionHtml(optionId, inputType, optionValue, answers.get(i),
                    question.getQuestionId(), isCorrect));
        }

        html.append("</ul>");
        return html.toString();
    }

    private String generateOptionId(int index, int questionId) {
        return String.format("answer%c_%d", (char) ('A' + index), questionId);
    }

    private String createOptionHtml(String optionId, String inputType, String value,
                                    String label, int questionId, boolean isCorrect) {
        return String.format(
                "<li><input type=\"%s\" id=\"%s\" name=\"%s%d\" value=\"%s\"%s>" +
                        "<label for=\"%s\">%s</label></li>",
                inputType, optionId, USER_ANSWER_PREFIX, questionId, value,
                isCorrect ? " checked" : "", optionId, label
        );
    }

    private String createMatchingItems(List<String> items, String type, int questionId) {
        StringBuilder html = new StringBuilder();
        html.append(String.format("<div class=\"matching-%ss\">", type));

        for (int i = 0; i < items.size(); i++) {
            String itemId = String.format("%s%d_%d", type, i + 1, questionId);
            html.append(String.format(
                    "<div class=\"matching-item\" id=\"%s\" onclick=\"select%s('%s')\">%s</div>",
                    itemId, capitalize(type), itemId, items.get(i)
            ));
        }

        html.append("</div>");
        return html.toString();
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}