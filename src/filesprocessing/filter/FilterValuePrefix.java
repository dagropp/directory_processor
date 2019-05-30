package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files which their names prefix equals the specified input
 * (or its negation).
 */
public class FilterValuePrefix extends AbstractFilterValue {

    /* Constructors */

    /**
     * Constructor for FilterValuePrefix. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterValuePrefix(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects whom name begins with specified String, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.prefix(f, this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }

    /* Private instance methods */

    /**
     * Check if file name begins with input.
     *
     * @param file  File object to check if begins with String.
     * @param input String to check if is the beginning of file name.
     * @return True if file name begins with input, false otherwise.
     */
    private boolean prefix(File file, String input) {
        String fileName = file.getName(); // Assigns file name to String, excluding path.
        StringBuilder nameStart = new StringBuilder(); // Creates empty StringBuilder to append characters to.
        // Iterates the size of the input String and generate sub string from the file name with this length.
        for (int i = 0; i < input.length(); i++)
            nameStart.append(fileName.charAt(i)); // Appends each character to StringBuilder.
        return input.equals(nameStart.toString()); // Equals the sub-string to the input.
    }
}
