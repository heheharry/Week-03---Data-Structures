package Day5.RuntimeBigO;

public class FibonacciComparison {
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 30;

        long startRec = System.nanoTime();
        int recResult = fibonacciRecursive(n);
        long endRec = System.nanoTime();
        System.out.println("Recursive Result: " + recResult + ", Time: " + (endRec - startRec) / 1_000_000 + " ms");

        long startItr = System.nanoTime();
        int itrResult = fibonacciIterative(n);
        long endItr = System.nanoTime();
        System.out.println("Iterative Result: " + itrResult + ", Time: " + (endItr - startItr) / 1_000_000 + " ms");
    }
}
