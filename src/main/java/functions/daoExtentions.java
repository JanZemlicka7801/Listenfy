package functions;

import business.Albums;
import business.Artist;
import persistence.AlbumDaoImpl;
import persistence.AlbumsDao;
import persistence.ArtistDao;
import persistence.ArtistDaoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
    public static void getAlbumsFromArtist(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter artist's first name (leave blank if single name or just has band name): ");
        String firstName = sc.nextLine().trim();
        if (firstName.isEmpty()) {
            firstName = null;
        }
        System.out.print("Enter artist's last name: ");
        String lastName = sc.nextLine().trim();

        AlbumsDao albumsDao = new AlbumDaoImpl("database.properties");

        List<Albums> albums = albumsDao.getAlbumsByArtistName(firstName, lastName);

        for (Albums album : albums) {
            System.out.println("Title: " + album.getAlbum_title() +
                    ", Release Year: " + album.getRelease_year());
        }
    }
}