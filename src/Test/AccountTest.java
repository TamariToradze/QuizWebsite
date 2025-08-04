package Test;

import Models.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() {
        account = new Account(
                "jgushiann",
                "Nini",
                "Jgushia",
                "ninimagaria",
                "jgushiann2003@gmail.com",
                "http://example.com/profile.jpg",
                "randomSalt"
        );
    }

    @Test
    public void getUsernameTest() {
        String expected = "jgushiann";
        assertEquals(expected, account.getUserName());
    }

    @Test
    public void setUsernameTest() {
        account.setUserName("njgushia");
        String expected = "njgushia";
        assertEquals(expected, account.getUserName());
    }

    @Test
    public void getFirstNameTest() {
        String expected = "Nini";
        assertEquals(expected, account.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        account.setFirstName("Ninutsa");
        String expected = "Ninutsa";
        assertEquals(expected, account.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        String expected = "Jgushia";
        assertEquals(expected, account.getLastName());
    }

    @Test
    public void setLastNameTest() {
        account.setLastName("jgushia");
        String expected = "jgushia";
        assertEquals(expected, account.getLastName());
    }

    @Test
    public void getPasswordTest() {
        String expected = "ninimagaria";
        assertEquals(expected, account.getPassword());
    }

    @Test
    public void setPasswordTest() {
        account.setPassword("ninimagaria123");
        String expected = "ninimagaria123";
        assertEquals(expected, account.getPassword());
    }

    @Test
    public void getEmailTest() {
        String expected = "jgushiann2003@gmail.com";
        assertEquals(expected, account.getEmail());
    }

    @Test
    public void setEmailTest() {
        account.setEmail("nini@gmail.com");
        String expected = "nini@gmail.com";
        assertEquals(expected, account.getEmail());
    }

    @Test
    public void getImageTest() {
        String expected = "http://example.com/profile.jpg";
        assertEquals(expected, account.getImageUrl());
    }

    @Test
    public void setImageTest() {
        account.setImageUrl("http://example.com/new_profile.jpg");
        String expected = "http://example.com/new_profile.jpg";
        assertEquals(expected, account.getImageUrl());
    }

    @Test
    public void getSaltTest() {
        String expected = "randomSalt";
        assertEquals(expected, account.getSalt());
    }

    @Test
    public void setSaltTest() {
        account.setSalt("newSalt");
        String expected = "newSalt";
        assertEquals(expected, account.getSalt());
    }

    @Test
    public void getFriendsTest() {
        List<String> friends = Arrays.asList("Tamuna", "Marta");
        account.setFriends(friends);
        assertEquals(friends, account.getFriends());
    }

    @Test
    public void setFriendsTest() {
        List<String> friends = Arrays.asList("Luka1", "Luka2");
        account.setFriends(friends);
        assertEquals(friends, account.getFriends());
    }

    @Test
    public void getAchievementsTest() {
        Set<Integer> achievements = Set.of(1,2,3);
        account.setAchievementIds(achievements);
        assertEquals(achievements, account.getAchievementIds());
    }

    @Test
    public void setAchievementsTest() {
        Set<Integer> achievements = Set.of(11,22,33);
        account.setAchievementIds(achievements);
        assertEquals(achievements, account.getAchievementIds());
    }

    @Test
    public void toStringTest() {
        String expected = "Account{" +
                ", userName='jgushiann'" +
                ", firstName='Nini'" +
                ", lastName='Jgushia'" +
                ", email='jgushiann2003@gmail.com'" +
                ", imageUrl='http://example.com/profile.jpg'" +
                ", friends=[]" +
                ", salt=randomSalt" +
                ", password=ninimagaria" +
                '}';
        assertEquals(expected, account.toString());
    }
}