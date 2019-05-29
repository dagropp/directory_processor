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
    private ArrayList<T> params;

    AbstractFilterParams(File[] files, FilterWrapper filter) {
        super(files, filter);
        this.params = this.setParams(filter.getParams());
    }

    protected ArrayList<T> getParams() {
        return this.params;
    }

    protected abstract ArrayList<T> setParams(ArrayList<String> params);
}
