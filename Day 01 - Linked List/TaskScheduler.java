package Day5.PracticeProblems;
import java.util.Scanner;
class Task {
    int taskId;
    String taskName;
    String priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, String priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class CircularTaskList {
    private Task head;
    private Task current;

    public void addTaskAtBeginning(int taskId, String taskName, String priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
    }

    public void addTaskAtEnd(int taskId, String taskName, String priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    public void addTaskAtPosition(int position, int taskId, String taskName, String priority, String dueDate) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null || position == 1) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task temp = head;
        int count = 1;
        while (count < position - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }

    public void removeTaskById(int taskId) {
        if (head == null) {
            System.out.println("No tasks to remove.");
            return;
        }
        Task temp = head;
        Task prev = null;
        do {
            if (temp.taskId == taskId) {
                if (prev != null) {
                    prev.next = temp.next;
                    if (temp == head) {
                        head = temp.next;
                    }
                } else {
                    if (temp.next == head) {
                        head = null;
                        current = null;
                        return;
                    }
                    Task last = head;
                    while (last.next != head) {
                        last = last.next;
                    }
                    head = temp.next;
                    last.next = head;
                }
                System.out.println("Task ID " + taskId + " removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Task ID " + taskId + " not found.");
    }

    public void viewCurrentAndMoveNext() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task: [ID: " + current.taskId + ", Name: " + current.taskName + ", Priority: " + current.priority + ", Due: " + current.dueDate + "]");
        current = current.next;
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        System.out.println("Task List:");
        do {
            System.out.println("[ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due: " + temp.dueDate + "]");
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(String priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority.equalsIgnoreCase(priority)) {
                System.out.println("Found Task: [ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due: " + temp.dueDate + "]");
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with Priority " + priority + ".");
        }
    }
}
public class TaskScheduler {
    public static void main(String[] args) {
        CircularTaskList taskList = new CircularTaskList();
        taskList.addTaskAtEnd(1, "Task A", "High", "2025-04-10");
        taskList.addTaskAtEnd(2, "Task B", "Medium", "2025-04-11");
        taskList.addTaskAtBeginning(3, "Task C", "Low", "2025-04-12");
        taskList.addTaskAtPosition(2, 4, "Task D", "High", "2025-04-13");

        taskList.displayAllTasks();
        System.out.println();
        taskList.viewCurrentAndMoveNext();
        taskList.viewCurrentAndMoveNext();

        System.out.println();
        taskList.searchByPriority("High");

        System.out.println();
        taskList.removeTaskById(2);
        taskList.displayAllTasks();
    }
}
