package filesprocessing.filter;

import filesprocessing.commandfileparser.CommandWrapper;

import java.io.File;

/**
 * This class receives a Command object and filters a files list using the specified command.
 */
public class FilterFactory {
    static final String FILTER_SIZE_GREATER = "greater_than";
    static final String FILTER_SIZE_SMALLER = "smaller_than";
    static final String FILTER_SIZE_BETWEEN = "between";
    static final String FILTER_VALUE_FILE_NAME = "file";
    static final String FILTER_VALUE_CONTAINS = "contains";
    static final String FILTER_VALUE_PREFIX = "prefix";
    static final String FILTER_VALUE_SUFFIX = "suffix";
    static final String FILTER_STATE_WRITE = "writable";
    static final String FILTER_STATE_EXECUTE = "executable";
    static final String FILTER_STATE_HIDDEN = "hidden";
    static final String FILTER_ALL = "all";

    public static File[] execute(File[] files, CommandWrapper command) {
        FilterWrapper filter = ReformatFilter.execute(command);
        return assignFilter(files, filter).getResults();
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
