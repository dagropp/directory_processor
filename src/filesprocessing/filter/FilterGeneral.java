package filesprocessing.filter;

import java.io.File;

public abstract class FilterGeneral {
    private File[] files;
    private boolean negation;

    FilterGeneral(File[] files, FilterWrapper filter) {
        this.files = files.clone();
        this.negation = filter.getNegation();
    }

    protected File[] getFiles() {
        return this.files;
    }

    protected boolean isNegation() {
        return this.negation;
    }
}
