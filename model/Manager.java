package model;

import util.Algorithm;

import java.sql.Date;
import java.util.Scanner;

public class Manager extends User {

    public Manager(int userId, String username, String password, String role, String firstName, String lastName, String phoneNo, String email, Date dateOfBirth, Date dateOfStart) {
        super(userId, username, password, role, firstName, lastName, phoneNo, email, dateOfBirth, dateOfStart);
    }

    public boolean menu() {
        System.out.println("[" + this.getRole() + "]");
        System.out.println("Logged in as " + this.getUsername());

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Some useful commands:");
        System.out.println("display: Displays your profile");
        System.out.println("algorithms: Compares sorting algorithms");
        System.out.println("logout: Returns to login screen");

        System.out.println("Enter command:");
        input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "display":
                System.out.println(this);
                break;
            case "algorithms":
                System.out.println("Enter the dataset size (1000 - 10000):");
                int size = scanner.nextInt();
                if (size < 1000 || size > 10000) {
                    System.out.println("Invalid size!");
                    break;
                }
                int[] dataset = new int[size];
                for (int i = 0; i < size; i++) {
                    dataset[i] = (int) (Math.random() * 20001) - 10000;
                }

                System.out.println("Radix Sort is working...");
                long start = System.nanoTime();
                Algorithm.radixSort(dataset.clone());
                long radixTime = System.nanoTime() - start;

                System.out.println("Shell Sort is working...");
                start = System.nanoTime();
                Algorithm.shellSort(dataset.clone());
                long shellTime = System.nanoTime() - start;

                System.out.println("Sorting times:");
                System.out.println("Radix Sort: " + radixTime);
                System.out.println("Shell Sort: " + shellTime);
                break;
            case "logout":
                System.out.println("Logged out.");
                return false;
        }
        return true;
    }
}

