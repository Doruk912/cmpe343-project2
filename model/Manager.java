package model;

import util.Algorithm;
import util.Repository;
import util.Validation;
import java.sql.Date;
import java.util.Scanner;

public class Manager extends User {

    Scanner scanner = new Scanner(System.in);
    Validation validation = new Validation();
    Repository repository = new Repository();

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
            System.out.println("[3] Update Profile");
            System.out.println("[4] List All Employees");
            System.out.println("[5] Display Details of an Employee");
            System.out.println("[6] Update Employee");
            System.out.println("[7] Hire Employee");
            System.out.println("[8] Fire Employee");
            System.out.println("[9] Compare Sorting Algorithms");
            System.out.println("[10] Logout");
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
                    while (updateMenu());
                    break;
                case "4":
                    displayAllEmployees();
                    break;
                case "5":
                    displayEmployee();
                    break;
                case "6":
                    updateEmployee();
                    break;
                case "7":
                    hireEmployee();
                    break;
                case "8":
                    fireEmployee();
                    break;
                case "9":
                    new Algorithm();
                    System.out.println("\nPress enter to continue...");
                    scanner.nextLine();
                    clearScreen();
                    break;
                case "10":
                    System.out.println("Logged out.");
                    return false;
                default:
                    clearScreen();
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

    private boolean updateMenu() {
        System.out.println("Update Profile");
        System.out.println("Choose what to update:");
        System.out.println("[1] Username");
        System.out.println("[2] Password");
        System.out.println("[3] Phone Number");
        System.out.println("[4] Email");
        System.out.println("[5] First Name");
        System.out.println("[6] Last Name");
        System.out.println("[7] Exit");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                System.out.println("Enter new username:");
                String newUsername = scanner.nextLine().trim();
                while (!validation.isValidUsername(newUsername)) {
                    System.out.println("Invalid username. Please try again. No numbers, symbols, capital or turkish letters allowed. Min 5, max 20 characters.");
                    newUsername = scanner.nextLine().trim();
                }
                this.setUsername(newUsername);
                System.out.println("Username updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "2":
                if (!updatePassword()) {
                    System.out.println("Password update cancelled.");
                }
                break;
            case "3":
                System.out.println("Enter the new phone number (e.g., +1 1234567890):");
                String newPhoneNo = scanner.nextLine().trim();
                while (!validation.isValidPhoneNumber(newPhoneNo)) {
                    System.out.println("Invalid phone number. Please use the format '+<country code> <10-digit number>'.");
                    System.out.println("Example: +1 1234567890");
                    newPhoneNo = scanner.nextLine().trim();
                }
                this.setPhoneNo(newPhoneNo);
                System.out.println("Phone number updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "4":
                System.out.println("Enter new email:");
                String newEmail = scanner.nextLine().trim();
                while (!validation.isValidEmail(newEmail)) {
                    System.out.println("Invalid email. Please try again.");
                    newEmail = scanner.nextLine().trim();
                }
                this.setEmail(newEmail);
                System.out.println("Email updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "5":
                System.out.println("Enter new first name:");
                String newFirstName = scanner.nextLine().trim();
                while (!validation.isValidName(newFirstName)) {
                    System.out.println("Invalid name. Please try again.");
                    newFirstName = scanner.nextLine().trim();
                }
                this.setFirstName(newFirstName);
                System.out.println("First name updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "6":
                System.out.println("Enter new last name:");
                String newLastName = scanner.nextLine().trim();
                while (!validation.isValidName(newLastName)) {
                    System.out.println("Invalid name. Please try again.");
                    newLastName = scanner.nextLine().trim();
                }
                this.setLastName(newLastName);
                System.out.println("Last name updated.");
                System.out.println("\nPress enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "7":
                System.out.println("Exiting update menu.");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;

        }
        return true;
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
        System.out.println("Hire Employee");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim().toLowerCase();
        while (!validation.isValidUsername(username)) {
            System.out.println("Invalid username. Please try again. No numbers, symbols, capital or turkish letters allowed. Min 5, max 20 characters.");
            username = scanner.nextLine().trim().toLowerCase();
        }

        if (repository.getUserByUsername(username) != null) {
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
        role = switch (role) {
            case "1" -> "MANAGER";
            case "2" -> "ENGINEER";
            case "3" -> "TECHNICIAN";
            case "4" -> "INTERN";
            default -> {
                System.out.println("Invalid role. Defaulting to INTERN.");
                yield "INTERN";
            }
        };

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        while (!validation.isValidName(firstName)) {
            System.out.println("Invalid name. Please try again.");
            firstName = scanner.nextLine().trim();
        }

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();
        while (!validation.isValidName(lastName)) {
            System.out.println("Invalid name. Please try again.");
            lastName = scanner.nextLine().trim();
        }

        System.out.println("Enter phone number (e.g., +1 1234567890):");
        String phoneNo = scanner.nextLine().trim();
        while (!validation.isValidPhoneNumber(phoneNo)) {
            System.out.println("Invalid phone number. Please use the format '+<country code> <10-digit number>'.");
            System.out.println("Example: +1 1234567890");
            phoneNo = scanner.nextLine().trim();
        }

        System.out.println("Enter email:");
        String email = scanner.nextLine().trim();
        while (!validation.isValidEmail(email)) {
            System.out.println("Invalid email. Please try again.");
            email = scanner.nextLine().trim();
        }

        Date dateOfBirth = validation.getDateFromUser("Enter date of birth");
        Date dateOfStart = validation.getDateFromUser("Enter date of start");

        if(dateOfBirth.after(dateOfStart)){
            System.out.println("Date of birth cannot be after date of start. Employee not hired.");
            System.out.println("\nPress enter to continue...");
            scanner.nextLine();
            clearScreen();
            return;
        }

        repository.addUser(username, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
        System.out.println("Employee hired successfully.");
        System.out.println("\nPress enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    private void updateEmployee(){
        System.out.println("Update Employee");
        System.out.println("Enter the username or the user ID of the employee you want to update: ");
        String input = scanner.nextLine().trim();
        User userToUpdate = null;

        if (input.matches("\\d+")) {
            int userId = Integer.parseInt(input);
            userToUpdate = repository.getUserById(userId);
        } else if(input.matches("[a-z]{3,20}")){
            userToUpdate = repository.getUserByUsername(input);
        }else {
            System.out.println("Invalid input.");
            return;
        }

        if (userToUpdate == null) {
            System.out.println("Employee not found.");
            System.out.println("\nPress enter to continue...");
            scanner.nextLine();
            clearScreen();
            return;
        }

        System.out.println("Current details:");
        System.out.println(userToUpdate.detailedProfile());

        while (true) {
            System.out.println("\nWhat would you like to update?");
            System.out.println("[1] Username");
            System.out.println("[2] Role");
            System.out.println("[3] First Name");
            System.out.println("[4] Last Name");
            System.out.println("[5] Phone Number");
            System.out.println("[6] Email");
            System.out.println("[7] Date of Birth");
            System.out.println("[8] Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Enter new username:");
                    String newUsername = scanner.nextLine().trim();
                    while (!validation.isValidUsername(newUsername)) {
                        System.out.println("Invalid username. Please try again.");
                        newUsername = scanner.nextLine().trim();
                    }
                    userToUpdate.setUsername(newUsername);
                    System.out.println("Username updated.");
                    break;

                case "2":
                    System.out.println("Choose new role:");
                    System.out.println("[1] MANAGER");
                    System.out.println("[2] ENGINEER");
                    System.out.println("[3] TECHNICIAN");
                    System.out.println("[4] INTERN");
                    String role = scanner.nextLine().trim();
                    role = switch (role) {
                        case "1" -> "MANAGER";
                        case "2" -> "ENGINEER";
                        case "3" -> "TECHNICIAN";
                        case "4" -> "INTERN";
                        default -> {
                            System.out.println("Invalid role. Defaulting to INTERN.");
                            yield "INTERN";
                        }
                    };
                    userToUpdate.setRole(role);
                    System.out.println("Role updated.");
                    break;

                case "3":
                    System.out.println("Enter new first name:");
                    String newFirstName = scanner.nextLine().trim();
                    while (!validation.isValidName(newFirstName)) {
                        System.out.println("Invalid name. Please try again.");
                        newFirstName = scanner.nextLine().trim();
                    }
                    userToUpdate.setFirstName(newFirstName);
                    System.out.println("First name updated.");
                    break;

                case "4":
                    System.out.println("Enter new last name:");
                    String newLastName = scanner.nextLine().trim();
                    while (!validation.isValidName(newLastName)) {
                        System.out.println("Invalid name. Please try again.");
                        newLastName = scanner.nextLine().trim();
                    }
                    userToUpdate.setLastName(newLastName);
                    System.out.println("Last name updated.");
                    break;

                case "5":
                    System.out.println("Enter new phone number (e.g., +1 1234567890):");
                    String newPhone = scanner.nextLine().trim();
                    while (!validation.isValidPhoneNumber(newPhone)) {
                        System.out.println("Invalid phone number. Please try again.");
                        newPhone = scanner.nextLine().trim();
                    }
                    userToUpdate.setPhoneNo(newPhone);
                    System.out.println("Phone number updated.");
                    break;

                case "6":
                    System.out.println("Enter new email:");
                    String newEmail = scanner.nextLine().trim();
                    while (!validation.isValidEmail(newEmail)) {
                        System.out.println("Invalid email. Please try again.");
                        newEmail = scanner.nextLine().trim();
                    }
                    userToUpdate.setEmail(newEmail);
                    System.out.println("Email updated.");
                    break;

                case "7":
                    System.out.println("Enter new date of birth (yyyy-mm-dd):");
                    Date newDateOfBirth = validation.getDateFromUser("Enter date of birth");
                    userToUpdate.setDateOfBirth(newDateOfBirth);
                    System.out.println("Date of birth updated.");
                    break;

                case "8":
                    System.out.println("Exiting update menu.");
                    System.out.println("\nPress enter to continue...");
                    scanner.nextLine();
                    clearScreen();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Details updated successfully.");
        }
    }

    private void fireEmployee() {
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
                User firedUser = repository.getUserById(Integer.parseInt(input));
                if (firedUser == null) {
                    System.out.println("User not found.");
                    break;
                }
                repository.removeUser(userId);
                System.out.println(firedUser.getFirstName() + " " + firedUser.getLastName() + " has been fired.");
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

    public void displayEmployee() {
        System.out.println("Enter username or used ID of the employee you want to display: ");
        String input = scanner.nextLine().trim();
        if(input.matches("\\d+")){
            int userId = Integer.parseInt(input);
            User user = repository.getUserById(userId);
            if(user != null){
                System.out.println(user.detailedProfile());
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                clearScreen();
            }else {
                System.out.println("User not found.");
            }
        }else if(input.matches("[a-z]{3,20}")){
            User user = repository.getUserByUsername(input);
            if(user != null){
                System.out.println(user.detailedProfile());
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                clearScreen();
            }else {
                System.out.println("User not found.");
            }
        }else {
            System.out.println("Invalid input.");
        }
    }
}

