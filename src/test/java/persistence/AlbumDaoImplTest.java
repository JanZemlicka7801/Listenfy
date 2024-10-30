package persistence;

import business.Albums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumDaoImplTest {
    private AlbumDaoImpl albumDao;

    @BeforeEach
    //Import this class to save on writing repeated code :)
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
}