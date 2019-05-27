package filesprocessing.filter;

import java.io.File;
import java.util.Arrays;

public class FilterSize extends FilterGeneral {
    private double[] params;

    FilterSize(File[] files, FilterWrapper filter) {
        super(files, filter);
        this.setParams(filter.getParams());
    }

    File[] greaterThan() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.length() > this.params[0] ^ this.isNegation()
        ).toArray(File[]::new);
    }

    File[] smallerThan() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.length() < this.params[0] ^ this.isNegation()
        ).toArray(File[]::new);
    }

    File[] between() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.length() >= this.params[0] && f.length() <= this.params[1] ^ this.isNegation()
        ).toArray(File[]::new);
    }

    private void setParams(String[] params) {
        this.params = new double[params.length];
        for (int i = 0; i < params.length; i++) {
            this.params[i] = Double.parseDouble(params[i]);
        }
    }
}
