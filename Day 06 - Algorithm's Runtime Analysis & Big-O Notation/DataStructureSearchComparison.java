package Day5.RuntimeBigO;
import java.util.*;
public class DataStructureSearchComparison {
    public static void main(String[] args) {
        int n = 1_000_000;
        int target = n - 1;

        int[] array = new int[n];
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            array[i] = i;
            hashSet.add(i);
            treeSet.add(i);
        }

        long startArray = System.nanoTime();
        boolean foundArray = false;
        for (int num : array) {
            if (num == target) {
                foundArray = true;
                break;
            }
        }
        long endArray = System.nanoTime();

        long startHashSet = System.nanoTime();
        boolean foundHashSet = hashSet.contains(target);
        long endHashSet = System.nanoTime();

        long startTreeSet = System.nanoTime();
        boolean foundTreeSet = treeSet.contains(target);
        long endTreeSet = System.nanoTime();

        System.out.println("Array Search Time: " + (endArray - startArray) / 1_000_000 + " ms");
        System.out.println("HashSet Search Time: " + (endHashSet - startHashSet) / 1_000_000 + " ms");
        System.out.println("TreeSet Search Time: " + (endTreeSet - startTreeSet) / 1_000_000 + " ms");
    }
}
