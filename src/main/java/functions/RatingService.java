package functions;

import persistence.RatingDao;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class RatingService {
    private final RatingDao ratingDao;

    public RatingService(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }

    public void rateSong(int userId, int songId, int rating) {
        try {
            if(rating < 1 || rating > 5) {
                System.out.println("Rating must be between 1 and 5.");
                return;
            }
            if (ratingDao.rateSong(userId, songId, rating)) {
                System.out.println("Rating submitted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while rating the song.");
            e.printStackTrace();
        }
    }

    public void viewRatedSongs(int userId) {
        try {
            List<String> ratedSongs = ratingDao.viewRatedSongs(userId);

            if (ratedSongs.isEmpty()) {
                System.out.println("You haven't rated any songs.");
            } else {
                System.out.println("Your Rated Songs:");
                for (String song : ratedSongs) {
                    System.out.println(song);
                }
            }
        } catch (SQLException e) {
            System.out.println("Could not fetch rated songs.");
        }
    }

    public void getTopRatedSong() {
        try {
            String result = ratingDao.getTopRatedSong();
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching the top-rated song.");
            e.printStackTrace();
        }
    }

    public void getMostPopularSong() {
        try {
            String result = ratingDao.getTheMostPopularSong();
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching the most popular song.");
            e.printStackTrace();
        }
    }
}
