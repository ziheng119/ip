package commands;

import core.TaskList;
import util.Storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Initializes and provides Command objects based on user input.
 * When chatbot is constructed, all command objects are created and stored in a map for easy retrieval.
 * These command objects are used to map user input strings to their corresponding command implementations.
 * This approach eliminates the need for multiple if-else or switch-case statements to determine which command to execute.
 */
public class CommandFactory {
    private final Map<String, Command> commands;
    
    public CommandFactory(TaskList taskList, Storage storage) {
        this.commands = new HashMap<>();
        initializeCommands(taskList, storage);
    }

    /**
     * Initializes the command mappings.
     * @param taskList TaskList object that stores the list of tasks.
     * @param storage Storage object that handles loading and saving tasks to persistent storage.
     */
    private void initializeCommands(TaskList taskList, Storage storage) {
        commands.put("bye", new ExitCommand(taskList, storage));
        commands.put("list", new ListCommand(taskList, storage));
        commands.put("mark", new MarkCommand(taskList, storage));
        commands.put("unmark", new UnmarkCommand(taskList, storage));
        commands.put("delete", new DeleteCommand(taskList, storage));
        commands.put("todo", new TodoCommand(taskList, storage));
        commands.put("event", new EventCommand(taskList, storage));
        commands.put("deadline", new DeadlineCommand(taskList, storage));
    }

    /**
     * @param commandType Command type as a string (e.g., "todo", "event", "deadline", "list", "mark", "unmark",
     *                    "delete", "bye").
     * @return Corresponding Command object, or null if the command type is invalid.
     */
    public Command getCommand(String commandType) {
        return commands.get(commandType.toLowerCase());
    }
}
