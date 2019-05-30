package filesprocessing.type2errors;

/**
 * This class represents an exception thrown when FILTER/ORDER command headers are not in expected format.
 */
public class InvalidCommandHeader extends InputException {
    /* Class members - constant variables */
    private static final long serialVersionUID = 1L; // Exception serial number.
    private static final String ERROR_MSG = "FILTER/ORDER headers are invalid."; // Exception error message.

    /* Constructors */

    /**
     * Constructor for InvalidCommandHeader. Calls parent constructor with custom message.
     */
    public InvalidCommandHeader() {
        super(ERROR_MSG);
    }
}
