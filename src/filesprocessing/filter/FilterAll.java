package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This class executes a filter command that returns all files (or none, with negation).
 */
public class FilterAll extends AbstractFilterNoParams {

    /* Constructors */

    /**
     * Constructor for FilterAll. Calls parent constructor that sets files array, filter command and negation.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterAll(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return All files in directory, or an empty File array if negation.
     */
    public File[] getResults() {
        return this.isNegation() ? new File[0] : this.getFiles();
    }
}
