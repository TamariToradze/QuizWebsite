package Models;

//achievements class
public class Achievement {
    private int achid;
    private String achname;
    private String achurl;
    private String text;


    public Achievement(int achievementId, String achievementName, String achievementUrl, String achievementDescription) {
        this.achid = achievementId;
        this.achname = achievementName;
        this.achurl = achievementUrl;
        this.text = achievementDescription;
    }

    public int getAchievementId() {
        return achid;
    }


    public void setAchievementId(int id) {
        this.achid = id;
    }


    public String getAchievementName() {
        return achname;
    }


    public void setAchievementName(String nm) {
        this.achname = nm;
    }


    public String getAchievementUrl() {
        return achurl;
    }


    public void setAchievementUrl(String url) {
        this.achurl = url;
    }

    public String getAchievementDescription() {
        return text;
    }


    public void setAchievementDescription(String text) {
        this.text = text;
    }
}