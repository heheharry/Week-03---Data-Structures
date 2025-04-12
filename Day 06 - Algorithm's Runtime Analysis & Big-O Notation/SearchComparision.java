package Day5.RuntimeBigO;
import java.util.Arrays;
import java.util.Random;
public class SearchComparision {
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
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
        int target = -1;

        for (int size : datasetSizes) {
            System.out.println("\nDataset size: " + size);

            int[] data = generateRandomArray(size, size * 10);

            long startLinear = System.nanoTime();
            int resultLinear = linearSearch(data, target);
            long endLinear = System.nanoTime();
            long durationLinear = (endLinear - startLinear) / 1_000_000;

            System.out.println("Linear Search: " + durationLinear + " ms");

            Arrays.sort(data);
            long startBinary = System.nanoTime();
            int resultBinary = binarySearch(data, target);
            long endBinary = System.nanoTime();
            long durationBinary = (endBinary - startBinary) / 1_000_000;

            System.out.println("Binary Search (after sort): " + durationBinary + " ms");
        }
    }
}
