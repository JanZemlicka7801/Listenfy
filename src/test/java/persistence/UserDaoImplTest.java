package persistence;

import business.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private UserDao userDao;
    private final String TEST_USERNAME = "testuser";
    private final String TEST_PASSWORD = "testpass123";
    private final String TEST_EMAIL = "test@gmail.com";

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl("test.database.properties");
    }

    @Test
    void register() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        User user = new User(TEST_USERNAME, TEST_PASSWORD, "", TEST_EMAIL, LocalDate.now());
        boolean result = userDao.register(user);
        assertTrue(result, "Registration failed");
        User duplicateUser = new User(TEST_USERNAME, "differentpass", "", "different@gmail.com", LocalDate.now());
        result = userDao.register(duplicateUser);
        assertFalse(result, "Duplicate username was allowed");
    }

    @Test
    void login() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userDao.login(TEST_USERNAME, TEST_PASSWORD);
        assertNotNull(user, "Login failed with correct credentials");
        assertEquals(TEST_USERNAME, user.getUsername());
        User wrongPassUser = userDao.login(TEST_USERNAME, "wrongpass");
        assertNull(wrongPassUser, "Login succeeded with wrong password");
        User nonExistentUser = userDao.login("doesntexist", "anypass");
        assertNull(nonExistentUser, "Login succeeded with non-existent user");
    }

    @Test
    void getAllUsers() throws SQLException {
        assertNull(userDao.getAllUsers(), "getAllUsers should return null");
    }
}