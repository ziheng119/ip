public class Parser {
    public static String[] parse(String input) {
        return input.trim().split("\\s+");
    }
    
    public static boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("bye");
    }
    
    public static boolean isListCommand(String input) {
        return input.equalsIgnoreCase("list");
    }
    
    public static boolean isMarkCommand(String[] parts) {
        return parts[0].equalsIgnoreCase("mark");
    }
    
    public static boolean isUnmarkCommand(String[] parts) {
        return parts[0].equalsIgnoreCase("unmark");
    }
    
    public static boolean isDeleteCommand(String[] parts) {
        return parts[0].equalsIgnoreCase("delete");
    }
    
    public static boolean isTodoCommand(String[] parts) {
        return parts[0].equalsIgnoreCase("todo");
    }
    
    public static boolean isEventCommand(String[] parts) {
        return parts[0].equalsIgnoreCase("event");
    }
    
    public static boolean isDeadlineCommand(String[] parts) {
        return parts[0].equalsIgnoreCase("deadline");
    }
    
    public static int getTaskNumber(String[] parts) throws TronaldDumpException {
        if (parts.length != 2) {
            throw new TronaldDumpException("Please specify the task number.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new TronaldDumpException("Invalid task number format.");
        }
    }
    
    public static String getTaskDescription(String input, String command) throws TronaldDumpException {
        String description = input.substring(command.length()).trim();
        if (description.isEmpty()) {
            throw new TronaldDumpException("I HATE DEMOCRATS! YOU NEED TO GIVE ME A DESCRIPTION FOR THE " + 
                command.toUpperCase() + " TASK!");
        }
        return description;
    }
}
