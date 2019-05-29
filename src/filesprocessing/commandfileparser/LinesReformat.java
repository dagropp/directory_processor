package filesprocessing.commandfileparser;

import filesprocessing.order.OrderFactory;
import filesprocessing.type2errors.InvalidCommandHeader;

import java.util.ArrayList;

/**
 * This class reformats the lines array, by checking if it contains correct and valid command headers, and by adding
 * default commands to missing lines.
 */
public class LinesReformat {
    /* Class members - constant variables */
    private static final String FILTER_HEADER = "FILTER"; // FILTER command header.
    private static final String ORDER_HEADER = "ORDER"; // ORDER command header.
    private static final int COMMAND_BLOCK_SIZE = 4; // Represents how many lines each command block should occupy.
    private static final int FILTER_HEADER_INDEX = 0; // Index of FILTER header in command block.
    private static final int FILTER_COMMAND_INDEX = 1; // Index of FILTER command in command block.
    private static final int ORDER_HEADER_INDEX = 2; // Index of ORDER header in command block.
    private static final int ORDER_COMMAND_INDEX = 3; // Index of ORDER command in command block.
    /* Class members - variables */
    private int filterCounter = 0; // Represents how many FILTER headers are in the command file.
    private int orderCounter = 0; // Represents how many ORDER headers are in the command file.
    private LineWrapper[] result; // The reformatted lines array.

    private enum LineSymbols {FILTER, ORDER, COMMAND} // Enum with command file optional headers.

    /* Constructors */

    /**
     * Constructor for LinesReformat. Reformats the lines array and assign the result.
     *
     * @param lines The lines array to reformat.
     * @throws InvalidCommandHeader If FILTER/ORDER command headers are not in expected format.
     */
    public LinesReformat(LineWrapper[] lines) throws InvalidCommandHeader {
        ArrayList<LineLegend> linesLegend = setLinesLegend(lines); // Sets list of abstract line representation.
        // If found that FILTER/ORDER headers don't match in amount, throw InvalidCommandHeader Exception.
        if (linesLegend == null)
            throw new InvalidCommandHeader();
        this.result = this.reformatLines(linesLegend); // Reformat result, using the abstract line representation.
        // If found that FILTER/ORDER headers are not placed correctly, throw InvalidCommandHeader Exception.
        if (this.result == null)
            throw new InvalidCommandHeader();
    }

    /**
     * @return The reformatted lines array.
     */
    public LineWrapper[] getResult() {
        return this.result;
    }

    /**
     * Reformats the lines array, using the abstract line representation. Checks if headers are set in expected order.
     *
     * @param linesLegend List with abstract line representation.
     * @return Reformatted lines array. Null if headers are not set in expected order.
     */
    private LineWrapper[] reformatLines(ArrayList<LineLegend> linesLegend) {
        // Assign results array, the size of FILTER commands * lines each command block occupies.
        LineWrapper[] result = new LineWrapper[this.filterCounter * COMMAND_BLOCK_SIZE];
        // Iterates over this array, with each iteration the size of the command block.
        for (int i = 0, j = 0; i < result.length; i += COMMAND_BLOCK_SIZE) {
            // If first line of block is FILTER, and third is ORDER, continue reformatting.
            if (linesLegend.get(j + FILTER_HEADER_INDEX).SYMBOL == LineSymbols.FILTER &&
                    linesLegend.get(j + ORDER_HEADER_INDEX).SYMBOL == LineSymbols.ORDER) {
                // Headers are valid: Assign indexes 0-2 of command block with the formatted lines.
                result[i + FILTER_HEADER_INDEX] =
                        new LineWrapper(linesLegend.get(j + FILTER_HEADER_INDEX).TEXT,
                                linesLegend.get(j + FILTER_HEADER_INDEX).LINE_NUM);
                result[i + FILTER_COMMAND_INDEX] =
                        new LineWrapper(linesLegend.get(j + FILTER_COMMAND_INDEX).TEXT,
                                linesLegend.get(j + FILTER_COMMAND_INDEX).LINE_NUM);
                result[i + ORDER_HEADER_INDEX] =
                        new LineWrapper(linesLegend.get(j + ORDER_HEADER_INDEX).TEXT,
                                linesLegend.get(j + ORDER_HEADER_INDEX).LINE_NUM);
                // If the next line after ORDER is FILTER: adds default order as last command block line.
                if (j + ORDER_COMMAND_INDEX >= linesLegend.size()
                        || linesLegend.get(j + ORDER_COMMAND_INDEX).SYMBOL.equals(LineSymbols.FILTER)) {
                    // Assign index 3 of command block with default order command (abs) and negative line.
                    result[i + ORDER_COMMAND_INDEX] = new LineWrapper(OrderFactory.getDefaultOrder(), -1);
                    j += ORDER_COMMAND_INDEX; // In this case, iterates j+3 as to maintain order.
                } else {
                    // Assign index 3 of command block with with the formatted line.
                    result[i + ORDER_COMMAND_INDEX] =
                            new LineWrapper(linesLegend.get(j + ORDER_COMMAND_INDEX).TEXT,
                                    linesLegend.get(j + ORDER_COMMAND_INDEX).LINE_NUM);
                    j += 4; // In this case, iterates j+3 as to maintain order.
                }

            } else
                return null; // If basic conditions were not met, meaning command headers are not as expected.
        }
        return result; // The formatted lines.
    }

    /**
     * Sets a list with LineLegend abstract line representation. Checks if headers match in amount.
     *
     * @param lines The lines array to abstract.
     * @return List with abstract line representation. Null if headers don't match in amount.
     */
    private ArrayList<LineLegend> setLinesLegend(LineWrapper[] lines) {
        ArrayList<LineLegend> linesLegend = new ArrayList<>(); // Creates new LineLegend list.
        // Iterates over the lines array:
        for (LineWrapper line : lines) {
            // Line equals FILTER header:
            if (line.equals(FILTER_HEADER)) {
                // Symbolizes the line as such, adds its text and line number.
                linesLegend.add(new LineLegend(LineSymbols.FILTER, line.toString(), line.getLineNum()));
                this.filterCounter++; // Appends FILTER headers counter.
                // Line equals ORDER header:
            } else if (line.equals(ORDER_HEADER)) {
                // Symbolizes the line as such, adds its text and line number.
                linesLegend.add(new LineLegend(LineSymbols.ORDER, line.toString(), line.getLineNum()));
                this.orderCounter++; // Appends ORDER headers counter.
                // Line not a header, supposedly a command:
            } else
                // Symbolizes the line as such, adds its text and line number.
                linesLegend.add(new LineLegend(LineSymbols.COMMAND, line.toString(), line.getLineNum()));
        }
        if (this.filterCounter == this.orderCounter)
            return linesLegend; // if headers match in amount return List with abstract line representation.
        return null; // If headers don't match in amount.
    }

    /**
     * This nested class represents an abstract view of line, consisting of type (header/command), text and number.
     */
    private class LineLegend {
        private LineSymbols SYMBOL; // Enum symbol with line type: header (ORDER/FILTER) or actual command.
        private final String TEXT; // Line text.
        private final int LINE_NUM; // Line number.

        /**
         * Constructor for LineLegend nested class, initiates all its members.
         *
         * @param symbol  Enum symbol with line type: header (ORDER/FILTER) or actual command.
         * @param text    Line text.
         * @param lineNum Line number.
         */
        private LineLegend(LineSymbols symbol, String text, int lineNum) {
            this.SYMBOL = symbol;
            this.TEXT = text;
            this.LINE_NUM = lineNum;
        }
    }
}
