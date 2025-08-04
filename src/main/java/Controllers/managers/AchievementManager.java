package Controllers.managers;

import Dao.AchievementDAO;
import Models.Achievement;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



//Add AchievementManager class

public class AchievementManager {
    public static final String ATTRIBUTE_NAME = "AchievementManager";
    private AchievementDAO ach;

    public AchievementManager() {
        SQLConnector sqlConnector = new SQLConnector();
        ach = new AchievementDAO(sqlConnector.dataSource);
    }

    //getallAchievement from dao
    public Set<Achievement> getAllAchievements() {
        try {
            return ach.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//get achievement using id
    public Achievement getAchievement(int id) {
        try {
            return ach.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//create new achievement
    public void createAchievement(Achievement achievement) {
        try {
            ach.createNewAchievement(achievement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//update achievement using dao
    public void updateAchievement(Achievement achievement) {
        try {
            ach.updateAchievement(achievement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//delete achievement  using id
    public void deleteAchievement(int achid) {
        try {
            ach.deleteAchievement(achid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//store all achievement of user
    public Set<Achievement> getAllAchievemtnsByUser(Set<Integer> achid) throws SQLException {
        Set<Achievement> sm = new HashSet<>();
        for(Integer id: achid) {
            Achievement achievement = ach.get(id);
            sm.add(achievement);
        }
        return sm;
    }


}