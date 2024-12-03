package util;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import static util.Application.clearScreen;


public class Algorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public Algorithm() {
        runAlgorithm();
    }

    private void runAlgorithm() {
        clearScreen();
        int size = askForDataSize();
        int[] dataset = datasetGenerator(size);

        /*
        System.out.println("Radix Sort is working...");
        long start = System.nanoTime();
        Algorithm.radixSort(dataset.clone());
        long radixTime = System.nanoTime() - start;
         */

        System.out.println("Shell Sort is working...");
        long start = System.nanoTime();
        Algorithm.shellSort(dataset.clone());
        long shellTime = System.nanoTime() - start;

        System.out.println("Sorting times:");
        //System.out.println("Radix Sort: " + radixTime);
        System.out.println("Shell Sort: " + shellTime);
        System.out.println("Press enter to continue...");
        scanner.nextLine(); //Consume the leftover newline character
        scanner.nextLine();
        clearScreen();
    }

    private int askForDataSize() {
        System.out.println("Enter the dataset size (1000 - 10000):");
        int size = 0;
        while (size < 1000 || size > 10000) {
            size = scanner.nextInt();
            if (size < 1000 || size > 10000) {
                System.out.println("Invalid size!");
            }
        }
        return size;
    }

    private int[] datasetGenerator(int size) {
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++)
            dataset[i] = random.nextInt(20001) - 10000;
        return dataset;
    }

    private long timeKeeper(int[] dataset, Runnable sortAlgorithm){
        int[] dataCopy = dataset.clone();
        long startTime = System.nanoTime();
        sortAlgorithm.run();
        return System.nanoTime() - startTime;
    }

    private static void radixSort (int[] array){
        int max = Arrays.stream(array).max().getAsInt();
        int exp = 1;

        while (max / exp > 0) {
            countingSortByDigit(array, exp);
            exp *= 10;
        }
    }

    private static void countingSortByDigit (int[] array, int exp){
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : array) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    private static void shellSort(int[] array){
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }
    }
}
