package filesprocessing.filter;

import java.io.File;
import java.util.ArrayList;

/**
 * This abstract class represents a basic filter command with String parameters.
 */
public abstract class AbstractFilterValue extends AbstractFilterParams<String> {
    ArrayList<String> params;

    public AbstractFilterValue(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    public ArrayList<String> setParams(ArrayList<String> params) {
        return params;
    }
}
