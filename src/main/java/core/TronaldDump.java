package core;

import commands.Command;
import commands.CommandFactory;
import commands.ExitCommand;
import commands.ListCommand;
import util.Parser;
import util.Storage;
import util.TronaldDumpException;

import java.util.Scanner;

/**
 * Main class for the TronaldDump chatbot application.
 */
public class TronaldDump {
    private Storage storage;
    private TaskList taskList;
    private CommandFactory commandFactory;

    public TronaldDump() {
        this.storage = new Storage();
        this.taskList = storage.load();
        this.commandFactory = new CommandFactory(taskList, storage);
    }

    /**
     * Runs the TronaldDump application.
     */
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

    /**
     * Processes the user input and returns the corresponding Command object.
     * Valid user inputs are "todo", "event", "deadline", "list", "mark", "unmark", "delete", and "bye".
     * If the input is invalid, a TronaldDumpException is thrown with an appropriate error message.
     * @param input String input from the user representing the commands given to the chatbot.
     * @return Command object corresponding to the user input.
     * @throws TronaldDumpException
     */
    private Command processCommand(String input) throws TronaldDumpException {
        String[] parts = Parser.parse(input);
        Ui.showHorizontalLine();
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
