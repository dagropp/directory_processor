package filesprocessing.commandfileparser;

import filesprocessing.DirectoryProcessor;

/**
 * This class creates Commands object with FILTER and ORDER String commands.
 */
public class Commands {
    /* Class members - variables */
    private String[] filter; // Filter commands.
    private String[] order; // Order commands.

    /* Public instance methods */

    Commands() {
    }

    /**
     * @return Filter commands.
     */
    public String[] getFilter() {
        return this.filter;
    }

    /**
     * @return Order commands.
     */
    public String[] getOrder() {
        return this.order;
    }

    /* Package-private instance methods */

    /**
     * Sets filter commands variable.
     *
     * @param line Filter command line to set.
     */
    public void setFilter(String line) {
        this.filter = this.parseLine(line);
    }

    /**
     * Sets order commands variable.
     *
     * @param line Order command line to set.
     */
    public void setOrder(String line) {
        this.order = this.parseLine(line);
    }

    /**
     * @return True if both filter and order commands are not null.
     */
    boolean validList() {
        return this.filter != null && this.order != null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("*COMMAND FILE CONTENT*\nFILTER: ");
        for (String command : this.getFilter()) {
            String add = command + "  ";
            result.append(add);
        }
        result.append("\nORDER: ");
        for (String command : this.getOrder()) {
            String add = command + "  ";
            result.append(add);
        }
        return result.toString();
    }

    private String[] parseLine(String line) {
        String[] result = new String[this.countSeparators(line)];
        int lastSeparator = 0;
        if (result.length > 1)
            for (int i = 0, j = 0; i < line.length(); i++) {
                if (line.charAt(i) == DirectoryProcessor.SEPARATOR) {
                    result[j] = line.substring(lastSeparator, i);
                    lastSeparator = i + 1;
                    j++;
                } else if (i == line.length() - 1)
                    result[j] = line.substring(lastSeparator);
            }
        else result[0] = line;
        return result;
    }

    private int countSeparators(String line) {
        int separators = 0;
        for (int i = 0; i < line.length(); i++)
            if (line.charAt(i) == DirectoryProcessor.SEPARATOR)
                separators++;
        return separators + 1;
    }
}
