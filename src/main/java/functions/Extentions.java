package functions;

import java.util.Scanner;

public class Extentions {

    static String answer = "";
    static Scanner sc = new Scanner(System.in);

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
        while(true) {
        System.out.println("Please select an option:\n" +
                "1. Sign in\n" +
                "2. Register\n" +
                "3. Exit\n");
        answer = sc.next();
        switch (answer){
            case "1":
                // log in logic
                break;
            case "2":
                // sign up logic
                break;
            case "3":
                // log out logic
                break;
            default:
                System.out.println("Not a valid option!\n" +
                        "Please select a valid option again.\n");
                break;
        }
    }}
}
