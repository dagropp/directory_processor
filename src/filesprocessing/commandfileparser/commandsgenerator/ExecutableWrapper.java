package filesprocessing.commandfileparser.commandsgenerator;

/**
 * Filter/Order command wrapper interface, to hold information on command (name, negation, warnings, line number).
 */
public interface ExecutableWrapper {
    /**
     * Sets the executable name.
     *
     * @param name Executable name.
     */
    void setName(String name);

    /**
     * Sets the executable negation.
     *
     * @param negation True if is negation, false otherwise.
     */
    void setNegation(boolean negation);

    /**
     * Sets warning for the executable.
     */
    void setWarning();

    /**
     * @return This executable name.
     */
    String getName();

    /**
     * @return True if is negation, false otherwise.
     */
    boolean isNegation();

    /**
     * @return True if executable has line warning, false otherwise.
     */
    boolean isWarning();

    /**
     * @return Executable line number.
     */
    int getLineNum();
}
