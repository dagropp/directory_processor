package filesprocessing.filter;

import filesprocessing.manager.DirectoryProcessorFactory;
import filesprocessing.commandfileparser.CommandWrapper;

import java.io.File;

public class FilterFactory {
    public static File[] execute(File[] files, CommandWrapper command) {
        FilterWrapper filter = ReformatFilter.execute(command);
        return assignFilter(files, filter).getResults();
    }

    private static Filter assignFilter(File[] files, FilterWrapper filter) {
        switch (filter.getName()) {
            case DirectoryProcessorFactory.FILTER_SIZE_GREATER:
                return new FilterSizeGreater(files, filter);
            case DirectoryProcessorFactory.FILTER_SIZE_SMALLER:
                return new FilterSizeSmaller(files, filter);
            case DirectoryProcessorFactory.FILTER_SIZE_BETWEEN:
                return new FilterSizeBetween(files, filter);
            case DirectoryProcessorFactory.FILTER_VALUE_FILE_NAME:
                return new FilterValueFileName(files, filter);
            case DirectoryProcessorFactory.FILTER_VALUE_CONTAINS:
                return new FilterValueContains(files, filter);
            case DirectoryProcessorFactory.FILTER_VALUE_PREFIX:
                return new FilterValuePrefix(files, filter);
            case DirectoryProcessorFactory.FILTER_VALUE_SUFFIX:
                return new FilterValueSuffix(files, filter);
            case DirectoryProcessorFactory.FILTER_PERMISSION_WRITE:
                return new FilterStateWritable(files, filter);
            case DirectoryProcessorFactory.FILTER_PERMISSION_EXECUTE:
                return new FilterStateExecutable(files, filter);
            case DirectoryProcessorFactory.FILTER_HIDDEN:
                return new FilterStateHidden(files, filter);
            case DirectoryProcessorFactory.FILTER_ALL:
            default:
                return new FilterAll(files, filter);
        }
    }
}
