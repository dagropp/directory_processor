package filesprocessing.commandfileparser.commandsgenerator;

/**
 * This generic abstract class represents reformatting guidelines and sets the required parameters.
 *
 * @param <T> Type of executable wrapper the child classes reformat.
 */
public abstract class ReformatExecutable<T extends ExecutableWrapper> {
    private String[] executable; // Executable command array.
    private String name; // Executable name.
    private int lineNum; // Executable line number.
    private T result; // Reformatted result.

    /**
     * Constructor for ReformatExecutable. Initialises command array, name, and line number, and calls abstract method
     * to set results.
     *
     * @param executable Executable command array.
     * @param lineNum    Executable line number.
     */
    public ReformatExecutable(String[] executable, int lineNum) {
        this.executable = executable;
        this.name = executable[0];
        this.lineNum = lineNum;
        this.result = this.identify();
    }

    /**
     * @return Reformatted result.
     */
    public T getResult() {
        return this.result;
    }

    /**
     * @return Executable command array.
     */
    protected String[] getExecutable() {
        return this.executable;
    }

    /**
     * @return Executable name.
     */
    protected String getName() {
        return this.name;
    }

    /**
     * @return Executable line number.
     */
    protected int getLineNum() {
        return this.lineNum;
    }

    /**
     * Checks if executable name is in given array.
     *
     * @param commandLegend Array with specified commands legend,
     * @return True if name is in the array, false otherwise.
     */
    protected boolean inArray(String[] commandLegend) {
        for (String item : commandLegend) // Iterates over the input array.
            if (this.name.equals(item))
                return true; // If the name equals any item.
        return false; // Otherwise.
    }

    /**
     * Identifies the relevant executable.
     *
     * @return Wrapper with identified executable, to be implemented in child classes.
     */
    protected abstract T identify();
}
