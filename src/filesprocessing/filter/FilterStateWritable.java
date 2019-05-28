package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

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
