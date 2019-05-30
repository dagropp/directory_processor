package filesprocessing.type2errors;

/**
 * This abstract class represents a basic Exception for dealing with input errors.
 */
public abstract class InputException extends Exception {
    /* Class members - constant variables */
    private static final long serialVersionUID = 1L; // Exception serial number.
    private static final String ERROR_MSG = "*INPUT EXCEPTION THROWN* "; // Exception wrapper error message.

    /* Constructors */

    /**
     * Constructor for InputException. Calls Exception constructor with default message.
     *
     * @param message Custom message given in each child class constructor.
     */
    public InputException(String message) {
        super(ERROR_MSG + message);
    }
}
