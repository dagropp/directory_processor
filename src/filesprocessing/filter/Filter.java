package filesprocessing.filter;

import java.io.File;

/**
 * Interface to unify all filter classes.
 */
public interface Filter {
    File[] getResults();

    File[] getFiles();

    boolean isNegation();
}
