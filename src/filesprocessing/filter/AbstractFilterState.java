package filesprocessing.filter;

import filesprocessing.manager.DirectoryProcessorFactory;

import java.io.File;

public abstract class AbstractFilterState extends AbstractFilterNoParams {
    private boolean param;

    AbstractFilterState(File[] files, FilterWrapper filter) {
        super(files, filter);
        this.param = filter.getParams().get(0).equals(DirectoryProcessorFactory.FILTER_YES);
    }

    protected boolean isParam() {
        return this.param;
    }
}
