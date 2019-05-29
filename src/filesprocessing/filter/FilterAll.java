package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This class executes a filter command that returns all files (or none, with negation).
 */
public class FilterAll extends AbstractFilterNoParams {
    public FilterAll(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return this.isNegation() ? new File[0] : this.getFiles();
    }
}
