package filesprocessing.commandfileparser.commandsgenerator;

import filesprocessing.filter.FilterFactory;

import java.util.ArrayList;

/**
 * This class reformats the filter String[], by checking if it contains correct and valid name and parameters, and by
 * replacing invalid filters with default filter (all).
 */
public class ReformatFilter extends ReformatExecutable<FilterWrapper> {
    private static final String FILTER_NOT = "NOT";
    private static final String FILTER_YES = "YES";
    private static final String FILTER_NO = "NO";
    private static final int LENGTH_NO_PARAMS = 1;
    private static final int LENGTH_1_PARAM = 2;
    private static final int LENGTH_2_PARAMS = 3;

    public ReformatFilter(String[] filter, int lineNum) {
        super(filter, lineNum);
    }

    protected FilterWrapper identify() {
        if (this.inArray(FilterFactory.getSizeFilters(1)))
            return this.isFilterSize(LENGTH_1_PARAM);
        else if (this.inArray(FilterFactory.getSizeFilters(2)))
            return this.isFilterSize(LENGTH_2_PARAMS);
        else if (this.inArray(FilterFactory.getValueFilters()))
            return this.isFilterGeneral(LENGTH_1_PARAM);
        else if (this.inArray(FilterFactory.getStateFilters()))
            return isFilterState();
        else if (this.getName().equals(FilterFactory.getDefaultFilter()))
            return isFilterGeneral(LENGTH_NO_PARAMS);
        return isFilterDefault();
    }

    private FilterWrapper isFilterDefault() {
        FilterWrapper result = new FilterWrapper(this.getLineNum());
        result.setName(FilterFactory.getDefaultFilter());
        result.setWarning();
        return result;
    }


    private FilterWrapper isFilterGeneral(int cmdLength) {
        String[] filter = this.getExecutable();
        FilterWrapper result = new FilterWrapper(this.getLineNum());
        result.setName(this.getName());
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
        return this.isFilterDefault();
    }

    private FilterWrapper isFilterSize(int cmdLength) {
        FilterWrapper result = this.isFilterGeneral(cmdLength);
        if (!result.getName().equals(this.getName()))
            return result;
        ArrayList<String> params = result.getParams();
        try {
            if (params.size() == 1) {
                if (Double.parseDouble(params.get(0)) < 0)
                    return this.isFilterDefault();
            } else if (params.size() == 2) {
                double num1 = Double.parseDouble(params.get(0));
                double num2 = Double.parseDouble(params.get(1));
                if (num1 < 0 || num2 < 0 || num1 > num2)
                    return this.isFilterDefault();
            } else {
                return this.isFilterDefault();
            }
            return result;
        } catch (NumberFormatException e) {
            return this.isFilterDefault();
        }
    }

    private FilterWrapper isFilterState() {
        FilterWrapper result = this.isFilterGeneral(LENGTH_1_PARAM);
        if (!result.getName().equals(this.getName()))
            return result;
        if (result.getParams().size() > 1)
            return this.isFilterDefault();
        String param = result.getParams().get(0);
        if (!param.equals(FILTER_YES) && !param.equals(FILTER_NO))
            return this.isFilterDefault();
        return result;
    }
}
