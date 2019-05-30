package filesprocessing.filter;

import filesprocessing.commandfileparser.commandsgenerator.FilterWrapper;

import java.io.File;

/**
 * This abstract class represents a basic filter command with no parameters.
 */
public abstract class AbstractFilterNoParams implements Filter {
    /* Class members - variables */
    private File[] files; // File array to filter.
    private FilterWrapper filter; // Filter command.
    private boolean negation; // Filter negation.

    /* Constructors */

    /**
     * Constructor for AbstractFilterNoParams. Initialises files array and sets filter command and negation.
     *
     * @param files  File array to filter.
     * @param filter Filter command.
     */
    public AbstractFilterNoParams(File[] files, FilterWrapper filter) {
        this.files = files.clone();
        this.filter = filter;
        this.negation = filter.isNegation();
    }

    /* Public instance methods */

    /**
     * @return File array to filter.
     */
    public File[] getFiles() {
        return this.files;
    }

    /**
     * @return True if is negation, false otherwise.
     */
    public boolean isNegation() {
        return this.negation;
    }
}
