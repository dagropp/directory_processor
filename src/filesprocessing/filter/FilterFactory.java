package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.CommandWrapper;
import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This class receives a Command object and filters a files list using the specified command.
 */
public class FilterFactory {
    /* Class members - constant variables */
    // String representation of each filter command:
    private static final String FILTER_SIZE_GREATER = "greater_than";
    private static final String FILTER_SIZE_SMALLER = "smaller_than";
    private static final String FILTER_SIZE_BETWEEN = "between";
    private static final String FILTER_VALUE_FILE_NAME = "file";
    private static final String FILTER_VALUE_CONTAINS = "contains";
    private static final String FILTER_VALUE_PREFIX = "prefix";
    private static final String FILTER_VALUE_SUFFIX = "suffix";
    private static final String FILTER_STATE_WRITE = "writable";
    private static final String FILTER_STATE_EXECUTE = "executable";
    private static final String FILTER_STATE_HIDDEN = "hidden";
    private static final String FILTER_ALL = "all";

    /* Public static methods */

    /**
     * Executes the filter method based on given command.
     *
     * @param files   File array to filter.
     * @param command Filter command.
     * @return A filtered File array by specified filter command.
     */
    public static File[] execute(File[] files, CommandWrapper command) {
        return assignFilter(files, command.getFilter()).getResults();
    }

    /**
     * @return Default filter command (all).
     */
    public static String getDefaultFilter() {
        return FILTER_ALL;
    }

    /**
     * @param params How many params are expected in filter.
     * @return Array of all possible size filter commands, based on param number.
     */
    public static String[] getSizeFilters(int params) {
        return params == 1
                ? new String[]{FILTER_SIZE_GREATER, FILTER_SIZE_SMALLER}
                : new String[]{FILTER_SIZE_BETWEEN};
    }

    /**
     * @return Array of all possible value filter commands.
     */
    public static String[] getValueFilters() {
        return new String[]{FILTER_VALUE_FILE_NAME, FILTER_VALUE_CONTAINS, FILTER_VALUE_PREFIX, FILTER_VALUE_SUFFIX};
    }

    /**
     * @return Array of all possible state filter commands.
     */
    public static String[] getStateFilters() {
        return new String[]{FILTER_STATE_WRITE, FILTER_STATE_EXECUTE, FILTER_STATE_HIDDEN};
    }

    /**
     * Assigns the filter command based on the given command.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     * @return A filtered File array by specified filter command.
     */
    private static Filter assignFilter(File[] files, FilterWrapper filter) {
        switch (filter.getName()) {
            case FILTER_SIZE_GREATER:
                return new FilterSizeGreater(files, filter);
            case FILTER_SIZE_SMALLER:
                return new FilterSizeSmaller(files, filter);
            case FILTER_SIZE_BETWEEN:
                return new FilterSizeBetween(files, filter);
            case FILTER_VALUE_FILE_NAME:
                return new FilterValueFileName(files, filter);
            case FILTER_VALUE_CONTAINS:
                return new FilterValueContains(files, filter);
            case FILTER_VALUE_PREFIX:
                return new FilterValuePrefix(files, filter);
            case FILTER_VALUE_SUFFIX:
                return new FilterValueSuffix(files, filter);
            case FILTER_STATE_WRITE:
                return new FilterStateWritable(files, filter);
            case FILTER_STATE_EXECUTE:
                return new FilterStateExecutable(files, filter);
            case FILTER_STATE_HIDDEN:
                return new FilterStateHidden(files, filter);
            case FILTER_ALL:
            default:
                return new FilterAll(files, filter);
        }
    }
}
