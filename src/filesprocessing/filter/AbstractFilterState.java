package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This abstract class represents a basic filter command with YES/NO parameter.
 */
public abstract class AbstractFilterState extends AbstractFilterNoParams {
    /* Class members - constant variables */
    private static final String FILTER_YES = "YES"; // The string command meaning true.
    /* Class members - variables */
    private boolean param; // Filter boolean param, based on YES/NO.

    /* Constructors */

    /**
     * Constructor for AbstractFilterState. Calls parent constructor and sets boolean parameter.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    AbstractFilterState(File[] files, FilterWrapper filter) {
        super(files, filter);
        this.param = filter.getParams().get(0).equals(FILTER_YES);
    }

    /* Protected instance methods */

    /**
     * @return True if filter parameter is YES, false if NO.
     */
    protected boolean isParam() {
        return this.param;
    }
}
