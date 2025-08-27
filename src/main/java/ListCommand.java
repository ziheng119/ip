

public class ListCommand extends Command {
    
    public ListCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }
    
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        Ui.showAllTasks(taskList);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
