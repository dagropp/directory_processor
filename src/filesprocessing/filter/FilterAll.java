package filesprocessing.filter;

import java.io.File;

public class FilterAll extends AbstractFilterNoParams {

    public FilterAll(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return this.isNegation() ? new File[0] : this.getFiles();
    }
}
