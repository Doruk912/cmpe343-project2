package util;

import model.User;
import java.util.Scanner;

/**
 * <h2>Authentication Class</h2>
 *
 * <p>The Authentication class handles the user authentication process. It prompts the user for their username and password,
 * verifies them against stored credentials. In the case of login credentials being wrong user gets to try again or quit. The user can also quit the process by entering 'q' as the username.</p>
 *
 * <p>The authenticate method uses a loop to continuously request credentials until the user is successfully authenticated or decides to quit.</p>
 *
 * <h3>Methods:</h3>
 * <ul>
 *     <li><b>authenticate:</b> Prompts the user for their username and password, verifies the credentials, and returns the authenticated User object.</li>
 * </ul>
 *
 * @author imrandurmus
 */
public class Authentication {

    /**
     * <h2>authenticate</h2>
     *
     * <p>This method handles the authentication process by asking the user for their username and password. The username is verified against the repository,
     * and if found, the user is asked for their password. If the credentials match, the user is authenticated and returned.
     * If the username or password is incorrect, the user will be prompted again. The process ends if the user chooses to quit by typing 'q'.</p>
     *
     * <h3>Flow:</h3>
     * <ol>
     *     <li>User is asked to enter a username.</li>
     *     <li>If the username exists, the user is prompted for their password.</li>
     *     <li>If the password is correct, the user is authenticated.</li>
     *     <li>If the username or password is incorrect, the process repeats.</li>
     *     <li>The process can be exited by typing 'q' during the username prompt.</li>
     * </ol>
     *
     * @return <code>User</code> - the authenticated User object if credentials are correct.
     * @throws SystemExitException if the user chooses to quit by typing 'q'.
     */
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
