package model;

import java.io.IOException;
import java.sql.*;

/**
 * Represents a user in the system, providing functionality to manage user details
 * and interact with the underlying database.
 * <p>The {@code User} class serves as an abstract base class for various types of users
 * in the system. It contains common properties such as {@code userId}, {@code username},
 * {@code password}, and other personal information, as well as methods for accessing
 * and updating these properties.</p>
 * <p>This class also establishes a connection to the database and includes methods
 * for updating user details in the database.</p>
 */
public abstract class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private Date dateOfBirth;
    private Date dateOfStart;

    private String url = "jdbc:mysql://localhost:3306/cmpe343";
    private String db_username = "root";
    private String db_password = "admin";
    private Connection connection;

    /**
     * Constructs a {@code User} instance with the specified user details.
     *
     * @param userId      The unique identifier for the user.
     * @param username    The username of the user.
     * @param password    The password of the user.
     * @param role        The role of the user (e.g., "ADMIN", "TECHNICIAN").
     * @param firstName   The first name of the user.
     * @param lastName    The last name of the user.
     * @param phoneNo     The phone number of the user.
     * @param email       The email address of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param dateOfStart The date when the user started employment.
     */
    public User(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;

        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the menu for the user and handles the user's input.
     * This method must be implemented by subclasses.
     *
     * @return {@code true} if the menu was successfully displayed and the operation completed,
     *         {@code false} otherwise.
     */
    public abstract boolean menu();

    /**
     * Returns the user ID of the user.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the user.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user and updates it in the database.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ? WHERE user_id = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user and updates it in the database.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE user_id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the role of the user.
     *
     * @return The role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user and updates it in the database.
     *
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET role = ? WHERE user_id = ?");
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the first name of the user.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user and updates it in the database.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET first_name = ? WHERE user_id = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the last name of the user.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user and updates it in the database.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET last_name = ? WHERE user_id = ?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the phone number of the user.
     *
     * @return The phone number.
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the phone number of the user and updates it in the database.
     *
     * @param phoneNo The phone number to set.
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET phone_no = ? WHERE user_id = ?");
            preparedStatement.setString(1, phoneNo);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user and updates it in the database.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET email = ? WHERE user_id = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the date of birth of the user.
     *
     * @return The date of birth.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the user and updates it in the database.
     *
     * @param dateOfBirth The date of birth to set.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET date_of_birth = ? WHERE user_id = ?");
            preparedStatement.setDate(1, dateOfBirth);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the date the user started employment.
     *
     * @return The date of start.
     */
    public Date getDateOfStart() {
        return dateOfStart;
    }

    /**
     * Sets the date of start of the user and updates it in the database.
     *
     * @param dateOfStart The date of start to set.
     */
    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET date_of_start = ? WHERE user_id = ?");
            preparedStatement.setDate(1, dateOfStart);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the console screen based on the operating system.
     * <p>If the operating system is Windows, the method uses the 'cls' command,
     * while on other operating systems, it uses the 'clear' command to clear the console screen.</p>
     * <p>If an error occurs during this process (e.g., due to insufficient permissions or an unsupported OS),
     * an error message is printed to the console.</p>
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error while clearing screen");
        }
    }

    public String detailedProfile() {
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
     * Returns a string representation of the user, including their full name, email, phone number,
     * and user ID.
     * <p>The returned string contains the following format:</p>
     * <pre>
     * Full name: [firstName] [lastName]
     * Email: [email]
     * Phone no: [phoneNo]
     * User id: [userId]
     * </pre>
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        String result = "Full name: " + this.getFirstName() + " " +  this.getLastName() + "\n";
        result += "Username: " + this.getUsername() + "\n";
        result += "Email: " + this.getEmail() + "\n";
        result += "Phone no: " + this.getPhoneNo() + "\n";
        result += "User id: " + this.getUserId() + "\n";
        return result;
    }
}