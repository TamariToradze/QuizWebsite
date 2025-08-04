package Models.Enums;

public enum AchievementType {
// we have several type of achievement like :
    AMATEUR_AUTHOR,
    PROLIFIC_AUTHOR,
    PRODIGIOUS_AUTHOR,
    QUIZ_MACHINE,
    I_AM_THE_GREATEST,
    PRACTICE_MAKES_PERFECT;

    public static QuestionType fromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return QuestionType.valueOf(str.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}