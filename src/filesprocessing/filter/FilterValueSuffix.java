package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files which their names suffix equals the specified input
 * (or its negation).
 */
public class FilterValueSuffix extends AbstractFilterValue {
    public FilterValueSuffix(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.suffix(f, this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }

    private boolean suffix(File file, String input) {
        String fileName = file.getName();
        StringBuilder nameEnd = new StringBuilder();
        for (int i = fileName.length() - input.length(); i < fileName.length(); i++)
            nameEnd.append(fileName.charAt(i));
        return input.equals(nameEnd.toString());
    }
}
