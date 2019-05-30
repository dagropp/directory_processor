package filesprocessing.commandfileparser.commandsgenerator;

import filesprocessing.commandfileparser.LineWrapper;

/**
 * This class generates a CommandWrapper array from the reformatted lines list.
 */
public class CommandsGenerator {
    /* Class members - constant variables */
    private static final int COMMAND_BLOCK_SIZE = 4; // Represents how many lines each command block should occupy.
    private static final int FILTER_COMMAND_INDEX = 1; // Index of FILTER command in command block.
    private static final int ORDER_COMMAND_INDEX = 3; // Index of ORDER command in command block.
    /* Class members - variables */
    private CommandWrapper[] commands; // Commands objects array.

    /* Constructors */

    /**
     * Constructor for CommandsGenerator. Initializes the commands array and extract the command lines to it.
     *
     * @param lines The lines array to extract commands from.
     */
    public CommandsGenerator(LineWrapper[] lines) {
        // Sets new array the size of total command blocks.
        this.commands = new CommandWrapper[lines.length / COMMAND_BLOCK_SIZE];
        this.setCommands(lines); // Sets the commands in the array.
    }

    /* Public instance methods */

    /**
     * @return Commands objects array.
     */
    public CommandWrapper[] getCommands() {
        return this.commands;
    }

    /* Private instance methods */

    /**
     * Sets commands array. Each index that comes after "FILTER" or "ORDER" headers is assigned to the respected field
     * in the CommandWrapper object, and adds it to commands list.
     *
     * @param lines The lines array to extract commands from.
     */
    private void setCommands(LineWrapper[] lines) {
        CommandWrapper temp; // Temp CommandWrapper object.
        // Iterates over the lines, with each iteration the size of the command block.
        for (int i = 0, j = 0; i < lines.length; i += COMMAND_BLOCK_SIZE, j++) {
            temp = new CommandWrapper(); // Initialises temp object.
            LineWrapper filterLine = lines[i + FILTER_COMMAND_INDEX]; // Fetches filter command line.
            LineWrapper orderLine = lines[i + ORDER_COMMAND_INDEX]; // Fetches order command line.
            temp.setFilter(filterLine.toString(), filterLine.getLineNum()); // Sets filter and line to temp object.
            temp.setOrder(orderLine.toString(), orderLine.getLineNum()); // Sets order and line to temp object.
            this.commands[j] = temp; // Assigns temp object to the commands array and iterates again.
        }
    }
}
