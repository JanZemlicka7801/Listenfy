package persistence;

import business.Playlist;
import business.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistDaoImplTest {
    private PlaylistDao playlistDao;
    private final int TEST_USER_ID = 1;
    private final int OTHER_USER_ID = 2;
    private final int TEST_SONG_ID = 1;
    private int testPlaylistId;

    @BeforeEach
    void setUp() {
        playlistDao = new PlaylistDaoImpl("test.database.properties");
    }

    private int createTestPlaylist() throws SQLException {
        playlistDao.createPlaylist(TEST_USER_ID, "Test Playlist", false);
        List<Playlist> playlists = playlistDao.getUserPlaylists(TEST_USER_ID);
        return playlists.get(playlists.size() - 1).getPlaylist_id();
    }

    @Test
    void createPlaylist() throws SQLException {
        boolean result = playlistDao.createPlaylist(TEST_USER_ID, "Test Private Playlist", false);
        assertTrue(result, "Creation of private playlist failed");
        result = playlistDao.createPlaylist(TEST_USER_ID, "Test Public Playlist", true);
        assertTrue(result, "Creation of public playlist failed");
        List<Playlist> userPlaylists = playlistDao.getUserPlaylists(TEST_USER_ID);
        assertTrue(userPlaylists.size() >= 2, "Expected at least 2 playlists after creation");
    }

    @Test
    void renamePlaylist() throws SQLException {
        testPlaylistId = createTestPlaylist();
        String newName = "Renamed Playlist";
        boolean result = playlistDao.renamePlaylist(testPlaylistId, newName);
        assertTrue(result, "Playlist rename operation failed");
        Playlist playlist = playlistDao.getPlaylistByName(newName);
        assertNotNull(playlist, "Renamed playlist not found in database");
        assertEquals(newName, playlist.getPlaylist_name());
    }

    @Test
    void getUserPlaylists() throws SQLException {
        testPlaylistId = createTestPlaylist();
        List<Playlist> playlists = playlistDao.getUserPlaylists(TEST_USER_ID);
        assertFalse(playlists.isEmpty(), "No playlists found for user");
        Playlist playlist = playlists.get(0);
        assertEquals(TEST_USER_ID, playlist.getUser_id());
        assertNotNull(playlist.getPlaylist_name(), "Playlist name is null");
        assertNotNull(playlist.getCreation_date(), "Creation date is null");
    }

    @Test
    void getPublicPlaylists() throws SQLException {
        playlistDao.createPlaylist(TEST_USER_ID, "Test Public Playlist", true);
        List<Playlist> publicPlaylists = playlistDao.getPublicPlaylists();
        assertFalse(publicPlaylists.isEmpty(), "No public playlists found");
        assertTrue(publicPlaylists.get(0).is_public(), "Playlist marked as public but isPublic=false");
    }

    @Test
    void addSongToPlaylist() throws SQLException {
        testPlaylistId = createTestPlaylist();
        boolean result = playlistDao.addSongToPlaylist(testPlaylistId, TEST_SONG_ID);
        assertTrue(result, "Failed to add song to playlist");
        List<Song> songs = playlistDao.getPlaylistSongs(testPlaylistId);
        assertFalse(songs.isEmpty(), "Playlist is empty after adding song");
        assertEquals(TEST_SONG_ID, songs.get(0).getSongId(), "Song ID mismatch after addition");
    }

    @Test
    void removeSongFromPlaylist() throws SQLException {
        testPlaylistId = createTestPlaylist();
        playlistDao.addSongToPlaylist(testPlaylistId, TEST_SONG_ID);
        boolean result = playlistDao.removeSongFromPlaylist(testPlaylistId, TEST_SONG_ID);
        assertTrue(result, "Failed to remove song from playlist");
        List<Song> songs = playlistDao.getPlaylistSongs(testPlaylistId);
        assertTrue(songs.isEmpty(), "Songs still exist in playlist after removal");
    }

    @Test
    void getPlaylistSongs() throws SQLException {
        testPlaylistId = createTestPlaylist();
        playlistDao.addSongToPlaylist(testPlaylistId, TEST_SONG_ID);
        List<Song> songs = playlistDao.getPlaylistSongs(testPlaylistId);
        assertFalse(songs.isEmpty(), "No songs found in playlist");
        Song song = songs.get(0);
        assertNotNull(song.getSongTitle(), "Song title is null");
        assertNotNull(song.getDuration(), "Song duration is null");
    }

    @Test
    void isPlaylistOwner() throws SQLException {
        testPlaylistId = createTestPlaylist();
        assertTrue(playlistDao.isPlaylistOwner(testPlaylistId, TEST_USER_ID), "Owner permission check failed");
        assertFalse(playlistDao.isPlaylistOwner(testPlaylistId, OTHER_USER_ID), "Non-owner wrongly identified as owner");
    }

    @Test
    void canUserAccessPlaylist() throws SQLException {
        testPlaylistId = createTestPlaylist();
        assertTrue(playlistDao.canUserAccessPlaylist(testPlaylistId, TEST_USER_ID), "Owner denied access to own playlist");
        assertFalse(playlistDao.canUserAccessPlaylist(testPlaylistId, OTHER_USER_ID), "Non-owner granted access to private playlist");
        boolean result = playlistDao.createPlaylist(TEST_USER_ID, "Public Test Playlist", true);
        assertTrue(result, "Failed to create public playlist");
        List<Playlist> playlists = playlistDao.getPublicPlaylists();
        assertFalse(playlists.isEmpty(), "Public playlist not found");
        int publicPlaylistId = playlists.get(0).getPlaylist_id();

        assertTrue(playlistDao.canUserAccessPlaylist(publicPlaylistId, OTHER_USER_ID), "User denied access to public playlist");
    }
}