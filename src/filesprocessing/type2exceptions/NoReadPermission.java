package filesprocessing.type2exceptions;

import java.io.File;

public class NoReadPermission extends Exception {
    private static final long serialVersionUID = 1L; // ddd
    private static final String ERROR_MSG = "Can't read file in location: "; // Default message for file constructor.

    public NoReadPermission() {
        super();
    }

    public NoReadPermission(String message) {
        super(message);
    }

    public NoReadPermission(File file) {
        super(ERROR_MSG + file.getAbsolutePath());
    }
}
