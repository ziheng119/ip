
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands;
    
    public CommandFactory(TaskList taskList, Storage storage) {
        this.commands = new HashMap<>();
        initializeCommands(taskList, storage);
    }
    
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
    
    public Command getCommand(String commandType) {
        return commands.get(commandType.toLowerCase());
    }
    
    public boolean isValidCommand(String commandType) {
        return commands.containsKey(commandType.toLowerCase());
    }
}
