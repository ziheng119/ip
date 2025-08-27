public class DeadlineCommand extends Command {
    
    public DeadlineCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }
    
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        if (parts.length == 1) {
            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE DEADLINE TASK!");
        }
        
        taskList.addTask(new Deadline(input.substring(9), false));
        Ui.showAddTask(taskList);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
