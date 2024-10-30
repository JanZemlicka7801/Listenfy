package functions;

import business.User;
import persistence.UserDao;
import persistence.UserDaoImpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Extentions {

    static String answer = "";
    static Scanner sc = new Scanner(System.in);

    static UserDao userDao = new UserDaoImpl("database.properties");

    public static void welcome() {
        System.out.println("Welcome to a brand new and best music storing platform in the world Listenfy! \n" +
                "This platform allows you to view all playlists, artists, songs and their albums. Also you \n" +
                "will be able to create your own playlists and rate songs that are on the platform. Please \n" +
                "before you will be able to access our platform log in or sign in using your credit or \n" +
                "debit card. Since this is a paid platform we need to validate those cards before showing\n" +
                "platform's content. Thank you for using Listenfy!\n");

        System.out.println("L       IIIII  SSSSS   TTTTT  EEEEE  N   N  FFFFF  Y   Y");
        System.out.println("L         I    S         T    E      NN  N  F       Y Y ");
        System.out.println("L         I     SSS      T    EEEE   N N N  FFF      Y ");
        System.out.println("L         I        S     T    E      N  NN  F        Y");
        System.out.println("LLLLL   IIIII  SSSSS     T    EEEEE  N   N  F        Y");
        System.out.println(" ");
    }

    public static void menu() {
        while (true) {
            System.out.println("Please select an option:\n" +
                    "1. Sign in\n" +
                    "2. Register\n" +
                    "3. Exit\n");

            answer = sc.nextLine().trim();

            switch (answer) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Not a valid option! Please select a valid option again.");
                    break;
            }
        }
    }

    public static void login() {
        System.out.print("Enter username: ");
        String username = sc.next();

        System.out.print("Enter password: ");
        String password = sc.next();

        try {
            User user = userDao.login(username, password);
            if (user != null) {
                System.out.println("Login successful! Welcome, " + user.getUsername());
                MainMenu mainMenu = new MainMenu(user, sc);
                mainMenu.displayMenu();
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred during login.");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static void register() {
        System.out.print("Enter a username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter a password: ");
        String password = sc.nextLine().trim();

        System.out.print("Enter an email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter your credit card number: ");
        String creditCard = sc.nextLine().trim();


        if (!cardService.cardRegister(creditCard)) {
            System.out.println("Invalid credit card. Registration failed.");
            return;
        }

        User newUser = new User(username, password, "", email, LocalDate.now());

        try {
            boolean success = userDao.register(newUser);
            if (success) {
                System.out.println("Registration successful! You can now log in.");
            } else {
                System.out.println("Registration failed.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred during registration.");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}



