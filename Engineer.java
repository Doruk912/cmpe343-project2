import java.sql.Date;

public class Engineer extends User {

    public Engineer(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }
    
    public boolean menu() {
        System.out.println("Engineer");
        return false;
    }
}
