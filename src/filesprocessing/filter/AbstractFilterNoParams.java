package filesprocessing.filter;

import java.io.File;

public abstract class AbstractFilterNoParams implements Filter{
    private File[] files;
    private FilterWrapper filter;
    private boolean negation;

    public AbstractFilterNoParams(File[] files, FilterWrapper filter) {
        this.files = files.clone();
        this.filter = filter;
        this.negation = filter.isNegation();
    }

    public File[] getFiles() {
        return this.files;
    }

    public boolean isNegation() {
        return this.negation;
    }
}
