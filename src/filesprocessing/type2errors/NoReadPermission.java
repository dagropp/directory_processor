package filesprocessing.type2errors;

import java.io.File;

/**
 * This class represents an exception thrown when can't read specified file/folder.
 */
public class NoReadPermission extends FileException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "Can't read file/folder in location: "; // Exception error message.

    public NoReadPermission(File file) {
        super(ERROR_MSG, file);
    }
}
