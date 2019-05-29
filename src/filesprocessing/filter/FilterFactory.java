package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.CommandWrapper;
import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This class receives a Command object and filters a files list using the specified command.
 */
public class FilterFactory {
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

    public static File[] execute(File[] files, CommandWrapper command) {
        return assignFilter(files, command.getFilter()).getResults();
    }

    public static String getDefaultFilter() {
        return FILTER_ALL;
    }

    public static String[] getSizeFilters(int params) {
        return params == 1
                ? new String[]{FILTER_SIZE_GREATER, FILTER_SIZE_SMALLER}
                : new String[]{FILTER_SIZE_BETWEEN};
    }

    public static String[] getValueFilters() {
        return new String[]{FILTER_VALUE_FILE_NAME, FILTER_VALUE_CONTAINS, FILTER_VALUE_PREFIX, FILTER_VALUE_SUFFIX};
    }

    public static String[] getStateFilters() {
        return new String[]{FILTER_STATE_WRITE, FILTER_STATE_EXECUTE, FILTER_STATE_HIDDEN};
    }

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
