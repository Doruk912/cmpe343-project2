package util;

import model.*;

import java.sql.*;

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
}
