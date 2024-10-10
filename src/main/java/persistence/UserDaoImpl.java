package persistence;

import business.User;

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
        return null;
    }
}