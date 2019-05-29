package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;
import java.util.ArrayList;

/**
 * This abstract class represents a basic filter command with generic parameters.
 *
 * @param <T> Type of the parameters, to be implemented in child classes.
 */
public abstract class AbstractFilterParams<T> extends AbstractFilterNoParams {
    /* Class members - variables */
    private ArrayList<T> params; // Filter parameters.

    /* Constructors */

    /**
     * Constructor for AbstractFilterParams. Calls parent constructor and initialises filter parameters.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    AbstractFilterParams(File[] files, FilterWrapper filter) {
        super(files, filter);
        this.params = this.setParams(filter.getParams());
    }

    /* Protected instance methods */

    /**
     * @return Filter parameters.
     */
    protected ArrayList<T> getParams() {
        return this.params;
    }

    /**
     * Sets the filter parameters. To be implemented in child classes.
     *
     * @param params Filter parameters.
     * @return Type T list with parameters.
     */
    protected abstract ArrayList<T> setParams(ArrayList<String> params);
}
