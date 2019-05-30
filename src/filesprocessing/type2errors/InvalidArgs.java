package filesprocessing.type2errors;

/**
 * This class represents an exception thrown when command line args are not in expected format.
 */
public class InvalidArgs extends InputException {
    /* Class members - constant variables */
    private static final long serialVersionUID = 1L; // Exception serial number.
    private static final String ERROR_MSG =
            "Command line args are invalid. Expected args number: 2; Actual args number: "; // Exception error message.

    /* Constructors */

    /**
     * Constructor for InvalidArgs. Calls parent constructor with custom message and args length.
     *
     * @param length Actual parameters in command line args.
     */
    public InvalidArgs(int length) {
        super(ERROR_MSG + length);
    }
}
