package model;

import util.Algorithm;
import util.Repository;

import java.sql.Date;
import java.util.Scanner;

public class Manager extends User {

    public Manager(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[" + this.getRole() + " MENU]");
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
        System.out.println("employees: Lists all employees");
        System.out.println("hire: Hire employee");
        System.out.println("fire: Fire employee");
        System.out.println("algorithms: Compares sorting algorithms");
        System.out.println("logout: Returns to login screen");

        System.out.println("Enter command:");
        input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "display":
                System.out.println("\n" + this);
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "employees":
                Repository repo = new Repository();
                for (User u : repo.getAllUsers())
                    System.out.println(u + "\n");
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                clearScreen();
                break;
            case "hire":
                Repository repo_h = new Repository();
                System.out.println("Username");
                String username = scanner.nextLine().trim().toLowerCase();

                if (repo_h.getUserByUsername(username) != null) {
                    System.out.println("Username already exists");
                    break;
                }
                
                System.out.println("Choose role:");
                System.out.println("[1] MANAGER");
                System.out.println("[2] ENGINEER");
                System.out.println("[3] TECHNICIAN");
                System.out.println("[4] INTERN");
                String role = scanner.nextLine();
                switch (role) {
                    case "1":
                        role = "MANAGER";
                        break;
                    case "2":
                        role = "ENGINEER";
                        break;
                    case "3":
                        role = "TECHNICIAN";
                        break;
                    case "4":
                        role = "INTERN";
                        break;
                    default:
                        role = "INTERN";
                }

                System.out.println("First name:");
                String first = scanner.nextLine();
                System.out.println("Last name:");
                String last = scanner.nextLine();

                repo_h.addUser(username, role, first, last);
                break;
            case "fire":
                while (true) {
                    System.out.print("User id to be fired: ");
                    input = scanner.nextLine();
                    if (input.isBlank()) {
                        System.out.println("Empty input. Please try again.");
                        continue;
                    }
                    try {
                        if (this.getUserId() == Integer.parseInt(input)) {
                            System.out.println("You cannot fire yourself.");
                            break;
                        }
                        Repository repo_f = new Repository();
                        repo_f.removeUser(Integer.parseInt(input));
                        System.out.println("User " + Integer.parseInt(input) + " is fired if it existed.");
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong data type, try again");
                    }
                }
                break;
            case "algorithms":
                new Algorithm();
                break;
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
}

