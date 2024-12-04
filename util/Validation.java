package util;

/**
 * Provides utility methods for validating user input data such as phone numbers,
 * email addresses, and passwords.
 *
 * <p><b>Features:</b>
 * <ul>
 *     <li>Phone number validation</li>
 *     <li>Email address validation</li>
 *     <li>Password strength evaluation</li>
 * </ul>
 * @author imrandurmus
 */
public class Validation {

    /**
     * Validates a phone number based on a specific format.
     * The expected format is: {@code +<country code> <10-digit number>}.
     *
     * @param phoneNo The phone number to validate.
     * @return {@code true} if the phone number matches the format, {@code false} otherwise.
     */
    public boolean isValidPhoneNumber(String phoneNo) {
        String regex = "\\+\\d{1,3} \\d{10}";
        return phoneNo.matches(regex);
    }

    /**
     * Validates an email address based on a specific format.
     * The email must conform to a standard format such as: {@code example@domain.com}.
     *
     * @param email The email address to validate.
     * @return {@code true} if the email matches the format, {@code false} otherwise.
     */
    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    /**
     * Evaluates the strength of a password based on its composition.
     * <p>The strength is determined using the following criteria:
     * <ul>
     *     <li>+1: Password is at least 8 characters long</li>
     *     <li>+1: Password contains both uppercase and lowercase letters</li>
     *     <li>+1: Password contains at least one digit</li>
     *     <li>+1: Password contains at least one special character (e.g., {@code !@#$%^&*})</li>
     * </ul>
     * </p>
     *
     * @param password The password to evaluate.
     * @return An integer representing the strength of the password (range: 0 to 4).
     */
    public int getPasswordStrength(String password) {
        int strength = 0;
        if (password.length() >= 8)
            strength++;
        if (password.matches(".*[A-Z].*") && password.matches(".*[a-z].*"))
            strength++;
        if (password.matches(".*\\d.*"))
            strength++;
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))
            strength++;
        return strength;
    }
}
