package model;

import java.sql.Date;

/**
 * Represents an Engineer, which is a type of {@link RegularEmployee}.
 * <p>This class inherits all attributes and behavior from the {@link RegularEmployee} class.</p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Stores and manages information specific to engineers.</li>
 *     <li>Encapsulates attributes such as user ID, username, password, role, name, contact details, and dates.</li>
 * </ul>
 */
public class Engineer extends RegularEmployee {

    /**
     * Constructs an {@code Engineer} instance with the provided details.
     *
     * @param userId      The unique identifier for the engineer.
     * @param username    The username of the engineer.
     * @param password    The password of the engineer.
     * @param role        The role of the user, expected to be {@code "ENGINEER"}.
     * @param firstName   The first name of the engineer.
     * @param lastName    The last name of the engineer.
     * @param phoneNo     The phone number of the engineer.
     * @param email       The email address of the engineer.
     * @param dateOfBirth The date of birth of the engineer.
     * @param dateOfStart The date when the engineer started employment.
     */
    public Engineer(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }
}
