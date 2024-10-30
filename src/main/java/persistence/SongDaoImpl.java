package persistence;

import business.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SongDaoImpl extends MySQLDao implements SongDao {
    public SongDaoImpl() {
        super();
    }

    public SongDaoImpl(String propFilename) {
        super(propFilename);
    }

    /**
     * @param albumId
     * @return
     */
    @Override
    public List<Song> getAllSongsByAlbumId(int albumId) {
        List<Song> songs = new ArrayList<>();

        String query = "SELECT * FROM songs WHERE album_id = ?";

        Connection conn = super.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, albumId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Song song = new Song(
                            rs.getInt("song_id"),
                            rs.getInt("album_id"),
                            rs.getString("song_title"),
                            rs.getTime("duration")
                    );
                    songs.add(song);
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while running the query or processing results.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while preparing the SQL statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return songs;
    }

    public static void main(String[] args) {
        SongDao s = new SongDaoImpl("database.properties");
        System.out.println(s.getAllSongsByAlbumId(1));
    }
}
