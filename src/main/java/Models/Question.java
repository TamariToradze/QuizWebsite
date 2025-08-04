package Models;

import Models.Enums.QuestionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a Question with various types such as multiple choice, fill in the blank, etc.
 */
public class Question implements Serializable {
    private int questionId;
    private int quizId;
    private QuestionType questionType; /**< The type of the question. */
    private String questionText; /**< The text of the question. */
    private String singleQuestionAnswer; /**< The single correct answer to the question. */
    private HashSet<String> alternativeAnswers; /**< A set of alternative correct answers. */
    private ArrayList<String> multipleChoiceAnswers; /**< A list of multiple choice answers. */
    private ArrayList<Integer> multipleChoiceCorrectIndexes; /**< Indexes of the correct multiple choice answers. */
    private String questionImage; /**< An image associated with the question, if any. */
    private ArrayList<String> multipleAnswerFields; /**< Fields for multiple answers. */
    private HashMap<String, String> matchingPairs; /**< Matching pairs for matching questions. */
    private static final long serialVersionUID = 1L; // Add a version UID for serialization

    /**
     * Constructs a new Question instance with the specified question ID.
     */
    public Question() {
        this.alternativeAnswers = new HashSet<>();
        this.multipleChoiceAnswers = new ArrayList<>();
        this.multipleChoiceCorrectIndexes = new ArrayList<>();
        this.multipleAnswerFields = new ArrayList<>();
        this.matchingPairs = new HashMap<>();
    }
    /**
     * Constructs a new Question instance with the specified question ID.
     *
     * @param questionId the unique ID of the question
     */
    public Question(int questionId) {
        this.questionId = questionId;
        this.alternativeAnswers = new HashSet<>();
        this.multipleChoiceAnswers = new ArrayList<>();
        this.multipleChoiceCorrectIndexes = new ArrayList<>();
        this.multipleAnswerFields = new ArrayList<>();
        this.matchingPairs = new HashMap<>();
    }


    /**
     * Constructs a new Question instance with all fields.
     *
     * @param questionId                  the unique ID of the question
     * @param quizId                      the ID of the quiz this question belongs to
     * @param questionType                the type of the question
     * @param questionText                the text of the question
     * @param singleQuestionAnswer        the single correct answer to the question
     * @param alternativeAnswers          a set of alternative correct answers
     * @param multipleChoiceAnswers       a list of multiple choice answers
     * @param multipleChoiceCorrectIndexes indexes of the correct multiple choice answers
     * @param questionImage               an image associated with the question, if any
     * @param multipleAnswerFields        fields for multiple answers
     * @param matchingPairs               matching pairs for matching questions
     */
    public Question(int questionId, int quizId, QuestionType questionType, String questionText,
                    String singleQuestionAnswer, HashSet<String> alternativeAnswers,
                    ArrayList<String> multipleChoiceAnswers, ArrayList<Integer> multipleChoiceCorrectIndexes,
                    String questionImage, ArrayList<String> multipleAnswerFields,
                    HashMap<String, String> matchingPairs) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionType = questionType;
        this.questionText = questionText;
        this.singleQuestionAnswer = singleQuestionAnswer;
        this.alternativeAnswers = alternativeAnswers;
        this.multipleChoiceAnswers = multipleChoiceAnswers;
        this.multipleChoiceCorrectIndexes = multipleChoiceCorrectIndexes;
        this.questionImage = questionImage;
        this.multipleAnswerFields = multipleAnswerFields;
        this.matchingPairs = matchingPairs;
    }

    // Getters and Setters
    public int getQuestionId() {
        return questionId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getSingleQuestionAnswer() {
        return singleQuestionAnswer;
    }

    public void setSingleQuestionAnswer(String singleQuestionAnswer) {
        this.singleQuestionAnswer = singleQuestionAnswer;
    }

    public HashSet<String> getAlternativeAnswers() {
        return alternativeAnswers;
    }

    public void setAlternativeAnswers(HashSet<String> alternativeAnswers) {
        this.alternativeAnswers = alternativeAnswers;
    }

    public ArrayList<String> getMultipleChoiceAnswers() {
        return multipleChoiceAnswers;
    }

    public void setMultipleChoiceAnswers(ArrayList<String> multipleChoiceAnswers) {
        this.multipleChoiceAnswers = multipleChoiceAnswers;
    }

    public ArrayList<Integer> getMultipleChoiceCorrectIndexes() {
        return multipleChoiceCorrectIndexes;
    }

    public void setMultipleChoiceCorrectIndexes(ArrayList<Integer> multipleChoiceCorrectIndexes) {
        this.multipleChoiceCorrectIndexes = multipleChoiceCorrectIndexes;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public ArrayList<String> getMultipleAnswerFields() {
        return multipleAnswerFields;
    }

    public void setMultipleAnswerFields(ArrayList<String> multipleAnswerFields) {
        this.multipleAnswerFields = multipleAnswerFields;
    }

    public HashMap<String, String> getMatchingPairs() {
        return matchingPairs;
    }

    public void setMatchingPairs(HashMap<String, String> matchingPairs) {
        this.matchingPairs = matchingPairs;
    }

    // Convenience methods for adding answers and matching pairs
    public void addAlternativeAnswer(String answer) {
        this.alternativeAnswers.add(answer);
    }

    public void addMultipleChoiceAnswer(String answer, boolean isCorrect) {
        this.multipleChoiceAnswers.add(answer);
        if (isCorrect) {
            this.multipleChoiceCorrectIndexes.add(this.multipleChoiceAnswers.size() - 1);
        }
    }

    public void addMultipleAnswerField(String field) {
        this.multipleAnswerFields.add(field);
    }

    public void addMatchingPair(String key, String value) {
        this.matchingPairs.put(key, value);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", quizId=" + quizId +
                ", questionType=" + questionType +
                ", questionText='" + questionText + '\'' +
                ", singleQuestionAnswer='" + singleQuestionAnswer + '\'' +
                ", alternativeAnswers=" + alternativeAnswers +
                ", multipleChoiceAnswers=" + multipleChoiceAnswers +
                ", multipleChoiceCorrectIndexes=" + multipleChoiceCorrectIndexes +
                ", questionImage='" + questionImage + '\'' +
                ", multipleAnswerFields=" + multipleAnswerFields +
                ", matchingPairs=" + matchingPairs +
                '}';
    }

    public boolean isMultipleChoice() {
        return questionType.equals(QuestionType.MULTIPLE_CHOICE);
    }
}
