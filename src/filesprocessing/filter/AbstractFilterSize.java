package filesprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public abstract class AbstractFilterSize extends AbstractFilterParams<Double> {
    private double BYTES_IN_KB = 1024;

    public AbstractFilterSize(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    protected ArrayList<Double> setParams(ArrayList<String> params) {
        ArrayList<Double> result = new ArrayList<>();
        for (String param : params)
            result.add(Double.parseDouble(param));
        return result;
    }

    protected double bytesToKB(double bytes) {
        return bytes / BYTES_IN_KB;
    }
}