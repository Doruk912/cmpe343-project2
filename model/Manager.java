package model;

import util.Algorithm;
import util.Repository;

import java.sql.Date;
import java.util.Scanner;

public class Manager extends User {

    public Manager(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[" + this.getRole() + " MENU]");
        System.out.println("Logged in as " + this.getUsername());

        Scanner scanner = new Scanner(System.in);
        String input;

        if (this.getPassword().equals("password")) {
            System.out.println("You need to enter another password:");
            input = scanner.nextLine().trim();
            while (input.equals("password")) {
                System.out.println("Password cannot be 'password' try again:");
                input = scanner.nextLine().trim();
            }

            this.setPassword(input);
        }

        System.out.println("Some useful commands:");
        System.out.println("display: Displays your profile");
        System.out.println("employees: Lists all employees");
        System.out.println("algorithms: Compares sorting algorithms");
        System.out.println("logout: Returns to login screen");

        System.out.println("Enter command:");
        input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "display":
                System.out.println("\n" + this);
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            case "employees":
                Repository repo = new Repository();
                for (User u : repo.getAllUsers())
                    System.out.println(u + "\n");
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            case "algorithms":
                new Algorithm();
                break;
            case "logout":
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

