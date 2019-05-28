package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

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
