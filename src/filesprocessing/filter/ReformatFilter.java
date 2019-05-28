package filesprocessing.filter;

import filesprocessing.manager.DirectoryProcessorFactory;
import filesprocessing.commandfileparser.CommandWrapper;

public class ReformatFilter {
    private FilterWrapper filter;
    private static final int LENGTH_NO_PARAMS = 1;
    private static final int LENGTH_1_PARAM = 2;
    private static final int LENGTH_2_PARAMS = 3;


    public static FilterWrapper execute(CommandWrapper command) {
        FilterWrapper filter = identifyFilter(command.getFilter(), command.getFilterLine());
        if (filter.isWarning())
            System.err.println(DirectoryProcessorFactory.WARNING_MSG + filter.getLineNum());
        return filter;
    }

    private static FilterWrapper identifyFilter(String[] filter, int lineNum) {
        switch (filter[0]) {
            case DirectoryProcessorFactory.FILTER_SIZE_GREATER:
                return isFilterNum(filter, DirectoryProcessorFactory.FILTER_SIZE_GREATER, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessorFactory.FILTER_SIZE_SMALLER:
                return isFilterNum(filter, DirectoryProcessorFactory.FILTER_SIZE_SMALLER, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessorFactory.FILTER_SIZE_BETWEEN:
                return isFilterNum(filter, DirectoryProcessorFactory.FILTER_SIZE_BETWEEN, LENGTH_2_PARAMS, lineNum);
            case DirectoryProcessorFactory.FILTER_VALUE_FILE_NAME:
                return isFilterGeneral(filter, DirectoryProcessorFactory.FILTER_VALUE_FILE_NAME, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessorFactory.FILTER_VALUE_CONTAINS:
                return isFilterGeneral(filter, DirectoryProcessorFactory.FILTER_VALUE_CONTAINS, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessorFactory.FILTER_VALUE_PREFIX:
                return isFilterGeneral(filter, DirectoryProcessorFactory.FILTER_VALUE_PREFIX, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessorFactory.FILTER_VALUE_SUFFIX:
                return isFilterGeneral(filter, DirectoryProcessorFactory.FILTER_VALUE_SUFFIX, LENGTH_1_PARAM, lineNum);
            case DirectoryProcessorFactory.FILTER_PERMISSION_WRITE:
                return isFilterYesNo(filter, DirectoryProcessorFactory.FILTER_PERMISSION_WRITE, lineNum);
            case DirectoryProcessorFactory.FILTER_PERMISSION_EXECUTE:
                return isFilterYesNo(filter, DirectoryProcessorFactory.FILTER_PERMISSION_EXECUTE, lineNum);
            case DirectoryProcessorFactory.FILTER_HIDDEN:
                return isFilterYesNo(filter, DirectoryProcessorFactory.FILTER_HIDDEN, lineNum);
            case DirectoryProcessorFactory.FILTER_ALL:
                return isFilterGeneral(filter, DirectoryProcessorFactory.FILTER_ALL, LENGTH_NO_PARAMS, lineNum);
            default:
                return isFilterDefault(lineNum);
        }
    }

    private static FilterWrapper isFilterDefault(int lineNum) {
        FilterWrapper result = new FilterWrapper(lineNum);
        result.setName(DirectoryProcessorFactory.FILTER_ALL);
        result.setWarning();
        return result;
    }


    private static FilterWrapper isFilterGeneral(String[] filter, String name, int cmdLength, int lineNum) {
        FilterWrapper result = new FilterWrapper(lineNum);
        result.setName(name);
        if (filter.length == cmdLength) {
            for (int i = 1; i < filter.length; i++)
                result.addParam(filter[i]);
            return result;
        } else if (filter.length == (cmdLength + 1) &&
                filter[filter.length - 1].equals(DirectoryProcessorFactory.FILTER_NOT)) {
            for (int i = 1; i < filter.length - 1; i++)
                result.addParam(filter[i]);
            result.setNegation(true);
            return result;
        }
        return isFilterDefault(lineNum);
    }

    private static FilterWrapper isFilterNum(String[] filter, String name, int cmdLength, int lineNum) {
        FilterWrapper result = isFilterGeneral(filter, name, cmdLength, lineNum);
        if (!result.getName().equals(name))
            return result;
        double num;
        for (String param : result.getParams())
            try {
                num = Double.parseDouble(param);
                if (num < 0)
                    return isFilterDefault(lineNum);
            } catch (NumberFormatException e) {
                return isFilterDefault(lineNum);
            }
        return result;
    }

    private static FilterWrapper isFilterYesNo(String[] filter, String name, int lineNum) {
        FilterWrapper result = isFilterGeneral(filter, name, LENGTH_1_PARAM, lineNum);
        if (!result.getName().equals(name))
            return result;
        if (result.getParams().size() > 1)
            return isFilterDefault(lineNum);
        String param = result.getParams().get(0);
        if (!param.equals(DirectoryProcessorFactory.FILTER_YES) && !param.equals(DirectoryProcessorFactory.FILTER_NO))
            return isFilterDefault(lineNum);
        return result;
    }
}
