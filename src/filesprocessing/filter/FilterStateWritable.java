package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all writable files (or its negation).
 */
public class FilterStateWritable extends AbstractFilterState {
    public FilterStateWritable(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.canWrite() ^ (!this.isParam() ^ this.isNegation())
        ).toArray(File[]::new);
    }
}
