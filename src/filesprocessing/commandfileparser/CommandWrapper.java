package filesprocessing.commandfileparser;

import filesprocessing.manager.DirectoryProcessorFactory;

/**
 * This class creates CommandWrapper object with FILTER and ORDER String commands.
 */
public class CommandWrapper {
    /* Class members - variables */
    private String[] filter; // FilterWrapper commands.
    private String[] order; // Order commands.
    private int filterLine;
    private int orderLine;

    /* Public instance methods */

    public CommandWrapper() {
    }

    /**
     * @return FilterWrapper commands.
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

    public int getFilterLine() {
        return this.filterLine;
    }

    public int getOrderLine() {
        return this.orderLine;
    }

    /* Package-private instance methods */

    /**
     * Sets filter commands variable.
     *
     * @param line FilterWrapper command line to set.
     */
    public void setFilter(String line, int lineNum) {
        this.filter = this.parseLine(line);
        this.filterLine = lineNum;
    }

    /**
     * Sets order commands variable.
     *
     * @param line Order command line to set.
     */
    public void setOrder(String line, int lineNum) {
        this.order = this.parseLine(line);
        this.orderLine = lineNum;
    }

    /**
     * @return True if both filter and order commands are not null. -- Am I using this method???
     */
    public boolean validList() {
        return this.filter != null && this.order != null;
    }

    private String[] parseLine(String line) {
        String[] result = new String[this.countSeparators(line)];
        int lastSeparator = 0;
        if (result.length > 1)
            for (int i = 0, j = 0; i < line.length(); i++) {
                if (line.charAt(i) == DirectoryProcessorFactory.SEPARATOR) {
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
            if (line.charAt(i) == DirectoryProcessorFactory.SEPARATOR)
                separators++;
        return separators + 1;
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
}
