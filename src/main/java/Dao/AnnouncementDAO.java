package Dao;

import Models.Announcement;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {
    private BasicDataSource ds;

    public AnnouncementDAO(BasicDataSource dataSource) {
        this.ds = dataSource;
    }
//Retrieve all announcements
    public void createAnnouncement(Announcement announcement) throws SQLException {
        String query = "INSERT INTO announcements (username, message, announcementTime) VALUES (?, ?, ?)";
        try (Connection cn = ds.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1, announcement.getUsername());
            st.setString(2, announcement.getMessage());
            st.setTimestamp(3, announcement.getAnnouncementTime());
            st.executeUpdate();
        }
    }
//Get announcement by ID
    public Announcement readAnnouncement(int id) throws SQLException {
        String table = "SELECT * FROM announcements WHERE announcementId = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement statement = connection.prepareStatement(table)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return new Announcement(
                        res.getInt("announcementId"),
                        res.getString("username"),
                        res.getString("message"),
                        res.getTimestamp("announcementTime")
                );
            }
            return null;
        }
    }
//Add new announcement
    public List<Announcement> getAllAnnouncements() throws SQLException {
        List<Announcement> ann = new ArrayList<>();
        String table = "SELECT * FROM announcements";
        try (Connection connection = ds.getConnection();
             PreparedStatement st = connection.prepareStatement(table);
             ResultSet res = st.executeQuery()) {
            while (res.next()) {
                ann.add(new Announcement(
                        res.getInt("announcementId"),
                        res.getString("username"),
                        res.getString("message"),
                        res.getTimestamp("announcementTime")
                ));
            }
        }
        return ann;
    }
// Update existing announcement
    public void updateAnnouncement(Announcement announcement) throws SQLException {
        String table = "UPDATE announcements SET username = ?, message = ?, announcementTime = ? WHERE announcementId = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement st = connection.prepareStatement(table)) {
            st.setString(1, announcement.getUsername());
            st.setString(2, announcement.getMessage());
            st.setTimestamp(3, announcement.getAnnouncementTime());
            st.setInt(4, announcement.getAnnouncementId());
            st.executeUpdate();
        }
    }
//Remove announcement
    public void deleteAnnouncement(int announcementId) throws SQLException {
        String table = "DELETE FROM announcements WHERE announcementId = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement st = connection.prepareStatement(table)) {
            st.setInt(1, announcementId);
            st.executeUpdate();
        }
    }
}