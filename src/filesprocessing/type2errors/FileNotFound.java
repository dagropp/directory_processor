package filesprocessing.type2errors;

import java.io.File;

/**
 * This class represents an exception thrown when specified file not found.
 */
public class FileNotFound extends FileException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "File not found in location: "; // Exception error message.

    public FileNotFound(File file) {
        super(ERROR_MSG, file);
    }
}
