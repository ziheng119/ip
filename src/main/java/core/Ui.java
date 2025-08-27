package core;

/**
 * Handles user interface, including displaying messages and formatting output.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "________________________________\n";

    public Ui() {
    }

    /**
     * Displays all tasks in the current TaskList.
     * @param taskList Current TaskList to be displayed.
     */
    public static void showAllTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.print("YOU HAVE NO TASKS IN YOUR LIST!\n");
        } else {
            System.out.print("HERE ARE THE TASKS IN YOUR LIST:\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println(String.format("%d. %s", i + 1, taskList.getTask(i).toString()));
            }
        }
        showHorizontalLine();
    }

    /**
     * Displays a message confirming the addition of a new task, along with the total number of tasks.
     * @param taskList TaskList containing the newly added task.
     */
    public static void showAddTask(TaskList taskList) {
        String formatted = String.format("OKAY. I HAVE ADDED THIS TASK:\n %s", taskList.getTask(taskList.getSize() - 1).toString());
        System.out.println(formatted);
        System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", taskList.getSize(), taskList.getSize() == 1 ? "TASK" : "TASKS"));
        showHorizontalLine();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     * @param taskList TaskList containing the task to be marked.
     * @param taskNumber Index of the task to be marked (0-based).
     */
    public static void showMarkTask(TaskList taskList, int taskNumber) {
        System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
        System.out.println(taskList.getTask(taskNumber));
        showHorizontalLine();
    }

    /**
     * Displays a message confirming that a task has been unmarked as not done.
     * @param taskList TaskList containing the task to be unmarked.
     * @param taskNumber Index of the task to be unmarked (0-based).
     */
    public static void showUnmarkTask(TaskList taskList, int taskNumber) {
        System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
        System.out.println(taskList.getTask(taskNumber));
        showHorizontalLine();
    }

    /**
     * Displays a message confirming the removal of a task, along with the total number of remaining tasks.
     * @param taskList TaskList containing the task to be removed.
     * @param removedTask The task that was removed.
     * @param taskNumber Index of the task that was removed (0-based).
     */
    public static void showDeleteTask(TaskList taskList, Task removedTask, int taskNumber) {
        System.out.println(String.format("TASK %d HAS BEEN REMOVED:\n%s", taskNumber + 1, removedTask));
        System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", taskList.getSize(), taskList.getSize() == 1 ? "TASK" : "TASKS"));
        showHorizontalLine();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public static void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a horizontal line for formatting purposes.
     */
    public static void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the goodbye message when the application ends.
     */
    public static void showGoodbyeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println(HORIZONTAL_LINE);

    }

    /**
     * Displays an error message.
     * @param message Error message to be displayed.
     */
    public static void showError(String message) {
        System.out.println(message);
        showHorizontalLine();
    }
}
