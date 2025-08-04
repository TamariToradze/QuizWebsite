package Models;

import java.sql.Time;
import java.util.Date;

/**
 * Represents a user's attempt history for a quiz.
 * It includes details such as quiz ID, username, score, start and end times, date of completion, and elapsed time.
 */
public class QuizHistory {

    private int quizId;
    private String username;
    private int quizScore;
    private Time startTime;
    private Time endTime;
    private Date endDate;
    private long elapsedTime;

    /**
     * Creates a new QuizHistory entry with default values for score, times, and elapsed duration.
     *
     * @param quizId   the unique identifier of the quiz
     * @param username the username of the user who attempted the quiz
     */
    public QuizHistory(int quizId, String username) {
        this.quizId = quizId;
        this.username = username;
        this.quizScore = 0;
        this.startTime = null;
        this.endTime = null;
        this.elapsedTime = 0;
        this.endDate = null;
    }

    /**
     * Creates a new QuizHistory entry with all relevant data.
     *
     * @param quizId      the unique identifier of the quiz
     * @param username    the username of the user who attempted the quiz
     * @param quizScore   the score obtained by the user
     * @param startTime   the time the quiz was started
     * @param endTime     the time the quiz was submitted
     * @param elapsedTime the duration (in milliseconds) the user took to complete the quiz
     */
    public QuizHistory(int quizId, String username, int quizScore, Time startTime, Time endTime, long elapsedTime) {
        this.quizId = quizId;
        this.username = username;
        this.quizScore = quizScore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.elapsedTime = elapsedTime;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username of the quiz taker (alias of setUsername).
     *
     * @param username the user ID (redundant method)
     */
    public void setUserId(String username) {
        this.username = username;
    }

    public int getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of this quiz attempt history,
     * showing all relevant fields in a human-readable format.
     *
     * @return a descriptive string of the quiz history record
     */
    @Override
    public String toString() {
        return "QuizHistory {" +
                "quizId=" + quizId +
                ", username='" + username + '\'' +
                ", quizScore=" + quizScore +
                ", startTime=" + (startTime != null ? startTime.toString() : "N/A") +
                ", endTime=" + (endTime != null ? endTime.toString() : "N/A") +
                ", endDate=" + (endDate != null ? endDate.toString() : "N/A") +
                ", elapsedTime=" + elapsedTime + " ms" +
                '}';
    }
}
