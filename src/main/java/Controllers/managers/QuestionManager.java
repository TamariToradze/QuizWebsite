package Controllers.managers;

import Dao.QuestionDAO;
import Models.Enums.QuestionType;
import Models.Question;
import utils.SQLConnector;

import java.util.*;

/**
 * Manages operations related to Question objects, including retrieval, creation, update, and deletion.
 */
public class QuestionManager {

    // Attribute name for session/context use
    public static final String ATTRIBUTE_NAME = "QuestionManager";

    private QuestionDAO questionDAO;

    /**
     * Constructs a QuestionManager instance.
     * Initializes the QuestionDAO using the provided SQLConnector.
     */
    public QuestionManager() {
        SQLConnector sqlConnector = new SQLConnector();
        this.questionDAO = new QuestionDAO();
        this.questionDAO.setDataSource(sqlConnector.dataSource);
    }

    /**
     * Retrieves a Question object by its ID.
     *
     * @param questionId The ID of the question to retrieve.
     * @return The Question object corresponding to the given ID, or null if not found.
     */
    public Question getQuestion(int questionId) {
        return questionDAO.ReadQuestion(questionId);
    }

    /**
     * Creates a new Question in the database.
     *
     * @param question The Question object to create.
     * @return The ID of the newly created question.
     */
    public int createQuestion(Question question) {
        return questionDAO.CreateQuestion(question);
    }

    /**
     * Updates an existing Question in the database.
     *
     * @param question The updated Question object.
     */
    public void updateQuestion(Question question) {
        questionDAO.updateQuestion(question);
    }

    /**
     * Deletes a Question from the database by its ID.
     *
     * @param questionId The ID of the Question to delete.
     */
    public void deleteQuestion(int questionId) {
        questionDAO.deleteQuestion(questionId);
    }

    /**
     * Checks whether the provided answers are correct for the given question.
     *
     * @param question         The question to check against.
     * @param singleAnswer     The answer provided by the user (for single-answer questions).
     * @param multipleAnswers  The list of answers provided by the user (for multiple-choice or multi-answer questions).
     * @param matchingAnswers  The map of user-provided matching pairs (for matching questions).
     * @return The number of correct answers.
     */
    public int isAnswerCorrect(Question question, String singleAnswer,
                               ArrayList<String> multipleAnswers,
                               HashMap<String, String> matchingAnswers) {

        QuestionType questionType = question.getQuestionType();

        if (questionType.equals(QuestionType.MATCHING)) {
            // Handle Matching Type Questions
            HashMap<String, String> correctMatchingPairs = question.getMatchingPairs();
            int correctCount = 0;

            // Check each user-provided pair
            for (Map.Entry<String, String> userEntry : matchingAnswers.entrySet()) {
                String userKey = userEntry.getKey();
                String userValue = userEntry.getValue();

                // Verify the user's answer matches the correct pair
                if (correctMatchingPairs.containsKey(userKey) &&
                        correctMatchingPairs.get(userKey).equals(userValue)) {
                    correctCount++;
                }
            }

            return correctCount;

        } else if (questionType.equals(QuestionType.QUESTION_RESPONSE) ||
                questionType.equals(QuestionType.FILL_IN_THE_BLANK) ||
                questionType.equals(QuestionType.PICTURE_RESPONSE)) {

            // Handle Single-Answer Questions
            String correctAnswer = question.getSingleQuestionAnswer();

            if (correctAnswer.equalsIgnoreCase(singleAnswer.trim())) {
                return 1;
            } else {
                return 0;
            }

        } else if (questionType.equals(QuestionType.MULTI_ANSWER)) {

            // Handle Multi-Answer Questions
            List<String> correctAnswerList = new ArrayList<>();
            for (String correct : question.getMultipleAnswerFields()) {
                correctAnswerList.add(correct.toLowerCase().trim());
            }

            List<String> userAnswerList = new ArrayList<>();
            for (String userAnswer : multipleAnswers) {
                userAnswerList.add(userAnswer.toLowerCase().trim());
            }

            int correctCount = 0;

            // Count how many correct answers are present in the user's answers
            for (String correct : correctAnswerList) {
                if (userAnswerList.contains(correct)) {
                    correctCount++;
                }
            }

            return correctCount;

        } else if (questionType.equals(QuestionType.MULTIPLE_CHOICE) ||
                questionType.equals(QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS)) {

            // Handle Multiple-Choice Questions
            ArrayList<String> correctAnswerTexts = new ArrayList<>();
            ArrayList<String> allChoices = question.getMultipleChoiceAnswers();
            ArrayList<Integer> correctAnswerIndices = question.getMultipleChoiceCorrectIndexes();

            // Extract the correct answer texts based on their indexes
            for (int index : correctAnswerIndices) {
                correctAnswerTexts.add(allChoices.get(index).toLowerCase().trim());
            }

            ArrayList<String> userAnswerList = new ArrayList<>();
            for (String userAnswer : multipleAnswers) {
                userAnswerList.add(userAnswer.toLowerCase().trim());
            }

            // Check if all correct answers are present in the user's answers
            if (userAnswerList.containsAll(correctAnswerTexts)) {
                return 1;
            } else {
                return 0;
            }
        }

        // Return 0 for unsupported question types
        return 0;
    }
}
