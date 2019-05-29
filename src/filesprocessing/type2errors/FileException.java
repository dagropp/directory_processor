package filesprocessing.type2errors;

import java.io.File;

/**
 * This abstract class represents a basic Exception for dealing with file errors.
 */
public abstract class FileException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "*FILE EXCEPTION THROWN* ";

    public FileException(String message, File file) {
        super(ERROR_MSG + message + file);
    }
}
