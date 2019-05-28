package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

public class FilterValuePrefix extends AbstractFilterValue {
    public FilterValuePrefix(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.prefix(f, this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }

    private boolean prefix(File file, String input) {
        String fileName = file.getName();
        StringBuilder nameStart = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            nameStart.append(fileName.charAt(i));
        return input.equals(nameStart.toString());
    }
}
