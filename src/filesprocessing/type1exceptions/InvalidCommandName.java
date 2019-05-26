package filesprocessing.type1exceptions;

public class InvalidCommandName extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String ERROR_MSG = "Warning in line "; // Default message for file constructor.

    public InvalidCommandName() {
        super();
        System.err.println(ERROR_MSG);
    }

    public InvalidCommandName(String message) {
        super(message);
    }
}
