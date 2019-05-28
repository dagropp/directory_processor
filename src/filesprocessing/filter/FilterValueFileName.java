package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

public class FilterValueFileName extends AbstractFilterValue {
    public FilterValueFileName(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.getName().equals(this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }
}


