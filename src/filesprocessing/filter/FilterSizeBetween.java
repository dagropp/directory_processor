package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files in between specified size a and b in KB
 * (or its negation).
 */
public class FilterSizeBetween extends AbstractFilterSize {

    /* Constructors */

    /**
     * Constructor for FilterSizeBetween. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterSizeBetween(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File object in specified size range, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.bytesToKB(f.length()) >= this.getParams().get(0) &&
                        this.bytesToKB(f.length()) <= this.getParams().get(1) ^ this.isNegation()
        ).toArray(File[]::new);
    }
}
