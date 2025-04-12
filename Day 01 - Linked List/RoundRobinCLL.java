package Day5.PracticeProblems;
class ProcessScheduler {
    private ProcessNode head;
    private int timeQuantum;
    private int totalWaitingTime;
    private int totalTurnaroundTime;
    private int totalProcesses;

    public ProcessScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
        this.head = null;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            ProcessNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        totalProcesses++;
    }

    public void removeProcess(int processId) {
        if (head == null) return;

        ProcessNode curr = head;
        ProcessNode prev = null;

        do {
            if (curr.processId == processId) {
                if (curr == head && curr.next == head) {
                    head = null;
                } else {
                    if (curr == head) {
                        ProcessNode temp = head;
                        while (temp.next != head) {
                            temp = temp.next;
                        }
                        head = head.next;
                        temp.next = head;
                    } else {
                        prev.next = curr.next;
                    }
                }
                totalProcesses--;
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    public void executeProcesses() {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        ProcessNode curr = head;
        int currentTime = 0;

        while (totalProcesses > 0) {
            if (curr.burstTime > 0) {
                int execTime = Math.min(timeQuantum, curr.burstTime);
                curr.burstTime -= execTime;
                currentTime += execTime;

                if (curr.burstTime == 0) {
                    curr.completionTime = currentTime;
                    curr.turnaroundTime = curr.completionTime;
                    curr.waitingTime = curr.turnaroundTime - curr.initialBurstTime;

                    totalWaitingTime += curr.waitingTime;
                    totalTurnaroundTime += curr.turnaroundTime;

                    System.out.println("Process " + curr.processId + " completed at time " + currentTime);
                    int pid = curr.processId;
                    curr = curr.next;
                    removeProcess(pid);
                } else {
                    curr = curr.next;
                }
            } else {
                curr = curr.next;
            }

            displayProcesses();
        }
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        ProcessNode temp = head;
        do {
            System.out.print("[PID: " + temp.processId + ", Remaining BT: " + temp.burstTime + ", Priority: " + temp.priority + "] -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(Back to Start)");
    }

    public void calculateAndDisplayTimes() {
        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / (totalWaitingTime == 0 ? 1 : totalProcesses + totalWaitingTime/4));
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / (totalTurnaroundTime == 0 ? 1 : totalProcesses + totalTurnaroundTime/4));
    }
}

// Node class representing each process
class ProcessNode {
    int processId;
    int burstTime;
    int initialBurstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    int completionTime;
    ProcessNode next;

    public ProcessNode(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.initialBurstTime = burstTime;
        this.priority = priority;
        this.next = null;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.completionTime = 0;
    }
}
public class RoundRobinCLL {
    public static void main(String[] args) {
        ProcessScheduler scheduler = new ProcessScheduler(4); // Time Quantum = 4 units

        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.addProcess(4, 6, 2);

        System.out.println("Initial Process List:");
        scheduler.displayProcesses();

        System.out.println("\nStarting Round Robin Scheduling:");
        scheduler.executeProcesses();

        scheduler.calculateAndDisplayTimes();
    }
}
