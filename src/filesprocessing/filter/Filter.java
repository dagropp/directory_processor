package filesprocessing.filter;

import java.io.File;

/**
 * Interface to unify all filter classes.
 */
public interface Filter {
    /**
     * @return Filtered results.
     */
    File[] getResults();

    /**
     * @return File array to filter.
     */
    File[] getFiles();

    /**
     * @return True if is negation, false otherwise.
     */
    boolean isNegation();
}
