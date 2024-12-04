package model;

import java.sql.Date;

/**
 * Represents a {@link Technician}, a subclass of {@link RegularEmployee}.
 * <p>The {@code Technician} class extends the functionality of the {@code RegularEmployee} class
 * to handle additional operations specific to technicians. As a subclass of {@link RegularEmployee},
 * it inherits all properties and methods for managing technician-specific tasks and profile details.</p>
 *
 * <p>This class does not add any new functionality or fields beyond those inherited from {@link RegularEmployee}.
 * It is intended for use as a specific role for employees who work as technicians, with the ability
 * to access and modify their profiles through the inherited methods.</p>
 * @author imrandurmus
 */
public class Technician extends RegularEmployee {

    /**
     * Constructs a {@code Technician} instance with the provided details.
     *
     * @param userId      The unique identifier for the technician.
     * @param username    The username of the technician.
     * @param password    The password of the technician.
     * @param role        The role of the user, expected to be {@code "TECHNICIAN"}.
     * @param firstName   The first name of the technician.
     * @param lastName    The last name of the technician.
     * @param phoneNo     The phone number of the technician.
     * @param email       The email address of the technician.
     * @param dateOfBirth The date of birth of the technician.
     * @param dateOfStart The date when the technician started employment.
     */
    public Technician(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }
}
