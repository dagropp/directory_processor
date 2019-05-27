package filesprocessing.type2errors;

public class InvalidCommandHeader extends Exception {
    private static final long serialVersionUID = 1L; // ddd
    private static final String ERROR_MSG = "Invalid command header in line: "; // Default message.

    public InvalidCommandHeader() {
        super();
    }

    public InvalidCommandHeader(String message) {
        super(message);
    }

    public InvalidCommandHeader(int line) {
        super(ERROR_MSG + line);
    }
}
