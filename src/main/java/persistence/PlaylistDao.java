package persistence;

import business.Playlist;
import business.Song;

import java.sql.SQLException;
import java.util.List;

public interface PlaylistDao {
    boolean createPlaylist(int userId, String name, boolean isPublic) throws SQLException;

    // 4.b Editing playlist
    boolean addSongToPlaylist(int playlistId, int songId) throws SQLException;
    boolean removeSongFromPlaylist(int playlistId, int songId) throws SQLException;
    boolean renamePlaylist(int playlistId, String newName) throws SQLException;
    boolean isPlaylistOwner(int playlistId, int userId) throws SQLException;

    // 4.c Viewing playlists
    List<Playlist> getUserPlaylists(int userId) throws SQLException;
    List<Playlist> getPublicPlaylists() throws SQLException;

    // 4.d Viewing contents
    List<Song> getPlaylistSongs(int playlistId) throws SQLException;
    boolean canUserAccessPlaylist(int playlistId, int userId) throws SQLException;
    Playlist getPlaylistById(int playlistId) throws SQLException;

}
