package Day1.HashMapAndHashFunctions;
import java.util.*;
public class MyHashMap {
    private static class Node {
        int key, value;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 1000;
    private Node[] map;

    public MyHashMap() {
        map = new Node[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void put(int key, int value) {
        int index = hash(key);
        if (map[index] == null) {
            map[index] = new Node(-1, -1);
        }
        Node prev = find(map[index], key);
        if (prev.next == null) {
            prev.next = new Node(key, value);
        } else {
            prev.next.value = value;
        }
    }

    public int get(int key) {
        int index = hash(key);
        if (map[index] == null) return -1;
        Node prev = find(map[index], key);
        if (prev.next == null) return -1;
        return prev.next.value;
    }

    public void remove(int key) {
        int index = hash(key);
        if (map[index] == null) return;
        Node prev = find(map[index], key);
        if (prev.next == null) return;
        prev.next = prev.next.next;
    }

    private Node find(Node head, int key) {
        Node node = head;
        while (node.next != null && node.next.key != key) {
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 10);
        map.put(2, 20);
        System.out.println(map.get(1));
        map.remove(1);
        System.out.println(map.get(1));
    }
}
