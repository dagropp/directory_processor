package filesprocessing.filter;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.CommandWrapper;

import java.io.File;

public class FilterFactory {
    public static File[] execute(File[] files, CommandWrapper command) {
        FilterWrapper filter = new ReformatFilter(command).getFilter();
        switch (filter.getName()) {
            case DirectoryProcessor.FILTER_SIZE_GREATER:
                return new FilterSize(files, filter).greaterThan();
            case DirectoryProcessor.FILTER_SIZE_SMALLER:
                return new FilterSize(files, filter).smallerThan();
            case DirectoryProcessor.FILTER_SIZE_BETWEEN:
                return new FilterSize(files, filter).between();
            case DirectoryProcessor.FILTER_VALUE_FILE_NAME:
            case DirectoryProcessor.FILTER_VALUE_CONTAINS:
            case DirectoryProcessor.FILTER_VALUE_PREFIX:
            case DirectoryProcessor.FILTER_VALUE_SUFFIX:
            case DirectoryProcessor.FILTER_PERMISSION_WRITE:
                return new FilterYesNo(files, filter).writable();
            case DirectoryProcessor.FILTER_PERMISSION_EXECUTE:
                return new FilterYesNo(files, filter).executable();
            case DirectoryProcessor.FILTER_HIDDEN:
                return new FilterYesNo(files, filter).hidden();
            case DirectoryProcessor.FILTER_ALL:
            default:
                return null;
        }
    }
}
