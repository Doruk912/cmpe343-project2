package model;

import util.Validation;

import java.sql.Date;
import java.util.Scanner;

public abstract class RegularEmployee extends User{

    Scanner scanner = new Scanner(System.in);
    Validation validation = new Validation();

    public RegularEmployee(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[EMPLOYEE MENU]");
        System.out.println("Logged in as " + this.getUsername() + "\n");
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
        System.out.println("[1] Display Profile");
        System.out.println("[2] Display Detailed Profile");
        System.out.println("[3] Update Profile");
        System.out.println("[4] Logout");
        System.out.println();

        System.out.println("Enter command: ");
        input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "1":
            case "display":
                System.out.println("\n" + this);
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "2":
            case "detailed":
                System.out.println("\n" + this.detailedProfile());
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "3":
            case "update":
                boolean flag = true;
                while(flag){
                    flag = updateMenu();
                }
                break;
            case "4":
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

    private String detailedProfile() {
        return "User ID: " + this.getUserId() + "\n" +
               "Username: " + this.getUsername() + "\n" +
               "Role: " + this.getRole() + "\n" +
               "First Name: " + this.getFirstName() + "\n" +
               "Last Name: " + this.getLastName() + "\n" +
               "Phone Number: " + this.getPhoneNo() + "\n" +
               "Email: " + this.getEmail() + "\n" +
               "Date of Birth: " + this.getDateOfBirth() + "\n" +
               "Date of Start: " + this.getDateOfStart() + "\n";
    }

    private boolean updateMenu() {
        System.out.println("Update Profile");
        System.out.println("Choose what to update:");
        System.out.println("[1] Password");
        System.out.println("[2] Phone Number");
        System.out.println("[3] Email");
        System.out.println("[4] Exit");
        System.out.println();

        System.out.println("Enter command: ");
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "1":
                System.out.println("Enter new password:");
                input = scanner.nextLine().trim();
                this.setPassword(input);
                System.out.println("Password updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "2":
                System.out.println("Enter the new phone number (e.g., +1 1234567890):");
                input = scanner.nextLine().trim();
                while (!validation.isValidPhoneNumber(input)) {
                    System.out.println("Invalid phone number. Please use the format '+<country code> <10-digit number>'.");
                    System.out.println("Example: +1 1234567890");
                    input = scanner.nextLine().trim();
                }
                this.setPhoneNo(input);
                System.out.println("Phone number updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "3":
                System.out.println("Enter new email:");
                input = scanner.nextLine().trim();
                while (!validation.isValidEmail(input)) {
                    System.out.println("Invalid email. Please try again.");
                    input = scanner.nextLine().trim();
                }
                this.setEmail(input);
                System.out.println("Email updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "4":
                clearScreen();
                return false;
            default:
                clearScreen();
                System.out.println("Invalid command.");
                break;
        }
        return false;
    }
}
