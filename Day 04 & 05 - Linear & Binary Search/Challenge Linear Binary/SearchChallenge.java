package Day4.ChallengeLinearBinary;
import java.util.Arrays;
public class SearchChallenge {
    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};
        int target = 4;

        int missingPositive = findFirstMissingPositive(arr.clone());
        System.out.println("First Missing Positive Integer: " + missingPositive);

        Arrays.sort(arr);
        int targetIndex = binarySearch(arr, target);
        System.out.println("Index of Target " + target + ": " + targetIndex);
    }

    public static int findFirstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
