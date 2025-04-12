package Day5.RuntimeBigO;
import java.util.Arrays;
import java.util.Random;
public class SortingComparision {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int[] generateRandomArray(int size, int maxVal) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxVal);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};

        for (int size : datasetSizes) {
            System.out.println("\nDataset size: " + size);
            int[] data1 = generateRandomArray(size, size * 10);
            int[] data2 = Arrays.copyOf(data1, data1.length);
            int[] data3 = Arrays.copyOf(data1, data1.length);

            if (size <= 10000) {
                long startBubble = System.nanoTime();
                bubbleSort(data1);
                long endBubble = System.nanoTime();
                long durationBubble = (endBubble - startBubble) / 1_000_000;
                System.out.println("Bubble Sort: " + durationBubble + " ms");
            } else {
                System.out.println("Bubble Sort: Skipped (too large)");
            }

            long startMerge = System.nanoTime();
            mergeSort(data2);
            long endMerge = System.nanoTime();
            long durationMerge = (endMerge - startMerge) / 1_000_000;
            System.out.println("Merge Sort: " + durationMerge + " ms");

            long startQuick = System.nanoTime();
            quickSort(data3, 0, data3.length - 1);
            long endQuick = System.nanoTime();
            long durationQuick = (endQuick - startQuick) / 1_000_000;
            System.out.println("Quick Sort: " + durationQuick + " ms");
        }
    }
}
