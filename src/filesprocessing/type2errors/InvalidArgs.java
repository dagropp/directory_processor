package filesprocessing.type2errors;

/**
 * This class represents an exception thrown when command line args are not in expected format.
 */
public class InvalidArgs extends InputException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG =
            "Command line args are invalid. Expected args number: 2; Actual args number: "; // Exception error message.

    public InvalidArgs(int length) {
        super(ERROR_MSG + length);
    }
}
