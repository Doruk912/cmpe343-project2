package util;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import static util.Application.clearScreen;

/**
 * Algorithm class is created to benchmark the various sorting algorithms
 * while checking their correctness utilizing the default java sorting algorithm.
 */
public class Algorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    /**
     * Constructor for the Algorithm class.
     * It starts the algorithm execution by calling runAlgorithm().
     */
    public Algorithm() {
        runAlgorithm();
    }

    /**
     * Asks for a dataset size.
     * Generates the dataset.
     * Verifies that each sorting algorithm works properly.
     * Times each of the sorting algorithms to measure their performances.
     */
    private void runAlgorithm() {
        clearScreen();
        int size = askForDataSize();
        int runs = 30;
        int[] dataset = datasetGenerator(size);

        System.out.println("Verifying sorting algorithms...");
        verifySortingAlgorithms(dataset);

        System.out.println("Radix Sort is working...");
        long radixTime = timeKeeper(dataset, "Radix", runs);

        System.out.println("Shell Sort is working...");
        long shellTime = timeKeeper(dataset, "Shell", runs);

        System.out.println("Heap Sort is working...");
        long heapTime = timeKeeper(dataset, "Heap", runs);

        System.out.println("Insertion Sort is working...");
        long insertionTime = timeKeeper(dataset, "Insertion", runs);

        System.out.println("Sorting times:");
        System.out.printf("%-15s: %.3f ms%n", "Radix Sort", radixTime);
        System.out.printf("%-15s: %.3f ms%n", "Shell Sort", shellTime);
        System.out.printf("%-15s: %.3f ms%n", "Heap Sort", heapTime);
        System.out.printf("%-15s: %.3f ms%n", "Insertion Sort", insertionTime);
        scanner.nextLine(); // Consume the leftover newline character
        scanner.nextLine();
        clearScreen();
    }

    /**
     * Asks the user for the dataset size, ensuring it's between 1000 and 10000.
     * @return the size of the dataset entered by the user.
     */
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

    /**
     *Generates the dataset with random integers between -10000 and 10000.
     *
     * @param size number of the elements in the dataset.
     * @return an array of the generated data set.
     */
    private int[] datasetGenerator(int size) {
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++)
            dataset[i] = random.nextInt(20001) - 10000;
        return dataset;
    }

    /**
     *Verifies if the sorting algorithms are sorting the data set correctly by comparing them to default java sorting algorithm.
     *
     * @param dataset the data set to sort
     * @param algorithm the name of the sorting algorithm to use
     * @return true if the sorting algorithm works correctly, false otherwise.
     */
    private boolean correctSorting(int[] dataset, String algorithm) {
        int[] expected = dataset.clone();
        Arrays.sort(expected);

        int[] actual = dataset.clone();
        switch (algorithm) {
            case "Radix":
                radixSort(actual);
                break;
            case "Shell":
                shellSort(actual);
                break;
            case "Heap":
                heapSort(actual);
                break;
            case "Insertion":
                insertionSort(actual);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }

        return Arrays.equals(expected, actual);
    }

    /**
     * Final verification, checks if the sorting algorithms gave the same output as the default sort provieded by java.
     * Prints out the result on the console. It uses a list to add the failed algorithms to a list and displays those failed
     * algorithms.
     *
     * @param dataset the dataset to verify with all sorting algorithms.
     */
    private void verifySortingAlgorithms(int[] dataset) {
        List<String> failedAlgorithms = new ArrayList<>();

        if (!correctSorting(dataset, "Radix")) {
            System.err.println("Radix Sort failed!");
            failedAlgorithms.add("Radix Sort");
        }
        if (!correctSorting(dataset, "Shell")) {
            System.err.println("Shell Sort failed!");
            failedAlgorithms.add("Shell Sort");
        }
        if (!correctSorting(dataset, "Heap")) {
            System.err.println("Heap Sort failed!");
            failedAlgorithms.add("Heap Sort");
        }
        if (!correctSorting(dataset, "Insertion")) {
            System.err.println("Insertion Sort failed!");
            failedAlgorithms.add("Insertion Sort");
        }

        if (!failedAlgorithms.isEmpty()) {
            System.out.println("Some algorithms failed: " + String.join(", ", failedAlgorithms));
        } else {
            System.out.println("All sorting algorithms verified!");
        }
    }


    /**
     *The JVM optimizes it compilation on every run, so it's like warming up an engine. One time runs of the algorithms
     * won't give us the real benchmarks. The performance of the run times can change depending on the system load at
     * moment. This is why we run the algorithms multiple times and take the average time of the runs. This is still not
     * the most optimized measuring of the performance, the real optimized measuring is out of the scope of this project.
     *
     *
     * @param dataset The dataset to be sorted.
     * @param algorithm The name of the sorting algorithm to be tested.
     * @param runs The number of runs that the chosen algorithm is going to be tested.
     * @return Returns the average run time of the chosen algorithm in milliseconds.
     */
    private long timeKeeper(int[] dataset, String algorithm, int runs) {
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            int[] dataCopy = dataset.clone();
            long startTime = System.nanoTime();
            switch (algorithm) {
                case "Radix":
                    radixSort(dataCopy);
                    break;
                case "Shell":
                    shellSort(dataCopy);
                    break;
                case "Heap":
                    heapSort(dataCopy);
                    break;
                case "Insertion":
                    insertionSort(dataCopy);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
            }
            long endTime = System.nanoTime() - startTime;
            totalTime += endTime;
        }
        return (totalTime/(double)runs)/1_000_000.0;
    }
    /**
     * Implements the Radix Sort algorithm for sorting integers.
     * It uses counting sort as a subroutine to sort the array by each digit,
     * starting from the least significant digit to the most significant digit.
     * Handles negative numbers by shifting the range of the array temporarily.
     *
     * @param array the array of integers to be sorted.
     */
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

    /**
     * Finds the maximum value in an integer array.
     *
     * @param array the array of integers.
     * @return the maximum value in the array.
     */
    private static int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Finds the minimum value in an integer array.
     *
     * @param array the array of integers.
     * @return the minimum value in the array.
     */
    private static int findMin(int[] array) {
        int min = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Performs counting sort on the array based on the digit at a specific place value.
     *
     * @param array the array of integers to be sorted.
     * @param exp the exponent representing the current digit place (e.g., 1 for units, 10 for tens).
     */
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

    /**
     * Implements the Shell Sort algorithm for sorting integers.
     * It works by comparing elements at a certain gap and progressively
     * reducing the gap until the array is fully sorted.
     *
     * @param array the array of integers to be sorted.
     */
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

    /**
     * Implements the Heap Sort algorithm for sorting integers.
     * It builds a max heap from the array and repeatedly extracts the maximum
     * element to place it at the end of the array.
     *
     * @param array the array of integers to be sorted.
     */
    public static void heapSort(int[] array)
    {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(array, n, i);
        }
        for (int i = n - 1; i > 0; i--)
        {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    /**
     * Restores the max heap property for a subtree rooted at a given index.
     *
     * @param array the array representing the heap.
     * @param n the size of the heap.
     * @param i the index of the root of the subtree.
     */
    private static void heapify(int[] array, int n, int i)
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest])
        {
            largest = left;
        }

        if (right < n && array[right] > array[largest])
        {
            largest = right;
        }

        if (largest != i)
        {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

    /**
     * Implements the Insertion Sort algorithm for sorting integers.
     * It builds the sorted portion of the array one element at a time
     * by inserting each new element into its correct position.
     *
     * @param array the array of integers to be sorted.
     */
    public static void insertionSort(int[] array)
    {
        int n = array.length;

        for (int i = 1; i < n; i++)
        {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key)
            {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
}
