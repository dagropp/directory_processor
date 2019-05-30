package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all hidden files (or its negation).
 */
public class FilterStateHidden extends AbstractFilterState {

    /* Constructors */

    /**
     * Constructor for FilterStateHidden. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterStateHidden(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects that are hidden, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.isHidden() ^ (!this.isParam() ^ this.isNegation())
        ).toArray(File[]::new);
    }
}
