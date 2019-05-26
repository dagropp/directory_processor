package filesprocessing.filter;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.Commands;

public class ReformatFilter {
    private Filter[] filters;
    private static final int LENGTH_NO_PARAMS = 1;
    private static final int LENGTH_1_PARAM = 2;
    private static final int LENGTH_2_PARAMS = 3;


    public ReformatFilter(Commands[] commands) {
        this.filters = new Filter[commands.length];
        for (int i = 0; i < commands.length; i++)
            this.filters[i] = this.identifyFilter(commands[i].getFilter());
    }

    public Filter[] getFilters() {
        return this.filters;
    }

    private Filter identifyFilter(String[] filter) {
        switch (filter[0]) {
            case DirectoryProcessor.FILTER_SIZE_GREATER:
                return this.identifier(filter, DirectoryProcessor.FILTER_SIZE_GREATER, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_SIZE_SMALLER:
                return this.identifier(filter, DirectoryProcessor.FILTER_SIZE_SMALLER, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_SIZE_BETWEEN:
                return this.identifier(filter, DirectoryProcessor.FILTER_SIZE_BETWEEN, LENGTH_2_PARAMS);
            case DirectoryProcessor.FILTER_VALUE_FILE_NAME:
                return this.identifier(filter, DirectoryProcessor.FILTER_VALUE_FILE_NAME, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_VALUE_CONTAINS:
                return this.identifier(filter, DirectoryProcessor.FILTER_VALUE_CONTAINS, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_VALUE_PREFIX:
                return this.identifier(filter, DirectoryProcessor.FILTER_VALUE_PREFIX, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_VALUE_SUFFIX:
                return this.identifier(filter, DirectoryProcessor.FILTER_VALUE_SUFFIX, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_PERMISSION_WRITE:
                return this.identifier(filter, DirectoryProcessor.FILTER_PERMISSION_WRITE, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_PERMISSION_EXECUTE:
                return this.identifier(filter, DirectoryProcessor.FILTER_PERMISSION_EXECUTE, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_HIDDEN:
                return this.identifier(filter, DirectoryProcessor.FILTER_HIDDEN, LENGTH_1_PARAM);
            case DirectoryProcessor.FILTER_ALL:
                return this.identifier(filter, DirectoryProcessor.FILTER_ALL, LENGTH_NO_PARAMS);
            default:
                return this.caseDefault();
        }
    }

    private Filter caseDefault() {
        Filter result = new Filter();
        result.setName(DirectoryProcessor.FILTER_ALL);
        result.setWarning();
        return result;
    }


    private Filter identifier(String[] filter, String name, int cmdLength) {
        Filter result = new Filter();
        result.setName(name);
        if (filter.length == cmdLength) {
            for (int i = 1; i < filter.length; i++)
                result.addParam(filter[i]);
            return result;
        } else if (filter.length == (cmdLength + 1) && filter[filter.length - 1].equals(DirectoryProcessor.FILTER_NOT)) {
            for (int i = 1; i < filter.length - 1; i++)
                result.addParam(filter[i]);
            result.setNegation(true);
            return result;
        }
        return this.caseDefault();

    }
}
