package filesprocessing.commandfileparser.commandsgenerator;

import java.util.ArrayList;

/**
 * This class creates a wrapper object with filter's name, parameters, negation, warning and its original line number.
 */
public class FilterWrapper implements ExecutableWrapper {
    /* Class members - variables */
    private String name; // Filter name.
    private ArrayList<String> params = new ArrayList<>(); // Filter parameters.
    private boolean negation = false; // Filter negation, default is false.
    private boolean warning = false; // Filter warning, default is false.
    private int lineNum; // Filter line number.

    /* Constructors */

    /**
     * Constructor for FilterWrapper. Initialises filter with line number.
     *
     * @param lineNum Filter line number.
     */
    public FilterWrapper(int lineNum) {
        this.lineNum = lineNum;
    }

    /* Public instance methods */

    /**
     * Sets filter name.
     *
     * @param name Executable name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets filter negation.
     *
     * @param negation True if is negation, false otherwise.
     */
    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    /**
     * Sets warning for filter.
     */
    public void setWarning() {
        this.warning = true;
    }

    /**
     * Adds parameter to filter params list.
     *
     * @param param Parameter to add.
     */
    public void addParam(String param) {
        this.params.add(param);
    }

    /**
     * @return Filter name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return True if is negation, false otherwise.
     */
    public boolean isNegation() {
        return this.negation;
    }

    /**
     * @return True if filter has line warning, false otherwise.
     */
    public boolean isWarning() {
        return this.warning;
    }

    /**
     * @return Filter line number.
     */
    public int getLineNum() {
        return this.lineNum;
    }

    /**
     * @return Parameters list.
     */
    public ArrayList<String> getParams() {
        return this.params;
    }
}
