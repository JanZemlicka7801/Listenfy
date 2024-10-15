package persistence;

import business.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArtistDaoImpl extends MySQLDao implements ArtistDao {
    public ArtistDaoImpl() {
        super();
    }

    public ArtistDaoImpl(String dbName) {
        super(dbName);
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Artist> getAllArtists() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Artist a1 = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("Can not connect to database");
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching artists: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        ArtistDao ad = new ArtistDaoImpl("database.properties");
        List list = ad.getAllArtists();

    }
}
