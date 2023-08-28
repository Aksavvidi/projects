package testbed.review;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ProjectCh8App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        do {
            printMenu();
            choice = getChoice();
            try {
                printChoice(choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 5);
    }

    public static void printMenu() {
        System.out.println("Επιλέξτε ένα από τα παρακάτω: ");
        System.out.println("1.");
        System.out.println("2.");
        System.out.println("3.");
        System.out.println("4.");
        System.out.println("5. Exit");
    }

    public static int getChoice() throws RuntimeException {
        String errorToken = "";

        if (in.hasNextInt()) {
            return in.nextInt();
        } else {
            System.err.println("Error int read");
            errorToken = in.nextLine();
            log(new RuntimeException(LocalDateTime.now() + "\n" + errorToken));
            return -1;
        }
    }

    public static void printChoice(int choice) throws IllegalArgumentException {
        try {
            if ((choice < 1) || (choice > 5)) {
                throw new IllegalArgumentException("Invalid Choice: " + choice);
            }
            System.out.println("Choice: " + choice);
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            log(e);
            throw e;
        }
    }

    public static void log(Exception e) {
        Path path = Paths.get("C:/tmp/log.txt");

        try (PrintStream ps = new PrintStream(new FileOutputStream(path.toFile(), true))) {
            ps.println(LocalDateTime.now() + "\n" + e);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
