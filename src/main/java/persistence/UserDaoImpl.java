package persistence;

import business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends MySQLDao implements UserDao {
    public UserDaoImpl(String dbName){
        super(dbName);
    }
    public UserDaoImpl(){
        super();
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }
    @Override
    public User login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn=getConnection();
            if (conn == null){
                throw new SQLException("Unable to connect to database!");
            }

            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();;

            if (rs.next()){
                user = new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("registrationDate").toLocalDateTime().toLocalDate());
            }
        }finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return user;
    }

    @Override
    public boolean register(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("Unable to connect to the database!");
            }

            String query = "INSERT INTO Users (username, password, email, registration_date) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());  // Ideally, password should be hashed
            stmt.setString(3, user.getEmail());
            stmt.setDate(4, java.sql.Date.valueOf(user.getRegistrationDate()));

            return stmt.executeUpdate() > 0;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}