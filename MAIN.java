package JobRecruitmentSystem;

import java.util.PriorityQueue;
import java.util.Scanner;

class Patient implements Comparable<Patient> {
    String name, condition, time;
    int priority;

    Patient(String n, int p, String c, String t) {
        name = n; priority = p; condition = c; time = t;
    }

    public int compareTo(Patient other) {
        if (priority != other.priority) return priority - other.priority;
        return time.compareTo(other.time); // earlier time first
    }

    public String toString() {
        return "[P" + priority + "] " + name + " - " + condition + " (" + time + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Patient> er = new PriorityQueue<>();
        int choice;

        do {
            System.out.println("\n--- EMERGENCY ROOM SYSTEM ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Treat Next Patient");
            System.out.println("3. Display Queue");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter priority (1=Critical, 2=Serious, 3=Stable, 4=Minor): ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter condition: ");
                    String condition = sc.nextLine();
                    System.out.print("Enter arrival time (HH:MM): ");
                    String time = sc.nextLine();
                    er.add(new Patient(name, priority, condition, time));
                    System.out.println("Patient added.");
                    break;

                case 2:
                    if (er.isEmpty()) {
                        System.out.println(">>> No patients to treat.");
                    } else {
                        System.out.println("\n>>> Treating patient now...");
                        System.out.println("Treated: " + er.poll());
                    }
                    break;

                case 3:
                    System.out.println("\n=== UPDATED QUEUE ===");
                    if (er.isEmpty()) {
                        System.out.println("No patients waiting.");
                    } else {
                        int i = 1;
                        for (Patient p : er.stream().sorted().toList()) {
                            System.out.println(i + ". " + p);
                            i++;
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
