package Dao;

import Models.Account;
//import Models.Quiz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Data Access Object (DAO) for the Account model.
 * Provides methods to perform CRUD operations on the Account table.
 */
public class AccountsDao {
    private final BasicDataSource dataSource;

    /**
     * Constructs an AccountDAO with the specified DataSource.
     *
     * @param dataSource the DataSource to be used for database connections.
     */
    public AccountsDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new account in the database.
     *
     * @param account the Account object containing the account details.
     */
    public void createAccount(Account account) {
        String query = "INSERT INTO Accounts (userName, firstName, lastName, password, email, imageUrl, salt, achievementIds, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(account, statement);
            statement.setBoolean(9, account.isAdmin());
            account.setAchievementIds(new HashSet<>());
            statement.setString(8, new Gson().toJson(account.getAchievementIds()));
            int rows = statement.executeUpdate();
            System.out.printf("rows affected: %d\n", rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setStatement(Account account, PreparedStatement statement) throws SQLException {
        statement.setString(1, account.getUserName());
        statement.setString(2, account.getFirstName());
        statement.setString(3, account.getLastName());
        statement.setString(4, account.getPassword());
        statement.setString(5, account.getEmail());
        statement.setString(6, account.getImageUrl());
        statement.setString(7, account.getSalt());
    }


    public Account readAccount(String username) {
        List<String> friends = new ArrayList<>();
        String query = "SELECT * FROM Accounts WHERE userName = ?";
        String queryFriends = "SELECT usernameFrom, usernameTo FROM Friends WHERE (usernameFrom = ? OR usernameTo = ?) AND isAccepted = TRUE";
        Account account = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement statementFriends = connection.prepareStatement(queryFriends)) {

            statement.setString(1, username);
            statementFriends.setString(1, username);
            statementFriends.setString(2, username);
            try (ResultSet resultSet = statement.executeQuery();
                 ResultSet resultSetFriends = statementFriends.executeQuery()) {
                if (resultSet.next()) {
                    account = new Account(
                            resultSet.getString("userName"),                // changed username->userName
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getString("imageUrl"),
                            resultSet.getString("salt")
                    );
                } else return null;

                while (resultSetFriends.next()) {
                    String usernameFrom = resultSetFriends.getString("usernameFrom");
                    String usernameTo = resultSetFriends.getString("usernameTo");

                    if (!usernameFrom.equals(username)) {
                        friends.add(usernameFrom);
                    } else {
                        friends.add(usernameTo);
                    }
                }
                account.setFriends(friends);
                account.setAdmin(resultSet.getBoolean("isAdmin"));
                setGson(resultSet, account);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }


    public void updateAccount(Account account) {
        String query = "UPDATE Accounts SET userName = ?, firstName = ?, lastName = ?, password = ?, email = ?, imageUrl = ?, salt = ?, achievementIds = ?, isAdmin = ? WHERE userName = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(account, statement);
            statement.setString(10, account.getUserName());
            statement.setBoolean(9, account.isAdmin());
            statement.setString(8, new Gson().toJson(account.getAchievementIds()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAccount(String username) {
        String query = "DELETE FROM Accounts WHERE userName = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Accounts";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getString("userName"),        //changed username->userName
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("imageUrl"),
                        resultSet.getString("salt")
                );

                // Deserialize JSON strings to ArrayLists
                setGson(resultSet, account);
                account.setAdmin(resultSet.getBoolean("isAdmin"));
                accounts.add(account);
            }
        }
        return accounts;
    }

    private void setGson(ResultSet resultSet, Account account) throws SQLException {
        Gson gson = new Gson();
        Type listType = new TypeToken<HashSet<Integer>>() {
        }.getType();
        HashSet<Integer> achievementIds = gson.fromJson(resultSet.getString("achievementIds"), listType);
        account.setAchievementIds(achievementIds);
    }
}