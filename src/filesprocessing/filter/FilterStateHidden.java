package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

public class FilterStateHidden extends AbstractFilterState {
    public FilterStateHidden(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.isHidden() ^ (!this.isParam() ^ this.isNegation())
        ).toArray(File[]::new);
    }
}
