package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all executable files (or its negation).
 */
public class FilterStateExecutable extends AbstractFilterState {
    public FilterStateExecutable(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.canExecute() ^ (!this.isParam() ^ this.isNegation())
        ).toArray(File[]::new);
    }
}
