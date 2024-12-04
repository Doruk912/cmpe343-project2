package util;

public class Validation {

    public boolean isValidPhoneNumber(String phoneNo) {
        String regex = "\\+\\d{1,3} \\d{10}";
        return phoneNo.matches(regex);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

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
