package filesprocessing.commandfileparser.commandsgenerator;

/**
 * This generic abstract class represents reformatting guidelines and sets the required parameters.
 *
 * @param <T> Type of executable wrapper the child classes reformat.
 */
public abstract class ReformatExecutable<T extends ExecutableWrapper> {
    private String[] executable;
    private String name;
    private int lineNum;
    private T result;

    public ReformatExecutable(String[] executable, int lineNum) {
        this.executable = executable;
        this.name = executable[0];
        this.lineNum = lineNum;
        this.result = this.identify();
    }

    public T getResult() {
        return this.result;
    }

    protected String[] getExecutable() {
        return this.executable;
    }

    protected String getName() {
        return this.name;
    }

    protected int getLineNum() {
        return this.lineNum;
    }

    protected boolean inArray(String[] commandLegend) {
        for (String item : commandLegend)
            if (this.name.equals(item))
                return true;
        return false;
    }

    protected abstract T identify();
}
