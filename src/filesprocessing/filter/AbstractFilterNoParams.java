package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This abstract class represents a basic filter command with no parameters.
 */
public abstract class AbstractFilterNoParams implements Filter {
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
