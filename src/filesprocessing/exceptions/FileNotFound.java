package filesprocessing.exceptions;

import java.io.File;

public class FileNotFound extends Exception {
    private static final long serialVersionUID = 1L; // ddd
    private static final String ERROR_MSG = "File not found in location: "; // Default message for file constructor.

    public FileNotFound() {
        super();
    }

    public FileNotFound(String message) {
        super(message);
    }

    public FileNotFound(File file) {
        super(ERROR_MSG + file.getAbsolutePath());
    }
}
