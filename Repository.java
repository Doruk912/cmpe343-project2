import java.sql.*;

public class Repository {
    private String url = "jdbc:mysql://localhost:3306/cmpe343";
    private String username = "root";
    private String password = "admin";
    private Connection connection;

    public Repository() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void example(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println("Customer ID: " + resultSet.getInt("user_id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Email: " + resultSet.getString("email"));
            }
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
}
