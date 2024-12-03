package util;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import static util.Application.clearScreen;


public class Algorithm {

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    /**
     *
     */
    public Algorithm() {
        runAlgorithm();
    }

    /**
     *
     */
    private void runAlgorithm() {
        clearScreen();
        int size = askForDataSize();
        int runs = 30;
        int[] dataset = datasetGenerator(size);

        System.out.println("Verifying sorting algorithms...");
        verifySortingAlgorithms(dataset);

        System.out.println("Radix Sort is working...");
        long radixTime = timeKeeper(dataset, "Radix", runs)/1000000;

        System.out.println("Shell Sort is working...");
        long shellTime = timeKeeper(dataset, "Shell", runs)/1000000;

        System.out.println("Heap Sort is working...");
        long heapTime = timeKeeper(dataset, "Heap", runs)/1000000;

        System.out.println("Insertion Sort is working...");
        long insertionTime = timeKeeper(dataset, "Insertion", runs)/1000000;

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
     *
     * @return
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
     *
     * @param size
     * @return
     */
    private int[] datasetGenerator(int size) {
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++)
            dataset[i] = random.nextInt(20001) - 10000;
        return dataset;
    }

    /**
     *
     * @param dataset
     * @param algorithm
     * @return
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
     *
     * @param dataset
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
     *
     * @param dataset
     * @param algorithm
     * @param runs
     * @return
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
        return totalTime;
    }
    /**
     *
     * @param array
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
     *
     * @param array
     * @return
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
     *
     * @param array
     * @return
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
     *
     * @param array
     * @param exp
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
     *
     * @param array
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
     *
     * @param array
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
     *
     * @param array
     * @param n
     * @param i
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
     *
     * @param array
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
