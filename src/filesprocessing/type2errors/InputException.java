package filesprocessing.type2errors;

/**
 * This abstract class represents a basic Exception for dealing with input errors.
 */
public abstract class InputException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "*INPUT EXCEPTION THROWN* "; // Abstract Exception error message.

    public InputException(String message) {
        super(ERROR_MSG + message);
    }
}
