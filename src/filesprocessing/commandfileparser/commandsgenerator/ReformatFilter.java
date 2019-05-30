package filesprocessing.commandfileparser.commandsgenerator;

import filesprocessing.filter.FilterFactory;

import java.util.ArrayList;

/**
 * This class reformats the filter String[], by checking if it contains correct and valid name and parameters, and by
 * replacing invalid filters with default filter (all).
 */
public class ReformatFilter extends ReformatExecutable<FilterWrapper> {
    /* Class members - constant variables */
    private static final String FILTER_NOT = "NOT";
    private static final String FILTER_YES = "YES";
    private static final String FILTER_NO = "NO";
    private static final int LENGTH_NO_PARAMS = 1;
    private static final int LENGTH_1_PARAM = 2;
    private static final int LENGTH_2_PARAMS = 3;

    /* Constructors */

    /**
     * Constructor for ReformatFilter. Calls parent constructor to set filter array, name and line number.
     *
     * @param filter  Filter array.
     * @param lineNum Filter line number.
     */
    public ReformatFilter(String[] filter, int lineNum) {
        super(filter, lineNum);
    }

    /* Protected instance methods */

    /**
     * Identifies the relevant filter command. If not in filter commands legend, assigns default filter and sets
     * type 1 error warning.
     *
     * @return Wrapper with identified filter.
     */
    protected FilterWrapper identify() {
        // Checks which filter group this filter belongs to, and returns the relevant parsing method:
        if (this.inArray(FilterFactory.getSizeFilters(1))) // Group: size filters with 1 parameter.
            return this.isFilterSize(LENGTH_1_PARAM);
        else if (this.inArray(FilterFactory.getSizeFilters(2))) // Group: size filters with 2 parameters.
            return this.isFilterSize(LENGTH_2_PARAMS);
        else if (this.inArray(FilterFactory.getValueFilters())) // Group: value filters.
            return this.isFilterGeneral(LENGTH_1_PARAM);
        else if (this.inArray(FilterFactory.getStateFilters())) // Group: state filters.
            return isFilterState();
        else if (this.getName().equals(FilterFactory.getDefaultFilter())) // Group: the 'all' filter.
            return isFilterGeneral(LENGTH_NO_PARAMS);
        return isFilterDefault(); // No group found, assigns default filter and set type 1 error warning.
    }

    /* Private instance methods */

    /**
     * Assigns default filter (all) and set type 1 error warning.
     *
     * @return Wrapper with the default filter and warning.
     */
    private FilterWrapper isFilterDefault() {
        FilterWrapper result = new FilterWrapper(this.getLineNum()); // Initialises new filter object.
        result.setName(FilterFactory.getDefaultFilter()); // Sets the filter name to default filter.
        result.setWarning(); // Sets warning to true.
        return result;
    }

    /**
     * General filter initializer, before it passes more specific checks.
     *
     * @param cmdLength This filter command expected length.
     * @return Wrapper with the named filter or the default filter if problems occur.
     */
    private FilterWrapper isFilterGeneral(int cmdLength) {
        String[] filter = this.getExecutable(); // Assigns the filter command array.
        FilterWrapper result = new FilterWrapper(this.getLineNum()); // Initialises new filter object.
        result.setName(this.getName()); // Sets the filter name to this filter's name.
        // If the expected length matches the actual length:
        if (filter.length == cmdLength) {
            for (int i = 1; i < filter.length; i++)
                result.addParam(filter[i]); // add parameters to the filter object
            return result; // Return the filter.
            // If actual length is 1 more than expected length, suspects NOT suffix:
        } else if (filter.length == (cmdLength + 1) &&
                filter[filter.length - 1].equals(FILTER_NOT)) { // If NOT suffix indeed there:
            for (int i = 1; i < filter.length - 1; i++)
                result.addParam(filter[i]); // Add parameters to the filter object.
            result.setNegation(true); // Sets negation of filter to true.
            return result; // Return the filter.
        }
        return this.isFilterDefault(); // If no test passed, return default filter and set warning.
    }

    /**
     * Size filter initializer.
     *
     * @param cmdLength This filter command expected length.
     * @return Wrapper with size filter or the default filter if problems occur.
     */
    private FilterWrapper isFilterSize(int cmdLength) {
        FilterWrapper result = this.isFilterGeneral(cmdLength); // Assign general filter initialization.
        // If the filter name changed, it means some problems occurred and the default filter was set.
        if (!result.getName().equals(this.getName()))
            return result; // Return this default filter.
        ArrayList<String> params = result.getParams(); // Assign filter params to list.
        // Tries to convert the String parameters to numeric doubles and run tests on them:
        try {
            // If only 1 param: checks if this param is not smaller than 0, therefore invalid.
            if (params.size() == 1) {
                if (Double.parseDouble(params.get(0)) < 0)
                    return this.isFilterDefault(); // If smaller than 0.
                // If 2 params: checks if not smaller than 0, or num1 > num2, therefore invalid.
            } else if (params.size() == 2) {
                double num1 = Double.parseDouble(params.get(0));
                double num2 = Double.parseDouble(params.get(1));
                if (num1 < 0 || num2 < 0 || num1 > num2)
                    return this.isFilterDefault(); // If smaller than 0 or num1 > num2.
            } else {
                return this.isFilterDefault(); // If more than 2 params.
            }
            return result; // All tests passed, return the filter.
            // If problems occurred when converting String to double, return default filter.
        } catch (NumberFormatException e) {
            return this.isFilterDefault();
        }
    }

    /**
     * State filter initializer.
     *
     * @return Wrapper with state filter or the default filter if problems occur.
     */
    private FilterWrapper isFilterState() {
        FilterWrapper result = this.isFilterGeneral(LENGTH_1_PARAM); // Assign general filter initialization.
        // If the filter name changed, it means some problems occurred and the default filter was set.
        if (!result.getName().equals(this.getName()))
            return result; // Return this default filter.
        if (result.getParams().size() > 1)
            return this.isFilterDefault(); // If more than 1 param.
        String param = result.getParams().get(0); // Assign param.
        // Checks if param is either YES or NO.
        if (!param.equals(FILTER_YES) && !param.equals(FILTER_NO))
            return this.isFilterDefault(); // If not YES/NO
        return result; // All tests passed, return the filter.
    }
}
