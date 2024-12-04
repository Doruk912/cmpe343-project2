package model;

import util.Algorithm;
import util.Repository;
import util.Validation;
import java.sql.Date;
import java.util.Scanner;

public class Manager extends User {

    Scanner scanner = new Scanner(System.in);
    Validation validation = new Validation();

    public Manager(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[MANAGER MENU]");
        System.out.println("Logged in as " + this.getUsername());

        while (this.getPassword().equals("password")) {
            System.out.println("You need to set a new password.");
            if (!updatePassword()) {
                System.out.println("Password change is mandatory. Returning to the login screen.");
                return false;
            }
        }

        while (true) {
            System.out.println("Some useful commands:");
            System.out.println("[1] Display Profile");
            System.out.println("[2] Display Detailed Profile");
            System.out.println("[3] List All Employees");
            System.out.println("[4] Hire Employee");
            System.out.println("[5] Fire Employee");
            System.out.println("[6] Compare Sorting Algorithms");
            System.out.println("[7] Logout");
            System.out.println();

            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n" + this);
                    System.out.println("Press enter to continue...");
                    scanner.nextLine();
                    clearScreen();
                    break;
                case "2":
                    System.out.println("\n" + detailedProfile());
                    System.out.println("Press enter to continue...");
                    scanner.nextLine();
                    clearScreen();
                    break;
                case "3":
                    displayAllEmployees();
                    break;
                case "4":
                    hireEmployee();
                    break;
                case "5":
                    fireEmployee();
                    break;
                case "6":
                    new Algorithm();
                    System.out.println("\nPress enter to continue...");
                    scanner.nextLine();
                    clearScreen();
                    break;
                case "7":
                    System.out.println("Logged out.");
                    return false;
                default:
                    clearScreen();
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

    private void displayAllEmployees() {
        Repository repo = new Repository();
        System.out.println("All Employees:");
        for (User u : repo.getAllUsers()) {
            System.out.println(u);
        }
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    private void hireEmployee() {
        Repository repo = new Repository();
        System.out.println("Hire Employee");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim().toLowerCase();

        if (repo.getUserByUsername(username) != null) {
            System.out.println("Username already exists. Employee not hired.");
            System.out.println("\nPress enter to continue...");
            scanner.nextLine();
            clearScreen();
            return;
        }

        System.out.println("Choose role:");
        System.out.println("[1] MANAGER");
        System.out.println("[2] ENGINEER");
        System.out.println("[3] TECHNICIAN");
        System.out.println("[4] INTERN");
        String role = scanner.nextLine().trim();
        switch (role) {
            case "1":
                role = "MANAGER";
                break;
            case "2":
                role = "ENGINEER";
                break;
            case "3":
                role = "TECHNICIAN";
                break;
            case "4":
                role = "INTERN";
                break;
            default:
                System.out.println("Invalid role. Defaulting to INTERN.");
                role = "INTERN";
        }

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        repo.addUser(username, role, firstName, lastName);
        System.out.println("Employee hired successfully.");
        System.out.println("\nPress enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    private void fireEmployee() {
        Repository repo = new Repository();
        System.out.println("Fire Employee");
        while (true) {
            System.out.print("Enter user ID to be fired: ");
            String input = scanner.nextLine().trim();
            if (input.isBlank()) {
                System.out.println("Empty input. Please try again.");
                continue;
            }
            try {
                int userId = Integer.parseInt(input);
                if (this.getUserId() == userId) {
                    System.out.println("You cannot fire yourself.");
                    break;
                }
                repo.removeUser(userId);
                System.out.println("User " + userId + " has been fired (if they existed).");
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid user ID. Please enter a numeric value.");
            }
        }
        System.out.println("\nPress enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    private boolean updatePassword() {
        System.out.println("Enter new password:");
        String input = scanner.nextLine().trim();

        while (input.equals("password")) {
            System.out.println("Password cannot be 'password'. Try again:");
            input = scanner.nextLine().trim();
        }

        int passwordStrength = validation.getPasswordStrength(input);

        if (passwordStrength <= 1) {
            System.out.println("Password is " + (passwordStrength == 0 ? "very weak" : "weak") + ". Are you sure you want to use this password?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");

            String choice = scanner.nextLine().trim();
            if (!choice.equals("1")) {
                System.out.println("Password not updated. Returning to the menu.");
                return false;
            }
        }

        this.setPassword(input);
        System.out.println("Password updated successfully.");
        System.out.println("\nPress enter to continue...");
        scanner.nextLine();
        clearScreen();
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
}

