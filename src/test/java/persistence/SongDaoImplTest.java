package persistence;

import business.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongDaoImplTest {
    private SongDaoImpl songDao;

    @BeforeEach
    void setUp() {
        songDao = new SongDaoImpl("database.properties");
    }
    @Test
    void testGetAllSongsByAlbumId_withValidId() {
        int albumId = 1;
        List<Song> songs = songDao.getAllSongsByAlbumId(albumId);
        assertNotNull(songs, "Songs list should not be null");
        assertFalse(songs.isEmpty(), "Songs list should not be empty");
    }

    @Test
    void testGetAllSongsByAlbumId_withInvalidId() {
        int invalidAlbumId = -1;
        List<Song> songs = songDao.getAllSongsByAlbumId(invalidAlbumId);
        assertNotNull(songs, "Songs list should not be null");
        assertTrue(songs.isEmpty(), "Songs list should be empty for invalid album ID");
    }

    @Test
    void testGetSongByTitle() {
        String title = "First Class";
        Song song = songDao.getSongByTitle(title);

        assertNotNull(song, "Song should not be null");
        assertEquals(title, song.getSongTitle(), "Song title should match the expected value");
    }

    @Test
    void testGetSongByTitle_withNonExistentTitle() {
        String nonExistentTitle = "Non Existent Title";
        Song song = songDao.getSongByTitle(nonExistentTitle);
        assertNull(song, "Song should be null for a non-existent title");
    }

    @Test
    void testSearchSongsByArtist_withValidArtist() {
        String firstName = "Jack";
        String lastName = "Harlow";
        List<Song> songs = songDao.searchSongsByArtist(firstName, lastName);
        assertNotNull(songs, "Songs list should not be null");
        assertFalse(songs.isEmpty(), "Songs list should not be empty for an existing artist");
    }

    @Test
    void testSearchSongsByArtist_withNonExistentArtist() {
        String firstName = "NonExistentFirstName";
        String lastName = "NonExistentLastName";
        List<Song> songs = songDao.searchSongsByArtist(firstName, lastName);
        assertNotNull(songs, "Songs list should not be null");
        assertTrue(songs.isEmpty(), "Songs list should be empty for a non-existent artist");
    }
}