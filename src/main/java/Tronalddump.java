import java.util.ArrayList;
import java.util.Scanner;


public class Tronalddump {
    public static void main(String[] args) {
        System.out.println("________________________________\n");
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println("________________________________\n");
        Storage storage = new Storage();
        ArrayList<Task> list = storage.load();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(" ");
                System.out.println("________________________________\n");
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.print("HERE ARE THE TASKS IN YOUR LIST:\n");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%d. %s", i + 1, list.get(i).toString()));
                    }
                    System.out.println("________________________________\n");
                } else if (parts[0].equalsIgnoreCase("mark")) {
                    if (parts.length != 2) {
                        throw new TronaldDumpException("Please specify the task number to mark.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < list.size()) {
                        list.get(taskNumber).markAsDone();
                        System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
                        System.out.println(list.get(taskNumber));
                        System.out.println("________________________________\n");
                    } else {
                        throw new TronaldDumpException("Invalid task number.");
                    }
                } else if (parts[0].equalsIgnoreCase("unmark")) {
                    if (parts.length != 2) {
                        throw new TronaldDumpException("Please specify the task number to unmark.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < list.size()) {
                        list.get(taskNumber).markAsNotDone();
                        System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
                        System.out.println(list.get(taskNumber));
                        System.out.println("________________________________\n");
                    } else {
                        throw new TronaldDumpException("Invalid task number.");
                    }
                } else if (parts.length == 2 && parts[0].equalsIgnoreCase("delete")) {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < list.size()) {
                        Task removedTask = list.remove(taskNumber);
                        System.out.println(String.format("TASK %d HAS BEEN REMOVED:\n%s", taskNumber + 1, removedTask));
                        System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", list.size(), list.size() == 1 ? "TASK" : "TASKS"));
                        System.out.println("________________________________\n");
                    } else {
                        throw new TronaldDumpException("Invalid task number.");
                    }
                } else {
                    if (parts[0].equalsIgnoreCase("todo")) {
                        if (parts.length == 1) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE TODO TASK!");
                        }
                        list.add(new Todo(input.substring(5), false));
                    } else if (parts[0].equalsIgnoreCase("event")) {
                        if (parts.length == 1) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE EVENT TASK!");
                        }
                        list.add(new Event(input.substring(6), false));
                    } else if (parts[0].equalsIgnoreCase("deadline")) {
                        if (parts.length == 1) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE DEADLINE TASK!");
                        }
                        list.add(new Deadline(input.substring(9), false));
                    } else {
                        throw new TronaldDumpException("I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!" +
                                "\nELSE, TRY LIST, MARK, UNMARK, DELETE, OR BYE TO EXIT!");
                    }
                    storage.save(list);
                    String formatted = String.format("OKAY. I HAVE ADDED THIS TASK:\n %s", list.get(list.size() - 1).toString());
                    System.out.println(formatted);
                    System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", list.size(), list.size() == 1 ? "TASK" : "TASKS"));
                    System.out.println("________________________________\n");
                }
                storage.save(list);
            } catch (TronaldDumpException e) {
                System.out.println(e.getMessage());
                System.out.println("________________________________\n");
            }
        }
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
}
