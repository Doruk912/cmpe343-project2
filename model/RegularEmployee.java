package model;

import java.sql.Date;
import java.util.Scanner;

public abstract class RegularEmployee extends User{

    public RegularEmployee(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[" + this.getRole() + " MENU]");
        System.out.println("Logged in as " + this.getUsername() + "\n");

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
        System.out.println("1. display: Displays your profile");
        System.out.println("2. update <field>: Updates information");
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
                clearScreen();
                break;
            case "update username":
                System.out.println("Enter new username:");
                input = scanner.nextLine().trim().toLowerCase();
                this.setUsername(input);
                System.out.println("Username updated.");
                break;
            case "update password":
                System.out.println("Enter new password:");
                input = scanner.nextLine().trim();
                this.setPassword(input);
                System.out.println("Password updated.");
                break;
            case "update first name":
                System.out.println("Enter new first name:");
                input = scanner.nextLine().trim();
                this.setFirstName(input);
                System.out.println("First name updated.");
                break;
            case "update last name":
                System.out.println("Enter new last name:");
                input = scanner.nextLine().trim();
                this.setLastName(input);
                System.out.println("Last name updated.");
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
