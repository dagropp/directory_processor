package filesprocessing.type2errors;

import java.io.File;

/**
 * This class represents an exception thrown when specified directory not found.
 */
public class DirectoryNotFound extends FileException {
    /* Class members - constant variables */
    private static final long serialVersionUID = 1L; // Exception serial number.
    private static final String ERROR_MSG = "Directory not found in location: "; // Exception error message.

    /* Constructors */

    /**
     * Constructor for DirectoryNotFound. Calls parent constructor with custom message and File object.
     *
     * @param file File object to refer to in this Exception.
     */
    public DirectoryNotFound(File file) {
        super(ERROR_MSG, file);
    }
}
