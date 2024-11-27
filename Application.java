import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Repository repository = new Repository();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Enter A to call example method, B to get user by ID, C to terminate:");
            input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "A":
                    repository.example();
                    break;
                case "B":
                    System.out.println("Enter user ID:");
                    int userId = Integer.parseInt(scanner.nextLine().trim());
                    User user = repository.getUserById(userId);
                    if (user != null) {
                        System.out.println("User ID: " + user.getUserId());
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Password: " + user.getPassword());
                        System.out.println("Role: " + user.getRole());
                        System.out.println("First Name: " + user.getFirstName());
                        System.out.println("Last Name: " + user.getLastName());
                        System.out.println("Phone No: " + user.getPhoneNo());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println("Date of Birth: " + user.getDateOfBirth());
                        System.out.println("Date of Start: " + user.getDateOfStart());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case "C":
                    System.out.println("Terminating...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

//chcp 65001
//javac Application.java
//java -cp ".;mysql-connector-j-9.1.0.jar" Application