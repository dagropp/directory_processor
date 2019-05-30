package filesprocessing.commandfileparser;

import filesprocessing.order.OrderFactory;
import filesprocessing.type2errors.InvalidCommandHeader;

import java.util.ArrayList;

/**
 * This class reformats the lines array, by checking if it contains correct and valid command headers, and by adding
 * default commands to missing lines.
 */
public class ReformatLines {
    /* Class members - constant variables */
    private static final String FILTER_HEADER = "FILTER"; // FILTER command header.
    private static final String ORDER_HEADER = "ORDER"; // ORDER command header.
    private static final int COMMAND_BLOCK_SIZE = 4; // Represents how many lines each command block should occupy.
    private static final int FILTER_HEADER_INDEX = 0; // Index of FILTER header in command block.
    private static final int FILTER_COMMAND_INDEX = 1; // Index of FILTER command in command block.
    private static final int ORDER_HEADER_INDEX = 2; // Index of ORDER header in command block.
    private static final int ORDER_COMMAND_INDEX = 3; // Index of ORDER command in command block.
    private static final int DEFAULT_LINE_NUM = -1; // Line number for default order lines.
    /* Class members - variables */
    private LineWrapper[] result; // The reformatted lines array.

    /* Constructors */

    /**
     * Constructor for ReformatLines. Reformats the lines array and assign the result.
     *
     * @param lines The lines array to reformat.
     * @throws InvalidCommandHeader If FILTER/ORDER command headers are not in expected format.
     */
    public ReformatLines(LineWrapper[] lines) throws InvalidCommandHeader {
        this.result = this.reformatLines(lines); // Reformat result, using the abstract line representation.
        // If found that FILTER/ORDER headers are not placed correctly, throw InvalidCommandHeader Exception.
        if (this.result == null)
            throw new InvalidCommandHeader();
    }

    /* Public instance methods */

    /**
     * @return The reformatted lines array.
     */
    public LineWrapper[] getResult() {
        return this.result;
    }

    /* Private instance methods */

    /**
     * Reformats the lines array. Checks if headers are set in expected order.
     *
     * @param lines The lines array to reformat.
     * @return Reformatted lines array. Null if headers are not set in expected order or irregular lines number.
     */
    private LineWrapper[] reformatLines(LineWrapper[] lines) {
        ArrayList<LineWrapper> temp = new ArrayList<>(); // Assign temp list to add formatted lines.
        // Iterates over the lines array.
        for (int i = 0; i < lines.length - ORDER_HEADER_INDEX; ) {
            // If first line of block is FILTER, and third is ORDER, continue reformatting (validating headers).
            if (lines[i + FILTER_HEADER_INDEX].equals(FILTER_HEADER) &&
                    lines[i + ORDER_HEADER_INDEX].equals(ORDER_HEADER)) {
                // Headers are valid: Add to list indexes 0-2 of command block with the formatted lines.
                temp.add(new LineWrapper(lines[i + FILTER_HEADER_INDEX].toString(),
                        lines[i + FILTER_HEADER_INDEX].getLineNum()));
                temp.add(new LineWrapper(lines[i + FILTER_COMMAND_INDEX].toString(),
                        lines[i + FILTER_COMMAND_INDEX].getLineNum()));
                temp.add(new LineWrapper(lines[i + ORDER_HEADER_INDEX].toString(),
                        lines[i + ORDER_HEADER_INDEX].getLineNum()));
                // If the next line after ORDER is FILTER: adds default order as last command block line.
                if (i + ORDER_COMMAND_INDEX >= lines.length
                        || lines[i + ORDER_COMMAND_INDEX].equals(FILTER_HEADER)) {
                    // Add to list index 3 of command block with default order command (abs) and negative line.
                    temp.add(new LineWrapper(OrderFactory.getDefaultOrder(), DEFAULT_LINE_NUM));
                    i += ORDER_COMMAND_INDEX; // In this case, iterates i + 3 to maintain order.
                } else {
                    // Add to list index 3 of command block with with the formatted line.
                    temp.add(new LineWrapper(lines[i + ORDER_COMMAND_INDEX].toString(),
                            lines[i + ORDER_COMMAND_INDEX].getLineNum()));
                    i += COMMAND_BLOCK_SIZE; // In this case, iterates i + 4 to maintain order.
                }
            } else
                return null; // If basic conditions were not met, meaning command headers are not as expected.
            if (i >= lines.length - ORDER_HEADER_INDEX) // If iteration condition will be met in the next iteration:
                if (i != lines.length) // Check if the command block holds more indexes, which it shouldn't.
                    return null; // If command blocks has invalid headers or irregular line number.
        }
        LineWrapper[] result = new LineWrapper[temp.size()]; // Assign results array, the size of temp list.
        return temp.toArray(result); // The formatted lines converted to the array.
    }
}
