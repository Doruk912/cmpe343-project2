package model;

import java.io.IOException;
import java.sql.*;

public abstract class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private Date dateOfBirth;
    private Date dateOfStart;

    private String url = "jdbc:mysql://localhost:3306/cmpe343";
    private String db_username = "root";
    private String db_password = "admin";
    private Connection connection;

    public User(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;

        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract boolean menu();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ? WHERE user_id = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE user_id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET role = ? WHERE user_id = ?");
            preparedStatement.setString(1, role);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET first_name = ? WHERE user_id = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET last_name = ? WHERE user_id = ?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET phone_no = ? WHERE user_id = ?");
            preparedStatement.setString(1, phoneNo);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET email = ? WHERE user_id = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET date_of_birth = ? WHERE user_id = ?");
            preparedStatement.setDate(1, dateOfBirth);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET date_of_start = ? WHERE user_id = ?");
            preparedStatement.setDate(1, dateOfStart);
            preparedStatement.setInt(2, this.getUserId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error while clearing screen");
        }
    }

    public String detailedProfile() {
        return "User ID: " + this.getUserId() + "\n" +
                "Username: " + this.getUsername() + "\n" +
                "Role: " + this.getRole() + "\n" +
                "First Name: " + this.getFirstName() + "\n" +
                "Last Name: " + this.getLastName() + "\n" +
                "Phone Number: " + this.getPhoneNo() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Date of Birth: " + this.getDateOfBirth() + "\n" +
                "Date of Start: " + this.getDateOfStart() + "\n";
    }

    @Override
    public String toString() {
        String result = "Full name: " + this.getFirstName() + " " +  this.getLastName() + "\n";
        result += "Email: " + this.getEmail() + "\n";
        result += "Phone no: " + this.getPhoneNo() + "\n";
        result += "User id: " + this.getUserId() + "\n";
        return result;
    }
}