package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This abstract class represents a basic filter command with YES/NO parameter.
 */
public abstract class AbstractFilterState extends AbstractFilterNoParams {
    private static final String FILTER_YES = "YES";
    private boolean param;

    AbstractFilterState(File[] files, FilterWrapper filter) {
        super(files, filter);
        this.param = filter.getParams().get(0).equals(FILTER_YES);
    }

    protected boolean isParam() {
        return this.param;
    }
}
