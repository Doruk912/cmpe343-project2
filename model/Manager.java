package model;

import util.Algorithm;
import util.Repository;
import util.Validation;
import java.sql.Date;
import java.util.Scanner;

/**
 * The Manager class represents a Manager user in the system. It extends from the User class and
 * provides methods to interact with the system for managing employee profiles, updating information,
 * hiring and firing employees, and more.
 * The manager has a role-based menu with different functionalities for managing the system.
 *
 * <p>Some of the key functionalities include:</p>
 * <ul>
 *   <li>Displaying and updating the manager's profile</li>
 *   <li>Managing employee records (list, update, hire, fire)</li>
 *   <li>Comparing sorting algorithms</li>
 * </ul>
 *
 * <p>The Manager class also ensures that the user's password is updated if it is still set to the default value.</p>
 *
 * @see User
 * @see Algorithm
 * @see Repository
 * @see Validation
 */
public class Manager extends User {

    Scanner scanner = new Scanner(System.in);
    Validation validation = new Validation();
    Repository repository = new Repository();

    /**
     * Constructs a new Manager object with the specified user details.
     *
     * @param userId the user's unique identifier
     * @param username the username of the user
     * @param password the user's password
     * @param role the role of the user (should be "MANAGER")
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param phoneNo the user's phone number
     * @param email the user's email address
     * @param dateOfBirth the user's date of birth
     * @param dateOfStart the user's date of start with the company
     */
    public Manager(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    /**
     * Displays the manager's menu with available options and handles user input for different actions.
     * <p>This method allows the manager to:</p>
     * <ul>
     *   <li>Update their password</li>
     *   <li>Display and update their profile</li>
     *   <li>Manage employee information (e.g., hire, fire, update, view)</li>
     *   <li>Compare sorting algorithms</li>
     *   <li>Logout</li>
     * </ul>
     *
     * @return {@code true} if the manager successfully logs out, {@code false} if the manager's session is terminated
     */
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

    /**
     * Displays the update profile menu and handles updating specific user information.
     *
     * @return {@code true} if the user continues to update their profile, {@code false} if they exit the update menu
     */
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

    /**
     * Displays a list of all employees in the system.
     * This method fetches all users from the repository and prints them to the console.
     */
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

    /**
     * Hires a new employee by collecting relevant information (e.g., username, role, phone number) and adding them to the system.
     * Validates the provided information, checks for existing users, and ensures that the start date is after the birth date.
     */
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

    /**
     * Updates the details of an existing employee. This method allows the manager to change the username, role, first name,
     * last name, phone number, email, and date of birth of an employee.
     */
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

    /**
     * Fires an employee by removing them from the system. The manager must provide the employee's ID to proceed.
     * If the employee cannot be found, an error message is shown.
     */
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

    /**
     * This method prompts the user to enter a new password, checks its strength, and updates the password
     * if it meets the required conditions. The password must not be 'password', and if the password is weak,
     * the user will be asked for confirmation before updating.
     *
     * @return boolean - Returns true if the password was updated successfully, false if the update was canceled.
     *
     * The method works as follows:
     * 1. Prompts the user to enter a new password.
     * 2. Validates the password to ensure it is not "password".
     * 3. If the password is weak (determined by a password strength validation method), asks the user for confirmation to proceed.
     * 4. If the user confirms, the password is updated and a success message is displayed.
     * 5. If the user cancels, the password is not updated, and a message is shown.
     * 6. After updating (or not updating) the password, the method clears the screen and returns to the menu.
     */
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

    /**
     * This method allows the user to search for an employee's profile using either their username or user ID.
     * It prompts the user to input either a username or an ID and displays the detailed profile of the corresponding employee.
     *
     * @return void
     *
     * The method works as follows:
     * 1. Prompts the user to enter a username or user ID.
     * 2. Checks if the input is a numeric ID or a valid username (between 3 and 20 lowercase letters).
     * 3. If the input is a valid ID, the method retrieves the user by ID from the repository and displays their profile.
     * 4. If the input is a valid username, the method retrieves the user by username from the repository and displays their profile.
     * 5. If the user is not found, an appropriate message is displayed.
     * 6. If the input is invalid (neither an ID nor a valid username), an error message is shown.
     * 7. After displaying the profile, the method waits for the user to press enter and clears the screen.
     */
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

