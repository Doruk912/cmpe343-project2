package util;

import model.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides database-related operations for managing {@link User} objects.
 * This class handles connections to a MySQL database and allows for CRUD operations
 * such as retrieving, adding, and removing users from the database.
 *
 * <p><b>Features:</b>
 * <ul>
 *     <li>Retrieve user details by ID or username</li>
 *     <li>Fetch all users</li>
 *     <li>Add or remove users</li>
 *     <li>Role-specific user object instantiation (e.g., Manager, Engineer)</li>
 * </ul>
 * @author imrandurmus
 */
public class Repository {
    private final String url = "jdbc:mysql://localhost:3306/cmpe343";
    private final String username = "root";
    private final String password = "admin";
    private Connection connection;

    /**
     * Initializes a {@code Repository} instance and establishes a connection to the database.
     */
    public Repository() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a {@link User} by their unique ID from the database.
     *
     * @param userId The unique identifier of the user.
     * @return A {@link User} object representing the user, or {@code null} if no user is found.
     */
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                switch (resultSet.getString("role")) {
                    case "MANAGER":
                        user = new Manager(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                    case "ENGINEER":
                        user = new Engineer(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                    case "TECHNICIAN":
                        user = new Technician(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                    case "INTERN":
                        user = new Intern(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Retrieves a {@link User} by their username from the database.
     *
     * @param username The username of the user.
     * @return A {@link User} object representing the user, or {@code null} if no user is found.
     */
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                switch (resultSet.getString("role")) {
                    case "MANAGER":
                        user = new Manager(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                    case "ENGINEER":
                        user = new Engineer(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                    case "TECHNICIAN":
                        user = new Technician(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                    case "INTERN":
                        user = new Intern(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone_no"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth"),
                            resultSet.getDate("date_of_start")
                        );
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return An {@link ArrayList} containing all {@link User} objects, or {@code null} if an error occurs.
     */
    public ArrayList<User> getAllUsers() {
        try {
            ArrayList<User> users = new ArrayList<User>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user_id FROM users ORDER BY first_name");
            while (resultSet.next()) {
                users.add(this.getUserById(resultSet.getInt(1)));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a new user to the database.
     *
     * @param usr   The username of the new user.
     * @param role  The role of the new user (e.g., "MANAGER", "ENGINEER").
     * @param first The first name of the new user.
     * @param last  The last name of the new user.
     */
    public void addUser(String usr, String role, String first, String last) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password, role, first_name, last_name) VALUES (?, 'password', ?, ?, ?)");
            preparedStatement.setString(1, usr);
            preparedStatement.setString(2, role);
            preparedStatement.setString(3, first);
            preparedStatement.setString(4, last);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a user from the database by their unique ID.
     *
     * @param user_id The unique identifier of the user to be removed.
     */
    public void removeUser(int user_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
