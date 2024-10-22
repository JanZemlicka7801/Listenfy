package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

public class MySQLDao {
    private Properties properties; // Holds database configuration properties
    private String databaseName = "listenify"; //db name

    public MySQLDao() {
    }

    public MySQLDao(String propFilename){
        properties = new Properties();
        try {
            //gets the path of database.properties
            String path = Thread.currentThread().getContextClassLoader().getResource(propFilename).getPath();

            // Load properties from the specified file
            properties.load(new FileInputStream(path));
        }catch (IOException e){
            System.out.println("Error while trying to load properties from " + propFilename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates connection with database using the database.properties file as pram
     *
     * @return A Connection object if successful, null otherwise.
     * @author Seb Mathews-Lynch
     */
    public Connection getConnection(){

        // Retrieve database connection properties
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String database = properties.getProperty("database");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password", "");

        try{
            // Load the JDBC driver
            Class.forName(driver);

            try{
                // Create and return a connection to the database
                Connection con = DriverManager.getConnection(url+database, username, password);
                return con;
            } catch (SQLException e) {
                // Handle SQL exceptions during connection attempts
                System.out.println(LocalDateTime.now() + ": SQLException trying to connect to " + url + database);
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            // Handle the case where the JDBC driver class cannot be found
            System.out.println("ClassNotFoundException while trying to load MySQL driver");
            System.out.println("Error: " + e.getMessage());
        }
        return null; // Return null if the connection fails
    }
}
