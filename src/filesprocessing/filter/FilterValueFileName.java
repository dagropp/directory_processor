package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files which their names are equal to the specified input
 * (or its negation).
 */
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


