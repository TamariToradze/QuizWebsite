package Models;

/**
 * Represents an entry in a leaderboard with a username, score, and elapsed time.
 */
public class LeaderboardEntry {
    private String username;
    private int result;
    private long time;

    /**
     * Constructs a new LeaderboardEntry.
     *
     * @param username    the username of the player
     * @param result       the score achieved by the player
     * @param time the elapsed time in milliseconds
     */
    public LeaderboardEntry(String username, int result, long time) {
        this.username = username;
        this.result = result;
        this.time = time;
    }

   //getter/setters
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getScore() {
        return this.result;
    }

    public void setScore(int result) {
        this.result = result;
    }

    public long getElapsedTime() {
        return this.time;
    }


    public void setElapsedTime(long elapsedTime) {
        this.time = elapsedTime;
    }



}
