package filesprocessing.commandfileparser;

/**
 * This class generates a commands ArrayList from the converted lines list.
 */
class CommandsGenerator {
    /* Class members - constant variables */
    private static final String FILTER = "FILTER"; // Filter command header.
    private static final String ORDER = "ORDER"; // Order command header.
    /* Class members - variables */
    private Command[] commands; // Command list as ArrayList of Command objects.

    /* Constructors */

    /**
     * Constructor for CommandsGenerator. Initializes the commands list and extract the command lines to it.
     *
     * @param lines List with command file's lines.
     */
    CommandsGenerator(Line[] lines) {
        this.commands = new Command[lines.length / 4];
        this.setCommands(lines);
    }

    /* Package-private instance methods */

    /**
     * @return Command list as ArrayList of Command objects.
     */
    Command[] getCommands() {
        return this.commands;
    }

    /* Private instance methods */

    /**
     * Sets commands list. Each index that comes after "FILTER" or "ORDER" headers is assigned to the respected field
     * in the Command object, and if object commands are valid (i.e. not null) adds it to commands list.
     *
     * @param lines List with command file's lines.
     */
    private void setCommands(Line[] lines) {
        boolean filterToggle = false; // Toggles between filter and order commands.
        int commandIdx = 0;
        Command temp = new Command(); // Initializes temp Command object.
        for (Line line : lines) { // Iterates over the command file's lines.
            // If line's value is FILTER header, re-initializes temp Command object and sets toggle to filter.
            if (line.equals(FILTER)) {
                temp = new Command();
                filterToggle = true;
                // If line's value is ORDER header, sets toggle to order.
            } else if (line.equals(ORDER)) {
                filterToggle = false;
                // If toggle is set on FILTER, sets temp filter to line.
            } else if (filterToggle) {
                temp.setFilter(line.toString(), line.getLineNum());
                // If toggle is set on ORDER, sets temp order to line, and if temp is valid, adds it to commands list.
            } else if (!filterToggle) {
                temp.setOrder(line.toString(), line.getLineNum());
                if (temp.validList()) {
                    this.commands[commandIdx] = (temp);
                    commandIdx++;
                }
            }
        }
    }
}
