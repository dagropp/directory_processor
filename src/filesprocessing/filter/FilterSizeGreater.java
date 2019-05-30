package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files greater than specified size in KB (or its negation).
 */
public class FilterSizeGreater extends AbstractFilterSize {

    /* Constructors */

    /**
     * Constructor for FilterSizeGreater. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterSizeGreater(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects whom size is bigger than specified size, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.bytesToKB(f.length()) > this.getParams().get(0) ^ this.isNegation()
        ).toArray(File[]::new);
    }
}
