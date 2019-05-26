package filesprocessing.filter;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.Commands;

public class ValidateFilterCommand {
    private static final String WARNING_MSG = "Warning in line ";
    private static final int COMMAND_INDEX_SHIFT = 2;

    static void validate(Commands command, int index) {
        if (!validateCommand(command)) {
            command.setFilter(DirectoryProcessor.FILTER_ALL);
            System.err.println(WARNING_MSG + (index + COMMAND_INDEX_SHIFT));
        }
    }

    private static boolean validateCommand(Commands command) {
        String[] filter = command.getFilter();
        return false;
    }

    private static boolean inArray(String string) {
        for (String item : DirectoryProcessor.FILTER_COMMANDS)
            if (string.equals(item))
                return true;
        return false;
    }
}
