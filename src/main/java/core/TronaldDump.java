package core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import commands.Command;
import commands.CommandFactory;
import commands.ExitCommand;
import commands.ListCommand;
import util.Parser;
import util.Storage;
import util.TronaldDumpException;

/**
 * Main class for the TronaldDump chatbot application.
 */
public class TronaldDump {
    private Storage storage;
    private TaskList taskList;
    private CommandFactory commandFactory;

    /**
     * Constructor for TronaldDump class.
     * Initializes storage, loads existing tasks, and creates the command factory.
     */
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
    public Command processCommand(String input) throws TronaldDumpException {
        String[] parts = Parser.parse(input);
        Ui.showHorizontalLine();
        if (parts.length == 0) {

            throw new TronaldDumpException(
                    "I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!\n"
                            + "ELSE, TRY LIST, MARK, UNMARK, DELETE, OR BYE TO EXIT!");
        }

        String commandType = parts[0].toLowerCase();
        Command command = commandFactory.getCommand(commandType);

        if (command == null) {
            throw new TronaldDumpException(
                    "I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!\n"
                            + "ELSE, TRY LIST, MARK, UNMARK, DELETE, OR BYE TO EXIT!");
        }

        // Execute the command (including exit commands) to capture their output
        command.execute(input, parts);
        
        if (command.isExit()) {
            return command;
        }

        // Save after any modification (except for list and exit commands)
        if (!(command instanceof ListCommand) && !(command instanceof ExitCommand)) {
            storage.save(taskList.getAllTasks());
        }
        

        assert command != null : "Command should not be null after processing";
        return command;
    }

    public static void main(String[] args) {
        new TronaldDump().run();
    }

    public String getResponse(String input) {
        assert input != null : "Input should not be null in getResponse";
        
        try {
            // Capture the actual output from commands
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            // Process the command (this will print to our captured stream)
            Command command = processCommand(input);
            
            // Restore original System.out
            System.setOut(originalOut);
            
            // Get the captured output
            String capturedOutput = outputStream.toString();
            
            assert capturedOutput != null : "Captured output should not be null";
            
            // If it's an exit command, show goodbye message
            // if (command.isExit()) {
            //     // Call the goodbye message method to capture its output
            //     Ui.showGoodbyeMessage();
                
            //     // Capture the goodbye message output
            //     String goodbyeOutput = outputStream.toString().trim();
                
            //     // Restore original System.out
            //     System.setOut(originalOut);
                
            //     return goodbyeOutput;
            // }
            
            // Return the actual captured output, or a default message if empty
            // if (capturedOutput.isEmpty()) {
            //     return "Command executed successfully!";
            // }
            
            return capturedOutput;
            
        } catch (TronaldDumpException e) {
            return e.getMessage();
        }
    }
}
