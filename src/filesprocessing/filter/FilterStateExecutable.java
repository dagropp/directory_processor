package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all executable files (or its negation).
 */
public class FilterStateExecutable extends AbstractFilterState {

    /* Constructors */

    /**
     * Constructor for FilterStateExecutable. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterStateExecutable(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects that are executable, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.canExecute() ^ (!this.isParam() ^ this.isNegation())
        ).toArray(File[]::new);
    }
}
