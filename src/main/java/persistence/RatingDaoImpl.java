package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDaoImpl extends MySQLDao implements RatingDao {

    public RatingDaoImpl(String dbName) {super(dbName);}

    /**
     *
     *
     * @param userId
     * @param songId
     * @param rating
     * @return
     * @throws SQLException
     */
    @Override
    public boolean rateSong(int userId, int songId, int rating) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("Unable to connect to the database!");
            }
            String query = "INSERT INTO Ratings (user_id, song_id, rating) VALUES (?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE rating = ?";
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, userId);
            stmt.setInt(2, songId);
            stmt.setInt(3, rating);
            stmt.setInt(4, rating);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> viewRatedSongs(int userId) throws SQLException {
        List<String> ratedSongs = new ArrayList<>();
        String query = "SELECT s.song_title, r.rating FROM Songs s "
                + "JOIN Ratings r ON s.song_id = r.song_id "
                + "WHERE r.user_id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String songInfo = "Song: " + rs.getString("song_title") + ", Rating: " + rs.getInt("rating");
                ratedSongs.add(songInfo);
            }
        } catch (SQLException e) {
            System.err.println("SQLException in viewRatedSongs: " + e.getMessage());
            e.printStackTrace();
        }
        return ratedSongs;
    }

    @Override
    public String getTopRatedSong() throws SQLException {
        String query = "SELECT s.song_title, AVG(r.rating) as avg_rating FROM Songs s "
                + "JOIN Ratings r ON s.song_id = r.song_id "
                + "GROUP BY s.song_id "
                + "ORDER BY avg_rating DESC LIMIT 1";

        try (Connection conn = super.getConnection()) {
            if (conn == null) {
                System.err.println("Failed to establish a database connection.");
                return "No ratings found.";
            }

            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return "Top-Rated Song: " + rs.getString("song_title")
                            + ", Average Rating: " + rs.getDouble("avg_rating");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getTopRatedSong: " + e.getMessage());
            e.printStackTrace();
        }
        return "No ratings found.";
    }

    @Override
    public String getTheMostPopularSong() throws SQLException {
        String query = "SELECT s.song_title, COUNT(ps.playlist_id) as playlist_count FROM Songs s "
                + "JOIN Playlist_Songs ps ON s.song_id = ps.song_id "
                + "GROUP BY s.song_id "
                + "ORDER BY playlist_count DESC LIMIT 1";

        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return "Most Popular Song: " + rs.getString("song_title")
                        + ", In Playlists: " + rs.getInt("playlist_count");
            }
        } catch (SQLException e) {
            System.err.println("SQLException in getTheMostPopularSong: " + e.getMessage());
            e.printStackTrace();
        }
        return "No most popular song.";
    }
}
