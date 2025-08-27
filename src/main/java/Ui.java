public class Ui {
    private static final String HORIZONTAL_LINE = "________________________________\n";

    public Ui() {
    }

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

    public static void showAddTask(TaskList taskList) {
        String formatted = String.format("OKAY. I HAVE ADDED THIS TASK:\n %s", taskList.getTask(taskList.getSize() - 1).toString());
        System.out.println(formatted);
        System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", taskList.getSize(), taskList.getSize() == 1 ? "TASK" : "TASKS"));
        showHorizontalLine();
    }
    
    public static void showMarkTask(TaskList taskList, int taskNumber) {
        System.out.println(String.format("GOOD JOB! TASK %d IS MARKED AS DONE.\n", taskNumber + 1));
        System.out.println(taskList.getTask(taskNumber));
        showHorizontalLine();
    }

    public static void showUnmarkTask(TaskList taskList, int taskNumber) {
        System.out.println(String.format("TASK %d IS UNMARKED.\n", taskNumber + 1));
        System.out.println(taskList.getTask(taskNumber));
        showHorizontalLine();
        showHorizontalLine();
    }
    
    public static void showDeleteTask(TaskList taskList, Task removedTask, int taskNumber) {
        System.out.println(String.format("TASK %d HAS BEEN REMOVED:\n%s", taskNumber + 1, removedTask));
        System.out.println(String.format("NOW YOU HAVE %d %s IN THE LIST.\n", taskList.getSize(), taskList.getSize() == 1 ? "TASK" : "TASKS"));
        showHorizontalLine();
    }
    
    public static void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showGoodbyeMessage() {
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
    
    public static void showError(String message) {
        System.out.println(message);
        showHorizontalLine();
    }
}
