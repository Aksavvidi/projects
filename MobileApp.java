package testbed.review;

import java.util.Arrays;
import java.util.Scanner;

public class MobileApp {
    static Scanner in = new Scanner(System.in);
    static String[][] contacts = new String[100][3];
    static int pivot = -1;

    public static void main(String[] args) {
        boolean quit = false;
        String s = "";

        do {
            printGenericMenu();
            s = getChoice();
            if (s.matches("[qQ]")) quit = true;
            else {
                try {
                    handleChoice(s);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!quit);

        System.out.println("Bios");
    }

    public static boolean isValid(int choice) {
        return ((choice >= 1) && (choice <= 5));
    }

    public static void handleChoice(String s) {
        int choice = 0;
        String phoneNumber = "";

        try {
            choice = Integer.parseInt(s);
            if (!isValid(choice)) {
                throw new IllegalArgumentException("Error - Choice between 1-5");
            }

            switch (choice) {
                case 1:
                    printContactMenu();
                    insertContact(getFirstname(), getLastname(), getPhoneNumber());
                    break;
                case 2:
                    phoneNumber = getPhoneNumber();
                    deleteContact(phoneNumber);
                    break;
                case 3:
                    phoneNumber = getPhoneNumber();
                    printContactMenu();
                    updateContact(phoneNumber, getFirstname(), getLastname(), getPhoneNumber());
                    break;
                case 4:
                    phoneNumber = getPhoneNumber();
                    try {
                        String[] contact = getContactByPhoneNumber(phoneNumber);
                        printContact(contact);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        throw e;
                    }

                    break;
                case 5:
                    printContacts();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Choice");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void printContact(String[] contact) {
        for (String item : contact) {
            System.out.print(contact[0] + " ");
        }

        System.out.println();
    }

    public static void printContacts() {
        for (int i = 0; i <= pivot; i ++) {
            System.out.printf("%s\t%s\t%s\n", contacts[i][0],contacts[i][1], contacts[i][2]);
        }
    }

    public static void printGenericMenu() {
        System.out.println("Επιλέξτε ένα από τα παρακάτω: ");
        System.out.println("1. Εισαγωγή Επαφής");
        System.out.println("2. Διαγραφή Επαφής");
        System.out.println("3. Ενημέρωση Επαφής");
        System.out.println("4. Αναζήτηση Επαφής");
        System.out.println("5. Εκτυπώστε τις επαφές");
        System.out.println("Q. Έξοδος");
    }

    public static void printContactMenu() {
        System.out.println("Εισάγετε Όνομα, Επώνυμο και Τηλέφωνο");
    }

    public static String getChoice() {
        System.out.println("Εισάγετε επιλογή");
        return in.nextLine().trim();
    }

    public static String getFirstname() {
        System.out.println("Εισάγετε Όνομα");
        return in.nextLine().trim();
    }

    public static String getLastname() {
        System.out.println("Εισάγετε Επώνυμο");
        return in.nextLine().trim();
    }

    public static String getPhoneNumber() {
        System.out.println("Εισάγετε Αριθμό Τηλεφώνου");
        return in.nextLine().trim();
    }


    // CRUD API

    public static int getContactIndexByPhoneNumber(String phoneNumber) {
        for (int i = 0; i <= pivot; i++) {
            if (contacts[i][2].equals(phoneNumber)) {
                return i;
            }
        }

        return -1;
    }

    public static void insertContact(String firstname, String lastname, String phoneNumber) {
        if (pivot == contacts.length) throw new IllegalArgumentException("Contacts list full");
        if (getContactIndexByPhoneNumber(phoneNumber) == -1) {
            pivot++;
            contacts[pivot][0] = firstname;
            contacts[pivot][1] = lastname;
            contacts[pivot][2] = phoneNumber;
        } else {
            throw new IllegalArgumentException("Contact already exists");
        }

    }

    public static void updateContact(String oldPhoneNumber, String firstname, String lastname, String newPhoneNumber) {
        int positionToUpdate = getContactIndexByPhoneNumber(oldPhoneNumber);

        try {
            if (positionToUpdate != -1) {
                contacts[positionToUpdate][0] = firstname;
                contacts[positionToUpdate][1] = lastname;
                contacts[positionToUpdate][2] = newPhoneNumber;
            } else {
                throw new IllegalArgumentException("Contact to update not exists");
            }
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }

    }

    public static void deleteContact(String phoneNumber) {
        int positionToDelete = getContactIndexByPhoneNumber(phoneNumber);

        try {
            if (positionToDelete != -1) {
                System.arraycopy(contacts, positionToDelete + 1, contacts, positionToDelete, pivot - (positionToDelete - 1));
                pivot--;
            } else {
                throw new IllegalArgumentException("Contact to delete not found");
            }
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }

    }

    public static String[] getContactByPhoneNumber(String phoneNumber) {
        int positionToReturn = getContactIndexByPhoneNumber(phoneNumber);

        try {
            if (positionToReturn == -1) {
                throw new IllegalArgumentException("Contact not found");
            } else {
                return contacts[getContactIndexByPhoneNumber(phoneNumber)];
            }
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            throw e;
        }

    }


}
