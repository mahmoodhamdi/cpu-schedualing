import java.util.*;

enum ProcessState {
    READY,
    RUNNING,
    COMPLETED
}

class Process {
    int processNumber;
    int arrivalTime;
    int requiredCPUTime;
    int priority;
    ProcessState processState;
    int startTime;
    int endTime;

    public Process(int processNumber, int arrivalTime, int requiredCPUTime, int priority) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.requiredCPUTime = requiredCPUTime;
        this.priority = priority;
        this.processState = ProcessState.READY;
        this.startTime = -1;
        this.endTime = -1;
    }

    public void setProcessState(ProcessState state) {
        this.processState = state;
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public int getWaitingTime() {
        return startTime - arrivalTime;
    }

    public int getTurnaroundTime() {
        return endTime - arrivalTime;
    }

    public int getResponseTime() {
        return startTime - arrivalTime;
    }
}

class ReadyQueue {
    Queue<Process> queue;

    public ReadyQueue() {
        queue = new LinkedList<>();
    }

    public void addProcess(Process process) {
        queue.add(process);
    }

    public Process getNextProcess() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
public class Fcfc {
    public static void executeFCFS(Scanner scanner) {
        List<Process> processes = new ArrayList<>();
        
        System.out.println("Enter the number of processes:");
        int numProcesses = scanner.nextInt();

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("\u001B[38;2;255;149;0m--------------------------------------");
            System.out.println("| Enter details for Process " + (i + 1) + ":");
            System.out.println("--------------------------------------\u001B[0m");

            System.out.print("\u001B[38;2;255;149;0mArrival Time:\u001B[0m ");
            int arrivalTime = scanner.nextInt();

            System.out.print("\u001B[38;2;255;149;0mCPU Burst Time:\u001B[0m ");
            int burstTime = scanner.nextInt();

            System.out.print("\u001B[38;2;255;149;0mPriority:\u001B[0m ");
            int priority = scanner.nextInt();

            processes.add(new Process(i + 1, arrivalTime, burstTime, priority));
        }

        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        ReadyQueue readyQueue = new ReadyQueue();

        for (Process process : processes) {
            readyQueue.addProcess(process);
        }

        int currentTime = 0;
        float totalTurnaroundTime = 0;
        float totalWaitingTime = 0;
        float totalResponseTime = 0;

        System.out.println("\u001B[36m  .______________________________________.");
        System.out.println("  |                                      |");
        System.out.println("  |           Welcome to FCFC            |");
        System.out.println("  |______________________________________|\u001B[0m");
        System.out.println("Executing processes...");

        while (!readyQueue.isEmpty()) {
            Process currentProcess = readyQueue.getNextProcess();

            if (currentTime < currentProcess.arrivalTime) {
                currentTime = currentProcess.arrivalTime;
            }

            currentProcess.setProcessState(ProcessState.RUNNING);
            currentProcess.startTime = currentTime;

            System.out.println("\u001B[33m  .------------------------------------.");
            System.out.println("  |                                    |");
            System.out.println("  |  Process " + currentProcess.processNumber + " starts at time " + currentTime + "  |");
            System.out.println("  |                                    |");
            System.out.println("  '------------------------------------'\u001B[0m");
            currentTime += currentProcess.requiredCPUTime;

            currentProcess.setProcessState(ProcessState.COMPLETED);
            currentProcess.endTime = currentTime;

            int turnaroundTime = currentProcess.getTurnaroundTime();
            int waitingTime = currentProcess.getWaitingTime();
            int responseTime = currentProcess.getResponseTime();

            totalTurnaroundTime += turnaroundTime;
            totalWaitingTime += waitingTime;
            totalResponseTime += responseTime;
            System.out.println("\u001B[36m    .__________________________________.");
            System.out.println(
                    "  Process " + currentProcess.processNumber + " completed at time " + currentTime);
            System.out.println("     Turnaround Time: " + turnaroundTime );
            System.out.println("     Waiting Time: " + waitingTime );
            System.out.println("     Response Time: " + responseTime );
            System.out.println("    '__________________________________'\u001B[0m");

        }

        float averageTurnaroundTime = totalTurnaroundTime / processes.size();
        float averageWaitingTime = totalWaitingTime / processes.size();
        float averageResponseTime = totalResponseTime / processes.size();

        System.out.println("\u001B[33m╭───────────────────────────────────╮");
        System.out.printf("\u001B[33m│ Average Turnaround Time: %.2f units│\n", averageTurnaroundTime);
        System.out.printf("\u001B[33m│ Average Waiting Time: %.2f units   │\n", averageWaitingTime);
        System.out.printf("\u001B[33m│ Average Response Time: %.2f units  │\n", averageResponseTime);
        System.out.println("\u001B[33m╰───────────────────────────────────╯\u001B[0m");
    }
}
