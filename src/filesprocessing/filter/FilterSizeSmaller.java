package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

public class FilterSizeSmaller extends AbstractFilterSize {
    public FilterSizeSmaller(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.bytesToKB(f.length()) < this.getParams().get(0) ^ this.isNegation()
        ).toArray(File[]::new);
    }
}