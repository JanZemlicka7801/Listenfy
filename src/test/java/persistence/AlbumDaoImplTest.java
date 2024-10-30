package persistence;

import business.Albums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumDaoImplTest {
    private AlbumDaoImpl albumDao;

    @BeforeEach
    //Jan Omar Import this class to save on writing repeated code :)
    public void setUp() {
        albumDao = new AlbumDaoImpl("test.database.properties");
    }
    @Test
    public void testGetAlbumsByArtistName_WithFirstNameAndLastName() {
        String firstName = "Jack";
        String lastName = "Harlow";

        List<Albums> albums = albumDao.getAlbumsByArtistName(firstName, lastName);

        assertNotNull(albums, "The album list should not be null.");
        assertFalse(albums.isEmpty(), "Jack Harlow should have albums in the database.");
        assertEquals(3, albums.size(), "Jack Harlow should have exactly 3 albums.");
    }
    @Test
    public void testGetAlbumsByArtistName_WithLastNameOnly() {
        String firstName = null;
        String lastName = "OneRepublic";

        List<Albums> albums = albumDao.getAlbumsByArtistName(firstName, lastName);

        assertNotNull(albums, "The album list should not be null.");
        assertFalse(albums.isEmpty(), "OneRepublic should have albums in the database.");
        assertEquals(3, albums.size(), "OneRepublic should have exactly 3 albums.");
    }

    @Test
    public void testGetAlbumsByArtistName_WithNoMatchingArtist() {
        String firstName = "Fake";
        String lastName = "Artist";

        List<Albums> albums = albumDao.getAlbumsByArtistName(firstName, lastName);

        assertNotNull(albums, "The album list should not be null.");
        assertTrue(albums.isEmpty(), "No albums should be found for a nonexistent artist.");
    }
    @Test
    public void testGetAlbumIdByAlbumTitle_WithValidTitle() {
        // Jack Harlow song
        String albumTitle = "Come Home the Kids Miss You";

        int albumId = albumDao.getAlbumIdByAlbumTitle(albumTitle);

        assertTrue(albumId > 0, "The album ID should be a positive integer for a valid album title.");
    }

    @Test
    public void testGetAlbumIdByAlbumTitle_WithInvalidTitle() {
        String albumTitle = "Fake Album";

        int albumId = albumDao.getAlbumIdByAlbumTitle(albumTitle);

        assertEquals(-1, albumId, "The album ID should be -1 for a fake album title.");
    }
}