import java.util.ArrayList;
import java.util.Scanner;


public class TronaldDump {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public TronaldDump() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                String[] parts = Parser.parse(input);
                Ui.showHorizontalLine();
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    Ui.showAllTasks(taskList);
                } else if (parts[0].equalsIgnoreCase("mark")) {
                    if (parts.length != 2) {
                        throw new TronaldDumpException("Please specify the task number to mark.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.getSize()) {
                        taskList.markTask(taskNumber);
                        Ui.showMarkTask(taskList, taskNumber);
                    } else {
                        throw new TronaldDumpException("Invalid task number.");
                    }
                } else if (parts[0].equalsIgnoreCase("unmark")) {
                    if (parts.length != 2) {
                        throw new TronaldDumpException("Please specify the task number to unmark.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.getSize()) {
                        taskList.unmarkTask(taskNumber);
                        Ui.showUnmarkTask(taskList, taskNumber);
                    } else {
                        throw new TronaldDumpException("Invalid task number.");
                    }
                } else if (parts.length == 2 && parts[0].equalsIgnoreCase("delete")) {
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.getSize()) {
                        Task removedTask = taskList.removeTask(taskNumber);
                        Ui.showDeleteTask(taskList, removedTask, taskNumber);
                    } else {
                        throw new TronaldDumpException("Invalid task number.");
                    }
                } else {
                    if (parts[0].equalsIgnoreCase("todo")) {
                        if (parts.length == 1) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE TODO TASK!");
                        }
                        taskList.addTask(new Todo(input.substring(5), false));
                        Ui.showAddTask(taskList);
                    } else if (parts[0].equalsIgnoreCase("event")) {
                        if (parts.length == 1) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE EVENT TASK!");
                        }
                        try {
                            taskList.addTask(new Event(input.substring(6), false));
                            Ui.showAddTask(taskList);
                        } catch (TronaldDumpException e) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE EVENT TASK!");
                        }
                    } else if (parts[0].equalsIgnoreCase("deadline")) {
                        if (parts.length == 1) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE DEADLINE TASK!");
                        }
                        try {
                            taskList.addTask(new Deadline(input.substring(9), false));
                            Ui.showAddTask(taskList);
                        } catch (TronaldDumpException e) {
                            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE DEADLINE TASK!");
                        }
                    } else {
                        throw new TronaldDumpException("I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!" +
                                "\nELSE, TRY LIST, MARK, UNMARK, DELETE, OR BYE TO EXIT!");
                    }
                    storage.save(taskList.getAllTasks());
                }
            } catch (TronaldDumpException e) {
                System.out.println(e.getMessage());
                Ui.showHorizontalLine();
            }
        }
        Ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new TronaldDump().run();
    }
}
