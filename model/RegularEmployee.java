package model;

import util.Validation;

import java.sql.Date;
import java.util.Scanner;

/**
 * Represents a {@link RegularEmployee}, a subclass of {@link User}.
 * <p>The {@code RegularEmployee} class provides functionalities for managing the profile of regular employees,
 * such as displaying, updating, and modifying employee details like password, phone number, and email.</p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Handles commands for displaying and updating employee profiles.</li>
 *     <li>Supports password, phone number, and email updates with validation checks.</li>
 *     <li>Ensures that employees do not use the default password ("password").</li>
 *     <li>Supports basic profile details and detailed profile views.</li>
 *     <li>Provides an option to logout from the menu.</li>
 * </ul>
 */
public abstract class RegularEmployee extends User{

    Scanner scanner = new Scanner(System.in);
    Validation validation = new Validation();

    /**
     * Constructs a {@code RegularEmployee} instance with the provided details.
     *
     * @param userId      The unique identifier for the regular employee.
     * @param username    The username of the regular employee.
     * @param password    The password of the regular employee.
     * @param role        The role of the user, expected to be {@code "REGULAR_EMPLOYEE"}.
     * @param firstName   The first name of the regular employee.
     * @param lastName    The last name of the regular employee.
     * @param phoneNo     The phone number of the regular employee.
     * @param email       The email address of the regular employee.
     * @param dateOfBirth The date of birth of the regular employee.
     * @param dateOfStart The date when the regular employee started employment.
     */
    public RegularEmployee(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    /**
     * Displays the employee's menu and handles commands entered by the regular employee.
     * <p>This method allows the regular employee to execute various commands, such as displaying profile, updating profile, or logging out.</p>
     *
     * @return {@code true} if the employee remains logged in, {@code false} if the employee logs out.
     */
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

    /**
     * Returns a detailed profile of the regular employee.
     * <p>This method returns a string representation of the employee's details, including personal and professional information.</p>
     *
     * @return A string containing the detailed profile of the regular employee.
     */
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

    /**
     * Displays the update menu and handles commands for updating profile details such as password, phone number, and email.
     * <p>This method provides validation for the updated values and ensures they meet the necessary criteria before updating.</p>
     *
     * @return {@code true} if the menu continues after an update, {@code false} if the employee chooses to exit the update menu.
     */
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
                while (input.equals("password")) {
                    System.out.println("Password cannot be 'password' try again:");
                    input = scanner.nextLine().trim();
                }
                int passwordStrength = validation.getPasswordStrength(input);
                switch (passwordStrength) {
                    case 0:
                        System.out.println("Password is very weak. Are you sure you want to use this password?");
                        System.out.println("[1] Yes");
                        System.out.println("[2] No");
                        String choice = scanner.nextLine().trim();
                        if(choice.equals("1")){
                            break;
                        }
                        if (choice.equals("2")) {
                            System.out.println("Password not updated.");
                            System.out.println("\nPress enter to continue...");
                            scanner.nextLine();
                            clearScreen();
                            return false;
                        }
                        else {
                            System.out.println("Invalid command. Password not updated. Returning to the menu.");
                            return false;
                        }
                    case 1:
                        System.out.println("Password is weak. Are you sure you want to use this password?");
                        System.out.println("[1] Yes");
                        System.out.println("[2] No");
                        choice = scanner.nextLine().trim();
                        if(choice.equals("1")){
                            break;
                        }
                        if (choice.equals("2")) {
                            System.out.println("Password not updated.");
                            System.out.println("\nPress enter to continue...");
                            scanner.nextLine();
                            clearScreen();
                            return false;
                        }
                        else {
                            System.out.println("Invalid command. Password not updated. Returning to the menu.");
                            return false;
                        }
                    case 2:
                        System.out.println("Password strength is medium.\n");
                        break;
                    case 3:
                        System.out.println("Password is strong.\n");
                        break;
                    case 4:
                        System.out.println("Password is very strong.\n");
                        break;
                }
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
