package filesprocessing.type2errors;

import java.io.File;

public class DirectoryNotFound extends Exception {
    private static final long serialVersionUID = 1L; // ddd
    private static final String ERROR_MSG = "Directory not found in location: "; // Default message for file constructor.

    public DirectoryNotFound() {
        super();
    }

    public DirectoryNotFound(String message) {
        super(message);
    }

    public DirectoryNotFound(File file) {
        super(ERROR_MSG + file.getAbsolutePath());
    }
}
