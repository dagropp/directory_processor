package filesprocessing.commandfileparser;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * This class generates a commands ArrayList from the converted lines list.
 */
class CommandsGenerator {
    /* Class members - constant variables */
    private static final String FILTER = "FILTER"; // Filter command header.
    private static final String ORDER = "ORDER"; // Order command header.
    /* Class members - variables */
    private Commands[] commands; // Commands list as ArrayList of Commands objects.

    /* Constructors */

    /**
     * Constructor for CommandsGenerator. Initializes the commands list and extract the command lines to it.
     *
     * @param lines List with command file's lines.
     */
    CommandsGenerator(String[] lines) {
        this.commands = new Commands[lines.length / 4];
        this.setCommands(lines);
    }

    /* Package-private instance methods */

    /**
     * @return Commands list as ArrayList of Commands objects.
     */
    Commands[] getCommands() {
        return this.commands;
    }

    /* Private instance methods */

    /**
     * Sets commands list. Each index that comes after "FILTER" or "ORDER" headers is assigned to the respected field
     * in the Commands object, and if object commands are valid (i.e. not null) adds it to commands list.
     *
     * @param lines List with command file's lines.
     */
    private void setCommands(String[] lines) {
        boolean filterToggle = false; // Toggles between filter and order commands.
        int commandIdx = 0;
        Commands temp = new Commands(); // Initializes temp Commands object.
        for (String line : lines) { // Iterates over the command file's lines.
            // If line's value is FILTER header, re-initializes temp Commands object and sets toggle to filter.
            if (line.equals(FILTER)) {
                temp = new Commands();
                filterToggle = true;
                // If line's value is ORDER header, sets toggle to order.
            } else if (line.equals(ORDER)) {
                filterToggle = false;
                // If toggle is set on FILTER, sets temp filter to line.
            } else if (filterToggle) {
                temp.setFilter(line);
                // If toggle is set on ORDER, sets temp order to line, and if temp is valid, adds it to commands list.
            } else if (!filterToggle) {
                temp.setOrder(line);
                if (temp.validList()) {
                    this.commands[commandIdx] = (temp);
                    commandIdx++;
                }
            }
        }
    }
}
