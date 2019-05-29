package filesprocessing.type2errors;

/**
 * This class represents an exception thrown when FILTER/ORDER command headers are not in expected format.
 */
public class InvalidCommandHeader extends InputException {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "FILTER/ORDER headers are invalid."; //

    public InvalidCommandHeader() {
        super(ERROR_MSG);
    }
}
