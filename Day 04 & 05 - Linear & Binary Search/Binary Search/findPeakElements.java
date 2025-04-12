package Day4.BinarySearch;

public class findPeakElements {
    public static void main(String[] args) {
        int[] array = {1, 3, 20, 4, 1, 0};
        int peakElement = findPeakElement(array);

        System.out.println("Peak element is: " + peakElement);
    }

    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if ((mid == 0 || arr[mid - 1] <= arr[mid]) && (mid == arr.length - 1 || arr[mid + 1] <= arr[mid])) {
                return arr[mid];
            } else if (mid > 0 && arr[mid - 1] > arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
