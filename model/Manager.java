package model;

import util.Algorithm;

import java.sql.Date;
import java.util.Scanner;

public class Manager extends User {

    public Manager(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[" + this.getRole() + " MENU]");
        System.out.println("Logged in as " + this.getUsername() + "\n");

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Some useful commands:");
        System.out.println("1. display: Displays your profile");
        System.out.println("2. algorithms: Compares sorting algorithms");
        System.out.println("3. logout: Returns to login screen");
        System.out.println();

        System.out.println("Enter command: ");
        input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "display":
            case "1":
                System.out.println("\n" + this);
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            case "algorithms":
            case "2":
                new Algorithm();
                break;
            case "logout":
            case "3":
                System.out.println("Logged out.");
                return false;
            default:
                clearScreen();
                System.out.println("Invalid command.");
                break;
        }
        return true;
    }
}

