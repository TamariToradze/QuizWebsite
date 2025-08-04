package Test;

import Models.passwordHasher;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class PasswordHasherTest {

    @Test
    public void generateSaltTest() {
        String salt_one = passwordHasher.generateSalt();
        String salt_two = passwordHasher.generateSalt();

        assertNotNull(salt_one, "salt should not be null");
        assertNotNull(salt_two, "salt should not be null");
        assertNotEquals(salt_one, salt_two, "salts should not be the same");
    }

    @Test
    public void hashAndCheckPasswordTest() throws NoSuchAlgorithmException {
        String password = "password";
        String salt = passwordHasher.generateSalt();

        String hashedPassword = passwordHasher.hash(password, salt);

        assertNotNull(hashedPassword, "hashed password should not be null");

        assertTrue("password should match hashed password",
                passwordHasher.isCorrectPassword(password, salt, hashedPassword));

        String wrongPassword = "wrongpassword";

        assertFalse("wrong password should not match hashed password",
                passwordHasher.isCorrectPassword(wrongPassword, salt, hashedPassword));
    }
}