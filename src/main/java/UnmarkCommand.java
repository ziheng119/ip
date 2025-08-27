

public class UnmarkCommand extends Command {
    
    public UnmarkCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }
    
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
