package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all writable files (or its negation).
 */
public class FilterStateWritable extends AbstractFilterState {

    /* Constructors */

    /**
     * Constructor for FilterStateWritable. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterStateWritable(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects that are writable, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.canWrite() ^ (!this.isParam() ^ this.isNegation())
        ).toArray(File[]::new);
    }
}
