package functions;

import business.User;
import persistence.*;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final User currentUser;

    public MainMenu(User user, Scanner scanner) {
        this.currentUser = user;
        this.scanner = scanner;
        scanner.nextLine();

        ArtistDao artistDao = new ArtistDaoImpl("database.properties");
        AlbumsDao albumsDao = new AlbumDaoImpl("database.properties");
        SongDao songDao = new SongDaoImpl("database.properties");
        PlaylistDao playlistDao = new PlaylistDaoImpl("database.properties");

    }

    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== LISTENFY MAIN MENU ===");
            System.out.println("1. View all artists");
            System.out.println("2. View artist albums");
            System.out.println("3. View album songs");
            System.out.println("4. Search for songs");
            System.out.println("5. Playlist Management");
            System.out.println("6. Song Ratings");
            System.out.println("7. Logout");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            if (choice.isEmpty()) {
                continue;
            }

            switch (choice) {
                case "1":
                    DaoExtentions.viewAllArtists();
                    break;
                case "2":
                    DaoExtentions.getAlbumsFromArtist();
                    break;
                case "3":
                    DaoExtentions.viewAllSongsInAlbum();
                    break;
                case "4":
                    //TODO Later
                    break;
                case "5":
                    handlePlaylistMenu();
                    break;
                case "6":
                    handleRatingsMenu();
                    break;
                case "7":
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void handlePlaylistMenu() {
        while (true) {
            System.out.println("\n=== PLAYLIST MANAGEMENT ===");
            System.out.println("1. Create new playlist");
            System.out.println("2. View all playlists");
            System.out.println("3. View playlist contents");
            System.out.println("4. Edit playlist");
            System.out.println("5. Return to main menu");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":
                    System.out.print("Enter playlist ID: ");

                    break;
                case "4":
                    handleEditPlaylistMenu();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void handleEditPlaylistMenu() {
        System.out.println("\n=== EDIT PLAYLIST ===");
        System.out.println("1. Add song");
        System.out.println("2. Remove song");
        System.out.println("3. Rename playlist");
        System.out.print("\nEnter your choice: ");

        String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid option.");
            }
    }

    private void handleRatingsMenu() {
        while (true) {
            System.out.println("\n=== SONG RATINGS ===");
            System.out.println("1. Rate a song");
            System.out.println("2. View your rated songs");
            System.out.println("3. View top-rated song");
            System.out.println("4. View most popular song");
            System.out.println("5. Return to main menu");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void searchForSong(){
        while (true){
            System.out.println("\n=== SEARCH MENU ===");
            System.out.println("1. Rate a song");
            System.out.println("2. View your rated songs");
            System.out.println("3. View top-rated song");
            System.out.println("4. View most popular song");
            System.out.println("5. Return to main menu");
            System.out.print("\nEnter your choice: ");
        }
    }
}
