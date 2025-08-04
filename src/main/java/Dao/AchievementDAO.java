package Dao;

import Models.Achievement;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
//Create DAO implementation for Achievement operations

public class AchievementDAO {

    private BasicDataSource dataSource;

    public AchievementDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
     //for creating new record
    public void createNewAchievement(Achievement achievement) throws SQLException {
        String table = "INSERT INTO Achievement (achievementName, achievementUrl, achievementDescription) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(table)) {
            st.setString(1, achievement.getAchievementName());
            st.setString(2, achievement.getAchievementUrl());
            st.setString(3, achievement.getAchievementDescription());
            st.executeUpdate();
        }
    }

    //for achievement record
    public Achievement get(int achievementId) throws SQLException {
        String table = "SELECT * FROM Achievement WHERE achievementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(table)) {
            st.setInt(1, achievementId);
            try (ResultSet an = st.executeQuery()) {
                if (an.next()) {
                    return new Achievement(
                            an.getInt("achievementId"),
                            an.getString("achievementName"),
                            an.getString("achievementUrl"),
                            an.getString("achievementDescription")
                    );
                }
            }
        }
        return null;
    }

    //change history of achievement
    public void updateAchievement(Achievement achievement) throws SQLException {
        String table = "UPDATE Achievement SET achievementName = ?, achievementUrl = ?, achievementDescription = ? WHERE achievementId = ?";
        try (Connection  con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(table)) {
            st.setString(1, achievement.getAchievementName());
            st.setString(2, achievement.getAchievementUrl());
            st.setString(3, achievement.getAchievementDescription());
            st.setInt(4, achievement.getAchievementId());
            st.executeUpdate();
        }
    }
    //delete achivement data
    public void deleteAchievement(int achievementId) throws SQLException {
        String table = "DELETE FROM Achievement WHERE achievementId = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(table)) {
            st.setInt(1, achievementId);
            st.executeUpdate();
        }
    }
    //return all achievements
    public Set<Achievement> getAll() throws SQLException {
        String table = "SELECT * FROM Achievement";
        Set<Achievement> achievements = new HashSet<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(table);
             ResultSet an = st.executeQuery()) {
            while (an.next()) {
                Achievement ch = new Achievement(
                        an.getInt("achievementId"),
                        an.getString("achievementName"),
                        an.getString("achievementUrl"),
                        an.getString("achievementDescription")
                );
                achievements.add(ch);
            }
        }
        return achievements;
    }


}