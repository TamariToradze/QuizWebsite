package Models;

import java.sql.Time;
import java.sql.Timestamp;

public class Announcement {
    private int annid;
    private String usernm;
    private String text;
    private Timestamp date;

    public Announcement(String username, String message) {
        annid = -1;
        this.usernm = username;
        this.text = message;
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public Announcement(int announcementId, String username, String message, Timestamp announcementTime) {
        this.annid = announcementId;
        this.usernm = username;
        this.text = message;
        this.date = announcementTime;
    }

    public String getUsername() {
        return usernm;
    }

    public void setUsername(String username) {
        this.usernm = username;
    }

    public Timestamp getAnnouncementTime() {
        return date;
    }

    public void setAnnouncementTime(Timestamp announcementTime) {
        this.date = announcementTime;
    }

    public String getMessage() {
        return text;
    }

    public void setMessage(String message) {
        this.text = message;
    }

    public int getAnnouncementId() {
        return annid;
    }

    public void setAnnouncementId(int annid) {
        this.annid = annid;
    }

}
