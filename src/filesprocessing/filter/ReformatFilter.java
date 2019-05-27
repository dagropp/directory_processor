package filesprocessing.filter;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.Command;

public class ReformatFilter {
    private Filter filter;
    private static final int LENGTH_NO_PARAMS = 1;
    private static final int LENGTH_1_PARAM = 2;
    private static final int LENGTH_2_PARAMS = 3;


    public ReformatFilter(Command command) {
        this.filter = this.identifyFilter(command.getFilter(), command.getFilterLine());
    }

    public Filter getFilter() {
        return this.filter;
    }

    private Filter identifyFilter(String[] filter, int lineNum) {
        switch (filter[0]) {
            case DirectoryProcessor.FILTER_SIZE_GREATER:
                return this.filterNum(filter, DirectoryProcessor.FILTER_SIZE_GREATER, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessor.FILTER_SIZE_SMALLER:
                return this.filterNum(filter, DirectoryProcessor.FILTER_SIZE_SMALLER, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessor.FILTER_SIZE_BETWEEN:
                return this.filterNum(filter, DirectoryProcessor.FILTER_SIZE_BETWEEN, LENGTH_2_PARAMS, lineNum);
            case DirectoryProcessor.FILTER_VALUE_FILE_NAME:
                return this.filterGeneral(filter, DirectoryProcessor.FILTER_VALUE_FILE_NAME, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessor.FILTER_VALUE_CONTAINS:
                return this.filterGeneral(filter, DirectoryProcessor.FILTER_VALUE_CONTAINS, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessor.FILTER_VALUE_PREFIX:
                return this.filterGeneral(filter, DirectoryProcessor.FILTER_VALUE_PREFIX, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessor.FILTER_VALUE_SUFFIX:
                return this.filterGeneral(filter, DirectoryProcessor.FILTER_VALUE_SUFFIX, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessor.FILTER_PERMISSION_WRITE:
                return this.filterYesNo(filter, DirectoryProcessor.FILTER_PERMISSION_WRITE, lineNum);
            case DirectoryProcessor.FILTER_PERMISSION_EXECUTE:
                return this.filterYesNo(filter, DirectoryProcessor.FILTER_PERMISSION_EXECUTE, lineNum);
            case DirectoryProcessor.FILTER_HIDDEN:
                return this.filterYesNo(filter, DirectoryProcessor.FILTER_HIDDEN, lineNum);
            case DirectoryProcessor.FILTER_ALL:
                return this.filterGeneral(filter, DirectoryProcessor.FILTER_ALL, LENGTH_NO_PARAMS, lineNum);
            default:
                return this.filterDefault(lineNum);
        }
    }

    private Filter filterDefault(int lineNum) {
        Filter result = new Filter(lineNum);
        result.setName(DirectoryProcessor.FILTER_ALL);
        result.setWarning();
        return result;
    }


    private Filter filterGeneral(String[] filter, String name, int cmdLength, int lineNum) {
        Filter result = new Filter(lineNum);
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
        return this.filterDefault(lineNum);
    }

    private Filter filterNum(String[] filter, String name, int cmdLength, int lineNum) {
        Filter result = this.filterGeneral(filter, name, cmdLength, lineNum);
        if (!result.getName().equals(name))
            return result;
        double num;
        for (String param : result.getParams())
            try {
                num = Double.parseDouble(param);
                if (num < 0)
                    return this.filterDefault(lineNum);
            } catch (NumberFormatException e) {
                return this.filterDefault(lineNum);
            }
        return result;
    }

    private Filter filterYesNo(String[] filter, String name, int lineNum) {
        Filter result = this.filterGeneral(filter, name, LENGTH_1_PARAM, lineNum);
        if (!result.getName().equals(name))
            return result;
        if (result.getParams().length > 1 || result.getNegation())
            return this.filterDefault(lineNum);
        String param = result.getParams()[0];
        if (!param.equals(DirectoryProcessor.FILTER_YES) && !param.equals(DirectoryProcessor.FILTER_NO))
            return this.filterDefault(lineNum);
        return result;
    }
}
