package Controllers.managers;

import Dao.AccountsDao;
import Models.Account;
//import Models.LeaderboardEntry;
import Models.LeaderboardEntry;
import Models.Quiz;
import Models.passwordHasher;
//import Models.Quiz;
import utils.SQLConnector;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The AccountManager class manages user accounts, allowing for the creation of new users and
 * verification of login credentials.
 */
public class AccountManager {
    public static final String ATTRIBUTE_NAME = "AccountManager";
    private final AccountsDao accountDAO;
    private final SQLConnector sqlConnector;

    /**
     * Constructs an AccountManager with a predefined set of user accounts.
     */
    public AccountManager() {
        sqlConnector = new SQLConnector();
        accountDAO = new AccountsDao(sqlConnector.dataSource);
    }

    /**
     * Creates a new user account if the username does not already exist.
     *
     * @param account new account
     * @return true if the account was successfully created, false if the username already exists
     */
    public void createNewUser(Account account) {
        accountDAO.createAccount(account);
    }

    /**
     * Checks if an account with the given username exists.
     *
     * @param username the username to check for existence
     * @return true if the account exists, false otherwise
     */
    public boolean accountExists(String username) {
        return accountDAO.readAccount(username) != null;
    }

    /**
     * Validates the login credentials for a given username and password.
     *
     * @param username the username of the account
     * @param password the password of the account
     * @return true if the username exists and the password matches, false otherwise
     */
    public boolean successfulLogin(String username, String password) throws NoSuchAlgorithmException {
        if(accountExists(username)){
            Account account = accountDAO.readAccount(username);
            System.out.println(account.toString());
            return passwordHasher.isCorrectPassword(password, account.getSalt(), account.getPassword());
        } else return false;
    }


    /**
     * Retrieves an account based on the username.
     *
     * @param username the username of the account
     * @return the account corresponding to the given username, or null if no such account exists
     */
    public Account getAccount(String username) {
        return accountDAO.readAccount(username);
    }

    /**
     * Updates an account based on the username.
     *
     * @param account  the account
     */
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public List<LeaderboardEntry> getLeaderboard() {
        return new ArrayList<>();
    }

    public List<Quiz> getNewQuizzes() {
        return new ArrayList<>();
    }

    public List<Account> getAccounts() throws SQLException {
       return accountDAO.getAllAccounts();
    }

    public boolean deleteAccount(String username){
        return accountDAO.deleteAccount(username);
    }

    public void makeAdmin(String username){
        Account account = accountDAO.readAccount(username);
        account.setAdmin(true);
        accountDAO.updateAccount(account);
    }

    public boolean isAdmin(String username){
        Account account = accountDAO.readAccount(username);
        return account.isAdmin();
    }
}

