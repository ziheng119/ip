package core;

import commands.Command;
import commands.CommandFactory;
import commands.ExitCommand;
import commands.ListCommand;
import util.Parser;
import util.Storage;
import util.TronaldDumpException;

import java.util.Scanner;

public class TronaldDump {
    private Storage storage;
    private TaskList taskList;
    private CommandFactory commandFactory;

    public TronaldDump() {
        this.storage = new Storage();
        this.taskList = storage.load();
        this.commandFactory = new CommandFactory(taskList, storage);
    }

    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                Command command = processCommand(input);
                if (command.isExit()) {
                    break;
                }
            } catch (TronaldDumpException e) {
                Ui.showError(e.getMessage());
            }
        }
        
        Ui.showGoodbyeMessage();
        scanner.close();
    }
    
    private Command processCommand(String input) throws TronaldDumpException {
        String[] parts = Parser.parse(input);
        if (parts.length == 0) {
            throw new TronaldDumpException("I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!\n" +
                    "ELSE, TRY LIST, MARK, UNMARK, DELETE, OR BYE TO EXIT!");
        }
        
        String commandType = parts[0].toLowerCase();
        Command command = commandFactory.getCommand(commandType);
        
        if (command == null) {
            throw new TronaldDumpException("I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!\n" +
                    "ELSE, TRY LIST, MARK, UNMARK, DELETE, OR BYE TO EXIT!");
        }
        
        if (command.isExit()) {
            return command;
        }
        
        Ui.showHorizontalLine();
        command.execute(input, parts);
        
        // Save after any modification (except for list and exit commands)
        if (!(command instanceof ListCommand) && !(command instanceof ExitCommand)) {
            storage.save(taskList.getAllTasks());
        }
        return command;
    }

    public static void main(String[] args) {
        new TronaldDump().run();
    }
}
