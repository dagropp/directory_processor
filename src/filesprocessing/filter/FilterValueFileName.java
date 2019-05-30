package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files which their names are equal to the specified input
 * (or its negation).
 */
public class FilterValueFileName extends AbstractFilterValue {

    /* Constructors */

    /**
     * Constructor for FilterValueFileName. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterValueFileName(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects whom name is equal to specified String, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.getName().equals(this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }
}


