import java.util.Scanner;


public class Tronalddump {
    public static void main(String[] args) {
        System.out.println("________________________________\n");
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println("________________________________\n");
        Task[] list = new Task[100];
        int index = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            System.out.println("________________________________\n");
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < index; i++) {
                    if (list[i].isDone()) {
                        System.out.println(String.format("%d. [X] %s",i + 1,list[i].getDescription()));
                    } else {
                        System.out.println(String.format("%d. [ ] %s", i + 1, list[i].getDescription()));
                    }
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber < index) {
                    list[taskNumber].markAsDone();
                    System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
                    System.out.println(String.format("[X] %s", list[taskNumber].getDescription()));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber < index) {
                    list[taskNumber].markAsNotDone();
                    System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
                    System.out.println(String.format("[ ] %s", list[taskNumber].getDescription()));
                } else {
                    System.out.println("Invalid task number.");
                }
            }
            else {
                list[index++] = new Task(input);
                String formatted = String.format("YOU SAID: %s", input.toUpperCase());
                System.out.println(formatted);

            }
            System.out.println("________________________________\n");
        }
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
}
