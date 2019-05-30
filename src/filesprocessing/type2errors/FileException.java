package filesprocessing.type2errors;

import java.io.File;

/**
 * This abstract class represents a basic Exception for dealing with file errors.
 */
public abstract class FileException extends Exception {
    /* Class members - constant variables */
    private static final long serialVersionUID = 1L; // Exception serial number.
    private static final String ERROR_MSG = "*FILE EXCEPTION THROWN* "; // Exception wrapper error message.

    /* Constructors */

    /**
     * Constructor for FileException. Calls Exception constructor with default message.
     *
     * @param message Custom message given in each child class constructor.
     * @param file    File object to refer to in this Exception.
     */
    public FileException(String message, File file) {
        super(ERROR_MSG + message + file.getAbsolutePath());
    }
}
