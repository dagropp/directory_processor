package filesprocessing.manager;

/**
 * Filter/Order command wrapper interface, to hold information on command (name, negation, warnings, line number).
 */
public interface Wrapper {
    void setName(String name);

    void setNegation(boolean negation);

    void setWarning();

    String getName();

    boolean isNegation();

    boolean isWarning();

    int getLineNum();
}
