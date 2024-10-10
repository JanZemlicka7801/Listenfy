package persistence;

import business.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public User login(String username, String password) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
