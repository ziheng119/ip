package commands;

import core.TaskList;
import core.Todo;
import core.Ui;
import util.Storage;
import util.TronaldDumpException;

public class TodoCommand extends Command {
    
    public TodoCommand(TaskList taskList, Storage storage) {
        super(taskList, storage);
    }
    
    @Override
    public void execute(String input, String[] parts) throws TronaldDumpException {
        if (parts.length == 1) {
            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE TODO TASK!");
        }
        
        taskList.addTask(new Todo(input.substring(5), false));
        Ui.showAddTask(taskList);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
