package persistence;

import business.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoImpl extends MySQLDao implements ArtistDao {
    public ArtistDaoImpl() {
        super();
    }

    public ArtistDaoImpl(String dbName) {
        super(dbName);
    }

    /**
     * Retrieves all artists from the database.
     *
     * @return a list of Artist objects
     * @throws SQLException if a database access error occurs
     * @auther Seb Mathews-Lynch
     */
    @Override
    public List<Artist> getAllArtists() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Artist> artList = new ArrayList<>();

        try {
            conn = super.getConnection();
            if (conn == null) {
                throw new SQLException("Can not connect to database");
            }

            String query = "SELECT * FROM artists";
            pstmt = conn.prepareStatement(query);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Artist dbArtist = new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("artist_first_name"),
                        rs.getString("artist_last_name"),
                        rs.getBoolean("band"),
                        rs.getString("description")
                );
                artList.add(dbArtist);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching artists: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return artList;
    }

    public static void main(String[] args) throws SQLException {
        try {
            ArtistDao ad = new ArtistDaoImpl("database.properties");

            List<Artist> artistList = ad.getAllArtists();

            for (Artist artist : artistList) {
                System.out.println(artist);
            }

        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
