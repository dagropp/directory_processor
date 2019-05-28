package filesprocessing.commandfileparser;

/**
 * This class generates a commands ArrayList from the converted lines list.
 */
public class CommandsGenerator {
    /* Class members - constant variables */
    private static final String FILTER = "FILTER"; // FilterWrapper command header.
    private static final String ORDER = "ORDER"; // Order command header.
    /* Class members - variables */
    private CommandWrapper[] commands; // CommandWrapper list as ArrayList of CommandWrapper objects.

    /* Constructors */

    /**
     * Constructor for CommandsGenerator. Initializes the commands list and extract the command lines to it.
     *
     * @param lines List with command file's lines.
     */
    public CommandsGenerator(LineWrapper[] lines) {
        this.commands = new CommandWrapper[lines.length / 4];
        this.setCommands(lines);
    }

    /* Package-private instance methods */

    /**
     * @return CommandWrapper list as ArrayList of CommandWrapper objects.
     */
    public CommandWrapper[] getCommands() {
        return this.commands;
    }

    /* Private instance methods */

    /**
     * Sets commands list. Each index that comes after "FILTER" or "ORDER" headers is assigned to the respected field
     * in the CommandWrapper object, and if object commands are valid (i.e. not null) adds it to commands list.
     *
     * @param lines List with command file's lines.
     */
    private void setCommands(LineWrapper[] lines) {
        boolean filterToggle = false; // Toggles between filter and order commands.
        int commandIdx = 0;
        CommandWrapper temp = new CommandWrapper(); // Initializes temp CommandWrapper object.
        for (LineWrapper line : lines) { // Iterates over the command file's lines.
            // If line's value is FILTER header, re-initializes temp CommandWrapper object and sets toggle to filter.
            if (line.equals(FILTER)) {
                temp = new CommandWrapper();
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
