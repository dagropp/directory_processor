package filesprocessing.filter;

import filesprocessing.DirectoryProcessor;

import java.io.File;
import java.util.Arrays;

public class FilterYesNo extends FilterGeneral {
    private boolean negation = true;

    FilterYesNo(File[] files, FilterWrapper filter) {
        super(files, filter);
    }

    File[] writable() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.canWrite() ^ !this.negation
        ).toArray(File[]::new);
    }

    File[] executable() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.canExecute() ^ !this.negation
        ).toArray(File[]::new);
    }

    File[] hidden() {
        return Arrays.stream(this.getFiles()).filter(
                f -> f.isHidden() ^ !this.negation
        ).toArray(File[]::new);
    }

    protected void setToggle(String[] params) {
        this.negation = params[0].equals(DirectoryProcessor.FILTER_YES);
    }
}
