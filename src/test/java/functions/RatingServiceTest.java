package functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.RatingDao;
import persistence.RatingDaoImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RatingServiceTest {
    private RatingDao ratingDao;

    @BeforeEach
    public void setUp() {
        ratingDao = new RatingDaoImpl("test.database.properties");
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
    void viewRatedSongs() {
    }

    @Test
    void getTopRatedSong() {
    }

    @Test
    void getMostPopularSong() {
    }
}