package persistence;

import business.Playlist;
import business.PlaylistSong;
import business.Song;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDaoImpl extends MySQLDao implements PlaylistDao {

    public PlaylistDaoImpl(String dbName) {
        super(dbName);
    }

    @Override
    public boolean createPlaylist(int userId, String name, boolean isPublic) throws SQLException {
        Connection conn = getConnection();
        String query = "INSERT INTO Playlists (user_id, playlist_name, is_public) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setBoolean(3, isPublic);
            return ps.executeUpdate() > 0;
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public boolean renamePlaylist(int playlistId, String newName) throws SQLException {
        Connection conn = getConnection();
        String query = "UPDATE Playlists SET playlist_name = ? WHERE playlist_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newName);
            ps.setInt(2, playlistId);
            return ps.executeUpdate() > 0;
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public List<Playlist> getUserPlaylists(int userId) throws SQLException {
        Connection conn = getConnection();
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlists WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                playlists.add(new Playlist(
                        rs.getInt("playlist_id"),
                        rs.getInt("user_id"),
                        rs.getString("playlist_name"),
                        rs.getBoolean("is_public"),
                        rs.getDate("creation_date").toLocalDate()
                ));
            }
        } finally {
            if (conn != null) conn.close();
        }
        return playlists;
    }

    @Override
    public List<Playlist> getPublicPlaylists() throws SQLException {
        Connection conn = getConnection();
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM Playlists WHERE is_public = true";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                playlists.add(new Playlist(
                        rs.getInt("playlist_id"),
                        rs.getInt("user_id"),
                        rs.getString("playlist_name"),
                        rs.getBoolean("is_public"),
                        rs.getDate("creation_date").toLocalDate()
                ));
            }
        } finally {
            if (conn != null) conn.close();
        }
        return playlists;
    }

    @Override
    public Playlist getPlaylistByName(String name) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT * FROM Playlists WHERE playlist_name = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Playlist(
                        rs.getInt("playlist_id"),
                        rs.getInt("user_id"),
                        rs.getString("playlist_name"),
                        rs.getBoolean("is_public"),
                        rs.getDate("creation_date").toLocalDate()
                );
            }
        } finally {
            if (conn != null) conn.close();
        }
        return null;
    }


    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) throws SQLException {
        Connection conn = getConnection();
        String query = "INSERT INTO Playlist_Songs (playlist_id, song_id) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            return ps.executeUpdate() > 0;
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) throws SQLException {
        Connection conn = getConnection();
        String query = "DELETE FROM Playlist_Songs WHERE playlist_id = ? AND song_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            return ps.executeUpdate() > 0;
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public List<Song> getPlaylistSongs(int playlistId) throws SQLException {
        Connection conn = getConnection();
        List<Song> songs = new ArrayList<>();
        String query = """
            SELECT s.* FROM Songs s 
            JOIN Playlist_Songs ps ON s.song_id = ps.song_id 
            WHERE ps.playlist_id = ? 
            ORDER BY s.song_title
        """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("song_id"),
                        rs.getInt("album_id"),
                        rs.getString("song_title"),
                        rs.getTime("duration")
                ));
            }
        } finally {
            if (conn != null) conn.close();
        }
        return songs;
    }

    @Override
    public boolean isPlaylistOwner(int playlistId, int userId) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT 1 FROM Playlists WHERE playlist_id = ? AND user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public boolean canUserAccessPlaylist(int playlistId, int userId) throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT 1 FROM Playlists WHERE playlist_id = ? AND (user_id = ? OR is_public = true)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, playlistId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } finally {
            if (conn != null) conn.close();
        }
    }
}