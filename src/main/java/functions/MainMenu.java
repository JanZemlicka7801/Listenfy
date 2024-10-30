package functions;

import business.User;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final User currentUser;

    public MainMenu(User user, Scanner scanner) {
        this.currentUser = user;
        this.scanner = scanner;
    }

    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== LISTENFY MAIN MENU ===");
            System.out.println("1. Artists");
            System.out.println("2. Albums");
            System.out.println("3. Songs");
            System.out.println("4. Playlists");
            System.out.println("5. Account");
            System.out.println("6. Logout");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleArtistMenu();
                    break;
                case "2":
                case "3":
                case "4":
                case "5":
                    System.out.println("Feature under development...");
                    break;
                case "6":
                    running = false;
                    System.out.println("Logging out...");
                    System.out.println("Press Enter to return to login menu...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void handleArtistMenu() {
        while (true) {
            System.out.println("\n=== ARTIST MENU ===");
            System.out.println("1. View all artists");
            System.out.println("2. Search artists");
            System.out.println("3. View artist albums");
            System.out.println("4. Return to main menu");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Viewing all artists...");
                    break;
                case "2":
                    System.out.print("Enter artist name to search: ");
                    String searchTerm = scanner.nextLine().trim();
                    break;
                case "3":
                    System.out.print("Enter artist ID: ");
                    String artistId = scanner.nextLine().trim();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
