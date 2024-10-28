package persistence;

import business.User;
import functions.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl extends MySQLDao implements UserDao {
    public UserDaoImpl(String dbName) {
        super(dbName);
    }

    public UserDaoImpl() {
        super();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public User login(String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("Unable to connect to the database!");
            }

            String query = "SELECT * FROM Users WHERE username = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String storedSalt = rs.getString("salt");
                String computedHash = PasswordHash.hashPassword(password, storedSalt);

                if (computedHash.equals(storedHash)) {
                    user = new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            storedHash,
                            storedSalt,
                            rs.getString("email"),
                            rs.getDate("registration_date").toLocalDate()
                    );
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return user;
    }

    @Override
    public boolean register(User user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("Unable to connect to the database!");
            }

            String checkQuery = "SELECT COUNT(*) FROM Users WHERE username = ?";
            stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return false;
            }

            String salt = PasswordHash.generateSalt();
            String hashedPassword = PasswordHash.hashPassword(user.getPassword(), salt);

            String query = "INSERT INTO Users (username, password, salt, email, registration_date) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, salt);
            stmt.setString(4, user.getEmail());
            stmt.setDate(5, Date.valueOf(LocalDate.now()));

            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}