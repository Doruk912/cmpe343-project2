package util;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private final String url = "jdbc:mysql://localhost:3306/cmpe343";
    private final String username = "root";
    private final String password = "admin";
    private Connection connection;

    public Repository() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public ArrayList<User> getAllUsers() {
        try {
            ArrayList<User> users = new ArrayList<User>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user_id FROM users");
            while (resultSet.next()) {
                users.add(this.getUserById(resultSet.getInt(1)));
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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
