package commands;

import core.TaskList;
import util.Storage;
import util.TronaldDumpException;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    
    public ExitCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }

    /**
     * Executes the exit command. This command does not perform any specific action.
     * @param input String input from the user representing the command given to the chatbot.
     * @param parts Array of strings obtained by splitting the input string by spaces.
     * @throws TronaldDumpException
     */
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        // Exit command doesn't need to do anything special
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
