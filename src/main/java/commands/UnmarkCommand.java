package commands;

import core.TaskList;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to unmark a task as not completed in the TaskList.
 */
public class UnmarkCommand extends Command {

    public UnmarkCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the unmark command to unmark a task as not completed in the TaskList.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
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
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
