package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files which their names contain specified input
 * (or its negation).
 */
public class FilterValueContains extends AbstractFilterValue {
    public FilterValueContains(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.contains(f, this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }

    private boolean contains(File file, String input) {
        String fileName = file.getName();
        for (int i = 0, j = input.length(); j <= fileName.length(); i++, j++) {
            StringBuilder subName = new StringBuilder();
            for (int a = i; a < j; a++)
                subName.append(fileName.charAt(a));
            if (input.equals(subName.toString()))
                return true;
        }
        return false;
    }
}
