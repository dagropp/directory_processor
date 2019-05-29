package filesprocessing.commandfileparser.commandsgenerator;

/**
 * This class creates a wrapper object with FILTER and ORDER command wrappers.
 */
public class CommandWrapper {
    /* Class members - constant variables */
    private static final char SEPARATOR = '#';
    /* Class members - variables */
    private FilterWrapper filter; // FilterWrapper commands.
    private OrderWrapper order; // Order commands.

    /* Public instance methods */

    /**
     * Sets filter command.
     *
     * @param line    Filter command line to set.
     * @param lineNum Filter line number.
     */
    public void setFilter(String line, int lineNum) {
        // Reformats filter command with dedicated method.
        this.filter = new ReformatFilter(this.parseLine(line), lineNum).getResult();
    }

    /**
     * Sets order command.
     *
     * @param line    Order command line to set.
     * @param lineNum Order line number.
     */
    public void setOrder(String line, int lineNum) {
        // Reformats order command with dedicated method.
        this.order = new ReformatOrder(this.parseLine(line), lineNum).getResult();
    }

    /**
     * @return True if both filter and order commands are not null. -- Am I using this method???
     */
    public boolean validList() {
        return this.filter != null && this.order != null;
    }

    /**
     * @return Filter command.
     */
    public FilterWrapper getFilter() {
        return this.filter;
    }

    /**
     * @return Order command.
     */
    public OrderWrapper getOrder() {
        return this.order;
    }

    /* Private instance methods */

    /**
     * Separates command string to array, using '#' as separator.
     *
     * @param line Command string.
     * @return Separated line array.
     */
    private String[] parseLine(String line) {
        // Creates new array with length based on separators number.
        String[] result = new String[this.countSeparators(line)];
        int lastSeparator = 0; // History of last separator index.
        // If found at least 1 separator:
        if (result.length > 1)
            // Iterates over the characters in the string.
            for (int i = 0, j = 0; i < line.length(); i++) {
                if (line.charAt(i) == SEPARATOR) { // Reached the separator:
                    result[j] = line.substring(lastSeparator, i); // Assigns the characters before it to the array.
                    lastSeparator = i + 1; // Assign last separator index with new index.
                    j++;
                    // Reached the end of the string, assign the characters between last separator and end to array.
                } else if (i == line.length() - 1)
                    result[j] = line.substring(lastSeparator);
            }
        else result[0] = line; // No separators found, assign result with the first (and only) index.
        return result;
    }

    /**
     * Counts '#' separators in the line.
     *
     * @param line Command string.
     * @return Number of separators.
     */
    private int countSeparators(String line) {
        int separators = 0; // Separator counter.
        // Iterates over the characters of the string.
        for (int i = 0; i < line.length(); i++)
            // If reached the separator, appends counter.
            if (line.charAt(i) == SEPARATOR)
                separators++;
        return separators + 1;
    }
}
