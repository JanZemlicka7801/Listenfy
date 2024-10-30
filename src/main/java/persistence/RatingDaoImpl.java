package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDaoImpl extends MySQLDao implements RatingDao {

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
        // Insert or update the rating if it already exists
        String query = "INSERT INTO Ratings (user_id, song_id, rating) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE rating = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);
            ps.setInt(3, rating);
            ps.setInt(4, rating);
            ps.executeUpdate();
            return true;
        }
    }

    @Override
    public List<String> viewRatedSongs(int userId) throws SQLException {
        List<String> ratedSongs = new ArrayList<>();
        String query = "SELECT s.title, r.rating FROM Songs s "
                + "JOIN Ratings r ON s.song_id = r.song_id "
                + "WHERE r.user_id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String songInfo = "Song: " + rs.getString("title") + ", Rating: " + rs.getInt("rating");
                ratedSongs.add(songInfo);
            }
        }
        return ratedSongs;
    }

    @Override
    public String getTopRatedSong() throws SQLException {
        String query = "SELECT s.title, AVG(r.rating) as avg_rating FROM Songs s "
                + "JOIN Ratings r ON s.song_id = r.song_id "
                + "GROUP BY s.song_id "
                + "ORDER BY avg_rating DESC LIMIT 1";
        try(Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {

            if(rs.next()) {
                return "Top-Rated Song: " + rs.getString("title") + ", Average Rating: " + rs.getDouble("avg_rating");
            }
        }
        return "No ratings found.";
    }

    @Override
    public String getTheMostPopularSong() throws SQLException {
        String query = "SELECT s.title, COUNT(ps.playlist_id) as playlist_count FROM Songs s "
                + "JOIN Playlist_Songs ps ON s.song_id = ps.song_id "
                + "GROUP BY s.song_id "
                + "ORDER BY playlist_count DESC LIMIT 1";

        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return "Most Popular Song: " + rs.getString("title") + ", In Playlists: " + rs.getInt("playlist_count");
            }
        }
        return "No most popular song.";
    }
}
