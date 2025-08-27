

public abstract class Command {
    protected TaskList taskList;
    protected Storage storage;
    
    public Command(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }
    
    public abstract void execute(String input, String[] parts) throws TronaldDumpException;
    
    public abstract boolean isExit();
}
