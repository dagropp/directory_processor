package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.Arrays;

/**
 * This class executes a filter command that returns all files which their names contain specified input
 * (or its negation).
 */
public class FilterValueContains extends AbstractFilterValue {

    /* Constructors */

    /**
     * Constructor for FilterValueContains. Calls parent constructor that sets files array, filter command, negation
     * and parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public FilterValueContains(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * @return Filtered array that contains all File objects whom name contains specified String, or its negation.
     */
    public File[] getResults() {
        return Arrays.stream(this.getFiles()).filter(
                f -> this.contains(f, this.getParams().get(0)) ^ this.isNegation()
        ).toArray(File[]::new);
    }

    /* Private instance methods */

    /**
     * Check if file name contains input.
     *
     * @param file  File object to check if contains String.
     * @param input String to check if is contained in file name.
     * @return True if file name contains input, false otherwise.
     */
    private boolean contains(File file, String input) {
        String fileName = file.getName(); // Assigns file name to String, excluding path.
        // Iterates over the input file name characters.
        for (int i = 0, j = input.length(); j <= fileName.length(); i++, j++) {
            StringBuilder subName = new StringBuilder(); // Creates empty StringBuilder to append characters to.
            // Iterates the size of the input String and generate sub strings from the file name.
            for (int a = i; a < j; a++)
                subName.append(fileName.charAt(a)); // Appends each character to StringBuilder.
            if (input.equals(subName.toString()))
                return true; // If the sub string matches the input.
        }
        return false; // If no sub strings matched the input.
    }
}
