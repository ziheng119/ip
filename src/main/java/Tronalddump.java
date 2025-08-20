import java.util.Scanner;


public class Tronalddump {
    public static void main(String[] args) {
        System.out.println("________________________________\n");
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println("________________________________\n");
        Task[] list = new Task[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            System.out.println("________________________________\n");
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.print("HERE ARE THE TASKS IN YOUR LIST:\n");
                for (int i = 0; i < index; i++) {
                    System.out.println(String.format("%d. %s",i + 1, list[i].toString()));
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber < index) {
                    list[taskNumber].markAsDone();
                    System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
                    System.out.println(list[taskNumber]);
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (parts.length == 2 && parts[0].equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber >= 0 && taskNumber < index) {
                    list[taskNumber].markAsNotDone();
                    System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
                    System.out.println(list[taskNumber]);
                } else {
                    System.out.println("Invalid task number.");
                }
            }
            else {
                if (parts[0].equals("todo")) {
                    list[index++] = new Todo(input.substring(5));
                } else if (parts[0].equals("event")) {
                    list[index++] = new Event(input.substring(6));
                } else if (parts[0].equals("deadline")) {
                    list[index++] = new Deadline(input.substring(9));
                } else {
                    list[index++] = new Task(input);
                }
                String formatted = String.format("OKAY. I HAVE ADDED THIS TASK:\n %s", list[index - 1].toString());
                System.out.println(formatted);
                System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", index, index == 1 ? "TASK" : "TASKS"));
            }
            System.out.println("________________________________\n");
        }
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
}
