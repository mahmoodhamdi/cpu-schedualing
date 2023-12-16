import java.util.Scanner;

public class SchedulerMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\u001B[36m--------------------------------------");
        System.out.println("|         Welcome to the Scheduler   |");
        System.out.println("|                                    |");
        System.out.println("| Choose a scheduling algorithm:     |");
        System.out.println("| 1. First Come First Served (FCFS)  |");
        System.out.println("| 2. Priority preemptive algorithm   |");
        System.out.println("| 3. Non-Preemptive Shortest Job     |");
        System.out.println("|    First (SJF)                     |");
        System.out.println("| 4. Non-Preemptive Priority         |");
        System.out.println("--------------------------------------\u001B[0m");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Fcfc.executeFCFS(scanner);
                break;
            // case 2:
            //     PriorityPreemptive.executePriorityPreemptive(scanner);
            //     break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        scanner.close();
    }
}
