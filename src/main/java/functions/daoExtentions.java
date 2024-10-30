package functions;

import business.Albums;
import business.Artist;
import business.Song;
import persistence.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class daoExtentions {
    public static void viewAllArtists() {
        ArtistDao artistDao = new ArtistDaoImpl("database.properties");
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
    public static void viewAllSongInAlbum(){
        Scanner scanner = new Scanner(System.in);
        AlbumDaoImpl albumDao = new AlbumDaoImpl("database.properties");
        SongDao songDao = new SongDaoImpl("database.properties");

        String albumTitle;

        while (true) {
            System.out.print("Enter album title (alphabetic characters only): ");
            albumTitle = scanner.nextLine().trim();

            if (albumTitle.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter alphabetic characters only.");
            }
        }
        int albumId = albumDao.getAlbumIdByAlbumTitle(albumTitle);

        if (albumId != -1) {
            System.out.println("Album ID for \"" + albumTitle + "\": " + albumId);

            List<Song> songs = songDao.getAllSongsByAlbumId(albumId);

            if (!songs.isEmpty()) {
                System.out.println("\nSongs in album \"" + albumTitle + "\":");
                for (Song song : songs) {
                    System.out.println("Title: " + song.getSongTitle() +
                            ", Duration: " + song.getDuration());
                }
            } else {
                System.out.println("No songs found in the album \"" + albumTitle + "\".");
            }
        } else {
            System.out.println("Album titled \"" + albumTitle + "\" not found.");
        }

        scanner.close();
    }
}