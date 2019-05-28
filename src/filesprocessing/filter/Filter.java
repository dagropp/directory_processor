package filesprocessing.filter;

import java.io.File;

public interface Filter {
    File[] getResults();

    File[] getFiles();

    boolean isNegation();
}
