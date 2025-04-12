package Day1.StackAndQueue;
import java.util.Stack;
public class QueueUsingStacks {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public QueueUsingStacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }
    public void enqueue(int x) {
        inStack.push(x);
    }
    public int dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        if (outStack.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return outStack.pop();
    }
    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        if (outStack.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return outStack.peek();
    }
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
    public int size() {
        return inStack.size() + outStack.size();
    }
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println(queue.dequeue());
        queue.enqueue(40);
        System.out.println(queue.dequeue());
        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
