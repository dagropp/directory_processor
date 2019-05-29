package filesprocessing.type2errors;

import java.io.File;

/**
 * This class represents an exception thrown when specified directory not found.
 */
public class DirectoryNotFound extends FileException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "Directory not found in location: "; // Exception error message.

    public DirectoryNotFound(File file) {
        super(ERROR_MSG, file);
    }
}
