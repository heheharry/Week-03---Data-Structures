package Day4.BinarySearch;

public class SearchIn2DSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        boolean result = searchMatrix(matrix, target);

        System.out.println("Target found: " + result);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0, right = rows * columns - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / columns;
            int col = mid % columns;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}
