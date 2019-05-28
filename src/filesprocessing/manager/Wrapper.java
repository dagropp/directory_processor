package filesprocessing.manager;

public interface Wrapper {
    void setName(String name);

    void setNegation(boolean negation);

    void setWarning();

    String getName();

    boolean isNegation();

    boolean isWarning();

    int getLineNum();
}
