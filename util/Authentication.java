package util;

import model.User;
import java.util.Scanner;

public class Authentication {

    public User authenticate() {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository();

        User loggedInUser = null;
        while(loggedInUser == null) {
            System.out.println("Please enter your username, type q to quit: ");
            String username = scanner.nextLine().trim().toLowerCase();
            if(username.equals("q"))
                System.exit(0);
            loggedInUser = repository.getUserByUsername(username);
            if(loggedInUser == null) {
                System.out.println("User not found!");
                continue;
            }
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine().trim();
            if(!loggedInUser.getPassword().equals(password)) {
                System.out.println("Wrong password!");
                loggedInUser = null;
            }
        }
        return loggedInUser;
    }
}
