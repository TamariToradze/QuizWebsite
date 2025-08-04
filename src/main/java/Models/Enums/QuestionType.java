package Models.Enums;

public enum QuestionType {
    QUESTION_RESPONSE,
    FILL_IN_THE_BLANK,
    MULTIPLE_CHOICE,
    PICTURE_RESPONSE,
    MULTI_ANSWER,
    MULTIPLE_CHOICE_WITH_ANSWERS,
    MATCHING;

    public static QuestionType fromString(String type) {
        if (type == null) {
            return null;
        }
        try {
            return QuestionType.valueOf(type.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
