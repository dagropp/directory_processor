package filesprocessing.filter;

import filesprocessing.manager.DirectoryProcessorFactory;
import filesprocessing.commandfileparser.CommandWrapper;

import java.util.ArrayList;

/**
 * This class reformats the filter String[], by checking if it contains correct and valid name and parameters, and by
 * replacing invalid filters with default filter (all).
 */
public class ReformatFilter {
    private static final String FILTER_NOT = "NOT";
    private static final String FILTER_YES = "YES";
    private static final String FILTER_NO = "NO";
    private static final int LENGTH_NO_PARAMS = 1;
    private static final int LENGTH_1_PARAM = 2;
    private static final int LENGTH_2_PARAMS = 3;


    public static FilterWrapper execute(CommandWrapper command) {
        FilterWrapper filter = identifyFilter(command.getFilter(), command.getFilterLine());
        if (filter.isWarning())
            System.err.println(DirectoryProcessorFactory.getWarningMsg() + filter.getLineNum());
        return filter;
    }

    private static FilterWrapper identifyFilter(String[] filter, int lineNum) {
        switch (filter[0]) {
            case FilterFactory.FILTER_SIZE_GREATER:
                return isFilterSize(filter, FilterFactory.FILTER_SIZE_GREATER, LENGTH_1_PARAM, lineNum);
            case FilterFactory.FILTER_SIZE_SMALLER:
                return isFilterSize(filter, FilterFactory.FILTER_SIZE_SMALLER, LENGTH_1_PARAM, lineNum);
            case FilterFactory.FILTER_SIZE_BETWEEN:
                return isFilterSize(filter, FilterFactory.FILTER_SIZE_BETWEEN, LENGTH_2_PARAMS, lineNum);
            case FilterFactory.FILTER_VALUE_FILE_NAME:
                return isFilterGeneral(filter, FilterFactory.FILTER_VALUE_FILE_NAME, LENGTH_1_PARAM, lineNum);
            case FilterFactory.FILTER_VALUE_CONTAINS:
                return isFilterGeneral(filter, FilterFactory.FILTER_VALUE_CONTAINS, LENGTH_1_PARAM, lineNum);
            case FilterFactory.FILTER_VALUE_PREFIX:
                return isFilterGeneral(filter, FilterFactory.FILTER_VALUE_PREFIX, LENGTH_1_PARAM, lineNum);
            case FilterFactory.FILTER_VALUE_SUFFIX:
                return isFilterGeneral(filter, FilterFactory.FILTER_VALUE_SUFFIX, LENGTH_1_PARAM, lineNum);
            case FilterFactory.FILTER_STATE_WRITE:
                return isFilterState(filter, FilterFactory.FILTER_STATE_WRITE, lineNum);
            case FilterFactory.FILTER_STATE_EXECUTE:
                return isFilterState(filter, FilterFactory.FILTER_STATE_EXECUTE, lineNum);
            case FilterFactory.FILTER_STATE_HIDDEN:
                return isFilterState(filter, FilterFactory.FILTER_STATE_HIDDEN, lineNum);
            case FilterFactory.FILTER_ALL:
                return isFilterGeneral(filter, FilterFactory.FILTER_ALL, LENGTH_NO_PARAMS, lineNum);
            default:
                return isFilterDefault(lineNum);
        }
    }

    private static FilterWrapper isFilterDefault(int lineNum) {
        FilterWrapper result = new FilterWrapper(lineNum);
        result.setName(FilterFactory.FILTER_ALL);
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
                filter[filter.length - 1].equals(FILTER_NOT)) {
            for (int i = 1; i < filter.length - 1; i++)
                result.addParam(filter[i]);
            result.setNegation(true);
            return result;
        }
        return isFilterDefault(lineNum);
    }

    private static FilterWrapper isFilterSize(String[] filter, String name, int cmdLength, int lineNum) {
        FilterWrapper result = isFilterGeneral(filter, name, cmdLength, lineNum);
        if (!result.getName().equals(name))
            return result;
        ArrayList<String> params = result.getParams();
        try {
            if (params.size() == 1) {
                if (Double.parseDouble(params.get(0)) < 0)
                    return isFilterDefault(lineNum);
            } else if (params.size() == 2) {
                double num1 = Double.parseDouble(params.get(0));
                double num2 = Double.parseDouble(params.get(1));
                if (num1 < 0 || num2 < 0 || num1 > num2)
                    return isFilterDefault(lineNum);
            } else {
                return isFilterDefault(lineNum);
            }
            return result;
        } catch (NumberFormatException e) {
            return isFilterDefault(lineNum);
        }
    }

    private static FilterWrapper isFilterState(String[] filter, String name, int lineNum) {
        FilterWrapper result = isFilterGeneral(filter, name, LENGTH_1_PARAM, lineNum);
        if (!result.getName().equals(name))
            return result;
        if (result.getParams().size() > 1)
            return isFilterDefault(lineNum);
        String param = result.getParams().get(0);
        if (!param.equals(FILTER_YES) && !param.equals(FILTER_NO))
            return isFilterDefault(lineNum);
        return result;
    }
}
