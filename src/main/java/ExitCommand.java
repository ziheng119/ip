public class ExitCommand extends Command {
    
    public ExitCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }
    
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        // Exit command doesn't need to do anything special
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
