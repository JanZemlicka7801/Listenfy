package functions;

import business.Artist;
import persistence.ArtistDao;
import persistence.ArtistDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class daoExtentions {
    public static void viewAllArtists(ArtistDao artistDao) {
        try {
            List<Artist> artists = artistDao.getAllArtists();
            System.out.println("Artists in the Library:");
            for (Artist artist : artists) {
                System.out.println("- " + artist.getArtistFirstName() + " " + artist.getArtistLastName());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching artists: " + e.getMessage());
        }
    }
}
