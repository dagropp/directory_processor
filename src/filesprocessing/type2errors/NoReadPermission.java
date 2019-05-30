package filesprocessing.type2errors;

import java.io.File;

/**
 * This class represents an exception thrown when can't read specified file/folder.
 */
public class NoReadPermission extends FileException {
    /* Class members - constant variables */
    private static final long serialVersionUID = 1L; // Exception serial number.
    private static final String ERROR_MSG = "Can't read file/folder in location: "; // Exception error message.

    /* Constructors */

    /**
     * Constructor for NoReadPermission. Calls parent constructor with custom message and File object.
     *
     * @param file File object to refer to in this Exception.
     */
    public NoReadPermission(File file) {
        super(ERROR_MSG, file);
    }
}
