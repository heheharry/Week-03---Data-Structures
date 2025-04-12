package Day4.StringBuilder;

public class ReverseStringWithStringBuilder {
    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder(input);

        sb.reverse();

        return sb.toString();
    }

    public static void main(String[] args) {
        String original = "hello";
        String reversed = reverseString(original);

        System.out.println("Original String: " + original);
        System.out.println("Reversed String: " + reversed);
    }
}
