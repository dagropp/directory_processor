package filesprocessing.type2errors;

public class InvalidArgs extends Exception {
    private static final long serialVersionUID = 1L; // ddd
    private static final String ERROR_MSG = "Command line args are invalid. "; // Default message for file constructor.

    public InvalidArgs() {
        super(ERROR_MSG);
    }

    public InvalidArgs(String message) {
        super(ERROR_MSG + message);
    }
}
