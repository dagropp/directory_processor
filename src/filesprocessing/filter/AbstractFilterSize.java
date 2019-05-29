package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.ArrayList;

/**
 * This abstract class represents a basic filter command with numeric Double parameters.
 */
public abstract class AbstractFilterSize extends AbstractFilterParams<Double> {
    /* Class members - constant variables */
    private static final double BYTES_IN_KB = 1024; // Conversion between bytes and kilobytes.

    /* Constructors */

    /**
     * Constructor for AbstractFilterSize. Calls parent constructor.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public AbstractFilterSize(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Protected instance methods */

    /**
     * Sets filter numeric Double parameters.
     *
     * @param params Filter parameters.
     * @return Double list with parameters.
     */
    protected ArrayList<Double> setParams(ArrayList<String> params) {
        ArrayList<Double> result = new ArrayList<>(); // Assigns new list for the results.
        // Iterates over the filter parameters, converts them to Double value, and adds them to list.
        for (String param : params)
            result.add(Double.parseDouble(param));
        return result; // Filled parameters list.
    }

    /**
     * @param bytes To convert.
     * @return Value of given bytes in kilobytes.
     */
    protected double bytesToKB(double bytes) {
        return bytes / BYTES_IN_KB;
    }
}
