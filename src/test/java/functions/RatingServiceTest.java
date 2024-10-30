package functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.RatingDao;
import persistence.RatingDaoImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceTest {
    private RatingDao ratingDao;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        ratingDao = new RatingDaoImpl("test.database.properties");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void rateSong_ValidRating() throws SQLException {
        int userId = 1;
        int songId = 1;
        int rating = 4;

        boolean result = ratingDao.rateSong(userId, songId, rating);

        assertTrue(result, "The rating should be submitted successfully.");
    }

    @Test
    void rateSong_InvalidRating() {
        int userId = 1;
        int songId = 1;
        int invalidRating = 6;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ratingDao.rateSong(userId, songId, invalidRating);
        });

        assertEquals("Rating must be between 1 and 5.", exception.getMessage());
    }

    @Test
    void rateSong_InvalidUserId() {
        int invalidUserId = -1;
        int songId = 1;
        int rating = 4;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ratingDao.rateSong(invalidUserId, songId, rating);
        });

        assertEquals("User ID and Song ID must be positive integers.", exception.getMessage());
    }

    @Test
    void viewRatedSongs_UserHasRatedSongs() {
        int userId = 1;

        RatingService.viewRatedSongs(userId, ratingDao);

        String output = outContent.toString();
        assertTrue(output.contains("Your Rated Songs:"), "Expected 'Your Rated Songs:' in the output");
        assertTrue(output.contains("Song: First Class, Rating: 4"), "Expected rated song details in the output");
    }

    @Test
    void viewRatedSongs_UserHasNoRatedSongs() {
        int userId = 2;

        RatingService.viewRatedSongs(userId, ratingDao);

        String output = outContent.toString();
        assertTrue(output.contains("You haven't rated any songs."), "Expected message for no rated songs.");
    }

    @Test
    void viewTopRatedSong_SongExists() {
        RatingService.viewTopRatedSong(ratingDao);

        String output = outContent.toString();
        assertTrue(output.contains("Top-Rated Song:"), "Expected 'Top-Rated Song:' in the output.");
    }

    @Test
    void viewTopRatedSong_NoSongsRated() {
        RatingService.viewTopRatedSong(ratingDao);

        String output = outContent.toString();
        assertFalse(output.contains("No ratings found."), "Expected message for no ratings found.");
    }

    @Test
    void viewMostPopularSong_SongExists() {
        RatingService.viewMostPopularSong(ratingDao);

        String output = outContent.toString();
        assertFalse(output.contains("Most Popular Song:"), "Expected 'Most Popular Song:' in the output.");
    }

    @Test
    void viewMostPopularSong_NoSongsInPlaylists() {
        RatingService.viewMostPopularSong(ratingDao);

        String output = outContent.toString();
        assertFalse(output.contains("No popular song found."), "Expected message for no popular song.");
    }
}
