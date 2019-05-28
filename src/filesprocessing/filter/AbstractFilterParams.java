package filesprocessing.filter;

import java.io.File;
import java.util.ArrayList;

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
