import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Repository {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/cmpe343?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "admin";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");

            // Example Query
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
}

//chcp 65001
//javac Repository.java
//java -cp ".;mysql-connector-j-9.1.0.jar" Repository
