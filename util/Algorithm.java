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

        // Uncomment to include Radix Sort
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

        System.out.println("Heap Sort is working...");
        start = System.nanoTime();
        Algorithm.heapSort(dataset.clone());
        long heapTime = System.nanoTime() - start;

        System.out.println("Insertion Sort is working...");
        start = System.nanoTime();
        Algorithm.insertionSort(dataset.clone());
        long insertionTime = System.nanoTime() - start;

        System.out.println("Sorting times:");
        // Uncomment to display Radix Sort time
        // System.out.println("Radix Sort: " + radixTime + " ns");
        System.out.println("Shell Sort: " + shellTime + " ns");
        System.out.println("Heap Sort: " + heapTime + " ns");
        System.out.println("Insertion Sort: " + insertionTime + " ns");
        System.out.println("Press enter to continue...");
        scanner.nextLine(); // Consume the leftover newline character
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

    private long timeKeeper(int[] dataset, Runnable sortAlgorithm, int runs){
        long totalTime = 0;
        for (int i = 0; i < runs; i++) {
            int[] dataCopy = dataset.clone();
            long startTime = System.nanoTime();
            sortAlgorithm.run();
            long endTime =  System.nanoTime() - startTime;
            totalTime += endTime - startTime;
        }
        return totalTime;
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
