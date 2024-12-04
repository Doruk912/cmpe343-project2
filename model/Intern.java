package model;

import java.sql.Date;

/**
 * Represents an Intern, which is a type of {@link RegularEmployee}.
 * <p>This class inherits all attributes and behavior from the {@link RegularEmployee} class.</p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Stores and manages information specific to interns.</li>
 *     <li>Encapsulates attributes such as user ID, username, password, role, name, contact details, and dates.</li>
 * </ul>
 */
public class Intern extends RegularEmployee {

    /**
     * Constructs an {@code Intern} instance with the provided details.
     *
     * @param userId      The unique identifier for the intern.
     * @param username    The username of the intern.
     * @param password    The password of the intern.
     * @param role        The role of the user, expected to be {@code "INTERN"}.
     * @param firstName   The first name of the intern.
     * @param lastName    The last name of the intern.
     * @param phoneNo     The phone number of the intern.
     * @param email       The email address of the intern.
     * @param dateOfBirth The date of birth of the intern.
     * @param dateOfStart The date when the intern started employment.
     */
    public Intern(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }
}
