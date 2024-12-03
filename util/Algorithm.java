import java.util.Random;
import java.util.Scanner;

public class Manager {

    public static void radixSort(int[] array) {
        int max = findMax(array);
        int min = findMin(array);

        int offset = Math.abs(min); // Shift negatives to positive
        for (int i = 0; i < array.length; i++) {
            array[i] += offset;
        }

        int exp = 1;
        while ((max + offset) / exp > 0) {
            countingSortByDigit(array, exp);
            exp *= 10;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] -= offset; // Shift back to original range
        }
    }

    private static int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static int findMin(int[] array) {
        int min = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    private static void countingSortByDigit(int[] array, int exp) {
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

    public static void shellSort(int[] array) {
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

    public static void algorithms() {
        Scanner scanner = new Scanner(System.in);

        // Get dataset size from user
        int datasetSize = 0;
        while (datasetSize < 1000 || datasetSize > 10000) {
            System.out.print("Enter the dataset size (1000 - 10000): ");
            datasetSize = scanner.nextInt();
            if (datasetSize < 1000 || datasetSize > 10000) {
                System.out.println("Invalid input. Please enter a number between 1000 and 10000.");
            }
        }

        // Generate random numbers
        int[] datasetForRadixSort = new int[datasetSize];
        int[] datasetForShellSort = new int[datasetSize];
        Random random = new Random();
        for (int i = 0; i < datasetSize; i++) {
            int num = random.nextInt(20000) - 10000; // Range -10000 to 10000
            datasetForRadixSort[i] = num;
            datasetForShellSort[i] = num;
        }

        // Apply Radix Sort
        System.out.println("Radix Sort:");
        long startTime = System.nanoTime();
        radixSort(datasetForRadixSort);
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
        System.out.println("First 10 elements after Radix Sort:");
        for (int i = 0; i < 10 && i < datasetSize; i++) {
            System.out.print(datasetForRadixSort[i] + " ");
        }
        System.out.println("\n");

        // Apply Shell Sort
        System.out.println("Shell Sort:");
        startTime = System.nanoTime();
        shellSort(datasetForShellSort);
        endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
        System.out.println("First 10 elements after Shell Sort:");
        for (int i = 0; i < 10 && i < datasetSize; i++) {
            System.out.print(datasetForShellSort[i] + " ");
        }
        System.out.println("\n");

        scanner.close();
    }

    public static void main(String[] args) {
        algorithms();
    }
}