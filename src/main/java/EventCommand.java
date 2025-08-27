

public class EventCommand extends Command {
    
    public EventCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }
    
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        if (parts.length == 1) {
            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE EVENT TASK!");
        }
        
        taskList.addTask(new Event(input.substring(6), false));
        Ui.showAddTask(taskList);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
