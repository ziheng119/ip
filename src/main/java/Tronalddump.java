import java.util.ArrayList;
import java.util.Scanner;


public class Tronalddump {
    public static void main(String[] args) {
        System.out.println("________________________________\n");
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println("________________________________\n");
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            System.out.println("________________________________\n");
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.print("HERE ARE THE TASKS IN YOUR LIST:\n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s",i + 1, list.get(i).toString()));
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber <= list.size()) {
                    list.get(taskNumber).markAsDone();
                    System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
                    System.out.println(list.get(taskNumber));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber <= list.size()) {
                    list.get(taskNumber).markAsNotDone();
                    System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
                    System.out.println(list.get(taskNumber));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("delete")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber <= list.size()) {
                    Task removedTask = list.remove(taskNumber);
                    System.out.println(String.format("TASK %d HAS BEEN REMOVED:\n%s", taskNumber + 1, removedTask));
                    System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", list.size(), list.size() == 1 ? "TASK" : "TASKS"));
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                if (parts[0].equalsIgnoreCase("todo")) {
                    if (parts.length == 1) {
                        System.out.println("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE TODO TASK!");
                        System.out.println("________________________________\n");
                        continue;
                    }
                    list.add(new Todo(input.substring(5)));
                } else if (parts[0].equalsIgnoreCase("event")) {
                    if (parts.length == 1) {
                        System.out.println("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE EVENT TASK!");
                        System.out.println("________________________________\n");
                        continue;
                    }
                    list.add(new Event(input.substring(6)));
                } else if (parts[0].equalsIgnoreCase("deadline")) {
                    if (parts.length == 1) {
                        System.out.println("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE DEADLINE TASK!");
                        System.out.println("________________________________\n");
                        continue;
                    }
                    list.add(new Deadline(input.substring(9)));
                } else {
                    System.out.println("I HATE DEMOCRATS! I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!");
                    System.out.println("________________________________\n");
                    continue;
                }
                String formatted = String.format("OKAY. I HAVE ADDED THIS TASK:\n %s", list.get(list.size() - 1).toString());
                System.out.println(formatted);
                System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", list.size(), list.size() == 1 ? "TASK" : "TASKS"));
            }
            System.out.println("________________________________\n");
        }
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
}
