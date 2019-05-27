package filesprocessing.filter;

import java.io.File;

public class FilterAll {
    private File[] files;

    FilterAll(File[] files) {
        this.files = files.clone();
    }

    File[] all() {
        return this.files;
    }
}
