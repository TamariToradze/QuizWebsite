package Controllers.managers;

import Dao.AnnouncementDAO;
import Models.Announcement;
import utils.SQLConnector;

import java.util.List;

public class AnnouncementManager {
    public static final String ATTRIBUTE_NAME = "AnnouncementManager";
    private AnnouncementDAO anndao;
    private SQLConnector con;

    public AnnouncementManager() {
        con = new SQLConnector();
        anndao = new AnnouncementDAO(con.dataSource);
    }
//Retrieve all announcements
    public List<Announcement> getAnnouncements() {
        try {
            return anndao.getAllAnnouncements();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//Get announcement by ID
    public Announcement getAnnouncement(int id) {
        try {
            return anndao.readAnnouncement(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//Create new announcement
    public void createAnnouncement(Announcement text) {
        try {
            anndao.createAnnouncement(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// Update existing announcement
    public void updateAnnouncement(Announcement text) {
        try {
            anndao.updateAnnouncement(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Remove announcement
    public void deleteAnnouncement(int annid) {
        try {
            anndao.deleteAnnouncement(annid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}