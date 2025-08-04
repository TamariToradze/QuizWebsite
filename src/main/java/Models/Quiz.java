package Models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a quiz entity, containing details such as quiz ID, creator ID, name, description,
 * score, associated question IDs, history, and various settings.
 */
public class Quiz implements Serializable {

    private int quizIdentifier;
    private String creatorName;
    private String quizTitle;
    private String quizDetails;
    private int totalScore;
    private ArrayList<Integer> questionIdsList;
    private boolean singlePageMode;
    private boolean shuffleQuestions;
    private boolean instantFeedback;
    private Timestamp creationTimestamp;
    private static final long serialVersionUID = 1L; // Add a version UID for serialization



    public Quiz() {
        this.creatorName = "";
        this.quizTitle = "";
        this.quizDetails = "";
        this.totalScore = 0;
        this.questionIdsList = new ArrayList<>();
        this.singlePageMode = false;
        this.shuffleQuestions = false;
        this.instantFeedback = false;
        this.creationTimestamp = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Default constructor that initializes a new Quiz instance with default values.
     *
     * @param quizID the unique ID of the quiz
     */
    public Quiz(int quizID) {
        this.quizIdentifier = quizID;
        this.creatorName = "";
        this.quizTitle = "New Quiz";
        this.quizDetails = "";
        this.totalScore = 0;
        this.questionIdsList = new ArrayList<>();
        this.singlePageMode = false;
        this.shuffleQuestions = false;
        this.instantFeedback = false;
        this.creationTimestamp = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructs a new Quiz instance with the specified quiz ID and basic details.
     *
     * @param quizID          the unique ID of the quiz
     * @param username        the username of the creator
     * @param quizName        the name of the quiz
     * @param quizDescription the description of the quiz
     */
    public Quiz(int quizID, String username, String quizName, String quizDescription) {
        this.quizIdentifier = quizID;
        this.creatorName = username;
        this.quizTitle = quizName;
        this.quizDetails = quizDescription;
        this.totalScore = 0;
        this.questionIdsList = new ArrayList<>();
        this.singlePageMode = false;
        this.shuffleQuestions = false;
        this.instantFeedback = false;
        this.creationTimestamp = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructs a new Quiz instance with the specified details.
     *
     * @param quizID             the unique ID of the quiz
     * @param username           the username of the creator
     * @param quizName           the name of the quiz
     * @param quizDescription    the description of the quiz
     * @param quizScore          the score of the quiz
     * @param questionIds        the list of question IDs associated with the quiz
     * @param isSinglePage       whether the quiz is displayed on a single page
     * @param randomizeQuestions whether questions in the quiz are randomized
     * @param immediateFeedback  whether immediate feedback is enabled for the quiz
     * @param createTime         the creation time of the quiz
     */
    public Quiz(int quizID, String username, String quizName, String quizDescription, int quizScore,
                ArrayList<Integer> questionIds, boolean isSinglePage, boolean randomizeQuestions,
                boolean immediateFeedback, Timestamp createTime) {
        this.quizIdentifier = quizID;
        this.creatorName = username;
        this.quizTitle = quizName;
        this.quizDetails = quizDescription;
        this.totalScore = quizScore;
        this.questionIdsList = questionIds != null ? new ArrayList<>(questionIds) : new ArrayList<>();
        this.singlePageMode = isSinglePage;
        this.shuffleQuestions = randomizeQuestions;
        this.instantFeedback = immediateFeedback;
        this.creationTimestamp = createTime != null ? createTime : new Timestamp(System.currentTimeMillis());
    }


    /**
     * Retrieves the unique ID of the quiz.
     *
     * @return the quiz ID
     */
    public int getQuizID() {
        return quizIdentifier;
    }

    /**
     * sets the unique ID of the quiz.
     */
    public void setQuizID(int quizID) {
        this.quizIdentifier = quizID;
    }

    /**
     * Retrieves the username of the creator of the quiz.
     *
     * @return the username of the creator of the quiz
     */
    public String getCreatorUsername() {
        return creatorName;
    }

    /**
     * Sets the username of the creator of the quiz.
     *
     * @param username the username of the creator of the quiz
     */
    public void setCreatorUsername(String username) {
        this.creatorName = username;
    }

    /**
     * Retrieves the name of the quiz.
     *
     * @return the name of the quiz
     */
    public String getQuizName() {
        return quizTitle;
    }

    /**
     * Sets the name of the quiz.
     *
     * @param quizName the name of the quiz
     */
    public void setQuizName(String quizName) {
        this.quizTitle = quizName;
    }

    /**
     * Retrieves the description of the quiz.
     *
     * @return the description of the quiz
     */
    public String getQuizDescription() {
        return quizDetails;
    }

    /**
     * Sets the description of the quiz.
     *
     * @param quizDescription the description of the quiz
     */
    public void setQuizDescription(String quizDescription) {
        this.quizDetails = quizDescription;
    }

    /**
     * Retrieves the score of the quiz.
     *
     * @return the score of the quiz
     */
    public int getQuizScore() {
        return totalScore;
    }

    /**
     * Sets the score of the quiz.
     *
     * @param quizScore the score of the quiz
     */
    public void setQuizScore(int quizScore) {
        this.totalScore = quizScore;
    }

    /**
     * Increments the score of the quiz by a specified amount.
     *
     * @param increment the amount to increment the score by
     */
    public void incrementQuizScore(int increment) {
        this.totalScore += increment;
    }

    /**
     * Resets the score of the quiz to zero.
     */
    public void resetQuizScore() {
        this.totalScore = 0;
    }

    /**
     * Retrieves the list of question IDs associated with the quiz.
     *
     * @return the list of question IDs
     */
    public ArrayList<Integer> getQuestionIds() {
        return (ArrayList<Integer>) questionIdsList;
    }

    /**
     * Sets the list of question IDs associated with the quiz.
     *
     * @param questionIds the list of question IDs
     */
    public void setQuestionIds(ArrayList<Integer> questionIds) {
        this.questionIdsList = questionIds;
    }

    /**
     * Adds a question ID to the quiz.
     *
     * @param questionId the ID of the question to add
     */
    public void addQuestionId(int questionId) {
        this.questionIdsList.add(questionId);
    }

    /**
     * Removes a question ID from the quiz.
     *
     * @param questionId the ID of the question to remove
     */
    public void removeQuestionId(int questionId) {
        this.questionIdsList.remove(Integer.valueOf(questionId));
    }

    /**
     * Checks if the quiz is displayed on a single page.
     *
     * @return true if the quiz is displayed on a single page; false otherwise
     */
    public boolean isSinglePage() {
        return singlePageMode;
    }

    /**
     * Sets whether the quiz is displayed on a single page or not.
     *
     * @param singlePage true if the quiz should be displayed on a single page; false otherwise
     */
    public void setSinglePage(boolean singlePage) {
        singlePageMode = singlePage;
    }

    /**
     * Checks if questions in the quiz are randomized.
     *
     * @return true if questions are randomized; false otherwise
     */
    public boolean isRandomizeQuestions() {
        return shuffleQuestions;
    }

    /**
     * Sets whether questions in the quiz should be randomized.
     *
     * @param randomizeQuestions true to randomize questions; false otherwise
     */
    public void setRandomizeQuestions(boolean randomizeQuestions) {
        this.shuffleQuestions = randomizeQuestions;
    }

    /**
     * Checks if immediate feedback is enabled for the quiz.
     *
     * @return true if immediate feedback is enabled; false otherwise
     */
    public boolean isImmediateFeedback() {
        return instantFeedback;
    }

    /**
     * Sets whether immediate feedback should be enabled for the quiz.
     *
     * @param immediateFeedback true to enable immediate feedback; false otherwise
     */
    public void setImmediateFeedback(boolean immediateFeedback) {
        this.instantFeedback = immediateFeedback;
    }

    /**
     * Retrieves the creation time of the quiz.
     *
     * @return the creation time of the quiz
     */
    public Timestamp getCreateTime() {
        return creationTimestamp;
    }

    /**
     * Sets the creation time of the quiz.
     *
     * @param createTime the creation time of the quiz
     */
    public void setCreateTime(Timestamp createTime) {
        this.creationTimestamp = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return quizIdentifier == quiz.quizIdentifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizIdentifier);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizID=" + quizIdentifier +
                ", username='" + creatorName + '\'' +
                ", quizName='" + quizTitle + '\'' +
                ", quizDescription='" + quizDetails + '\'' +
                ", quizScore=" + totalScore +
                ", questionIds=" + questionIdsList +
                ", isSinglePage=" + singlePageMode +
                ", randomizeQuestions=" + shuffleQuestions +
                ", immediateFeedback=" + instantFeedback +
                ", createTime=" + creationTimestamp +
                '}';
    }
}