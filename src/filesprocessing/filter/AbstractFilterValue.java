package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.ArrayList;

/**
 * This abstract class represents a basic filter command with String parameters.
 */
public abstract class AbstractFilterValue extends AbstractFilterParams<String> {

    /* Constructors */

    /**
     * Constructor for AbstractFilterValue. Calls parent constructor.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public AbstractFilterValue(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    /* Public instance methods */

    /**
     * Sets filter String parameters.
     *
     * @param params Filter parameters.
     * @return String list with parameters.
     */
    public ArrayList<String> setParams(ArrayList<String> params) {
        return params;
    }
}
