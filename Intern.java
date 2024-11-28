import java.sql.Date;
import java.util.Scanner;

public class Intern extends User {

    public Intern(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }
    
    public boolean menu() {
        System.out.println("[Intern]");
        System.out.println("Logged in as " + this.getUsername());

        Scanner scanner = new Scanner(System.in);
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
        System.out.println("display: Displays your profile");
        System.out.println("update <field>: Updates information");
        System.out.println("logout: Returns to login screen");

        System.out.println("Enter command:");
        input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "display":
                System.out.println(this);
                scanner.nextLine();
                break;
            case "update username":
                System.out.println("Enter new username:");
                input = scanner.nextLine().trim().toLowerCase();
                this.setUsername(input);
                break;
            case "update password":
                System.out.println("Enter new password:");
                input = scanner.nextLine().trim();
                this.setPassword(input);
                break;
            case "update first name":
                System.out.println("Enter new first name:");
                input = scanner.nextLine().trim();
                this.setFirstName(input);
                break;
            case "update last name":
                System.out.println("Enter new last name:");
                input = scanner.nextLine().trim();
                this.setLastName(input);
                break;
            case "logout":
                return false;
        }

        return true;
    }
}
