package Day4.LinearSearch;

public class linearSearch {
    public static void main(String[] args) {
        int[] arr = {5, 3, -1, 7, 8, -5, 9};

        int index = findFirstNegativeNumber(arr);

        if (index != -1) {
            System.out.println("The first negative number is at index: " + index);
        } else {
            System.out.println("No negative number found.");
        }
    }

    public static int findFirstNegativeNumber(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i; // Return the index of the first negative number
            }
        }
        return -1; // Return -1 if no negative number is found
    }
}
