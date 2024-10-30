package functions;

import business.Song;
import business.User;
import persistence.RatingDao;
import persistence.SongDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RatingService extends MainMenu{
    private static final Scanner scanner = new Scanner(System.in);

    public RatingService(User user, Scanner scanner) {
        super(user, scanner);
    }

    public static void rateSong(int userId, RatingDao ratingDao, SongDao songDao) {
        System.out.print("Enter song name: ");
        String songName = scanner.nextLine().trim();

        Song song;
        song = songDao.getSongByTitle(songName);
        if (song.getSongId() == -1) {
            System.out.println("Song not found. Please enter a valid song name.");
            return;
        }

        System.out.print("Enter rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine().trim());

        try {
            if (rating < 1 || rating > 5) {
                System.out.println("Rating must be between 1 and 5.");
                return;
            }
            if (ratingDao.rateSong(userId, song.getSongId(), rating)) {
                System.out.println("Rating submitted successfully.");
            } else {
                System.out.println("Failed to submit rating.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while rating the song.");
        }
    }

    public static void viewRatedSongs(int userId, RatingDao ratingDao) {
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

    public static void viewTopRatedSong(RatingDao ratingDao) {
        try {
            String topRatedSong = ratingDao.getTopRatedSong();
            System.out.println(topRatedSong != null ? topRatedSong : "No ratings found.");
        } catch (SQLException e) {
            System.out.println("Could not fetch the top-rated song.");
        }
    }

    public static void viewMostPopularSong(RatingDao ratingDao) {
        try {
            String mostPopularSong = ratingDao.getTheMostPopularSong();
            System.out.println(mostPopularSong != null ? mostPopularSong : "No popular song found.");
        } catch (SQLException e) {
            System.out.println("Could not fetch the most popular song.");
        }
    }
}