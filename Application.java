import java.util.Scanner;
import java.io.IOException;
import java.sql.*;

public class Application {
    private static String url = "jdbc:mysql://localhost:3306/cmpe343";
    private static String username = "root";
    private static String password = "admin";
    private static Connection connection;

    public Application() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ClearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error while clearing screen");
        }
    }

    public static void main(String[] args) {

        Repository repository = new Repository();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Username: ");
            input = scanner.nextLine().trim().toUpperCase();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                preparedStatement.setString(1, input);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Password: ");
                    input = scanner.nextLine().trim().toUpperCase();
                    if (resultSet.getString("password").equals(input)) {
                        User user = repository.getUserById(resultSet.getInt("user_id"));
                        do {
                            ClearScreen();
                        } while (user.menu());
                    } else {
                        System.out.println("Wrong password");
                        continue;
                    }
                } else {
                    System.out.println("There are no user named " + input);
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

//chcp 65001
//javac Application.java
//java -cp ".;mysql-connector-j-9.1.0.jar" Application