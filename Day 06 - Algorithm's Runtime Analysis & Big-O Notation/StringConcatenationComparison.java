package Day5.RuntimeBigO;

public class StringConcatenationComparison {
    public static void stringConcat(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "a";
        }
    }

    public static void stringBuilderConcat(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
    }

    public static void stringBufferConcat(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
    }

    public static void main(String[] args) {
        int[] operationCounts = {1000, 10000, 1000000};

        for (int n : operationCounts) {
            System.out.println("\nOperations count: " + n);

            if (n <= 10000) {
                long startString = System.nanoTime();
                stringConcat(n);
                long endString = System.nanoTime();
                long durationString = (endString - startString) / 1_000_000;
                System.out.println("String: " + durationString + " ms");
            } else {
                System.out.println("String: Skipped (too slow)");
            }

            long startBuilder = System.nanoTime();
            stringBuilderConcat(n);
            long endBuilder = System.nanoTime();
            long durationBuilder = (endBuilder - startBuilder) / 1_000_000;
            System.out.println("StringBuilder: " + durationBuilder + " ms");

            long startBuffer = System.nanoTime();
            stringBufferConcat(n);
            long endBuffer = System.nanoTime();
            long durationBuffer = (endBuffer - startBuffer) / 1_000_000;
            System.out.println("StringBuffer: " + durationBuffer + " ms");
        }
    }
}
