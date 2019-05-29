package filesprocessing.commandfileparser.commandsgenerator;

/**
 * This class creates a wrapper object with order's name, negation, warning and its original line number.
 */
public class OrderWrapper implements ExecutableWrapper {
    /* Class members - variables */
    private String name; // Order name.
    private boolean negation = false; // Order negation, default is false.
    private boolean warning = false; // Order warning, default is false.
    private int lineNum; // Order line number.

    /* Constructors */

    /**
     * Constructor for OrderWrapper. Initialises order with line number.
     *
     * @param lineNum Order line number.
     */
    public OrderWrapper(int lineNum) {
        this.lineNum = lineNum;
    }

    /* Public instance methods */

    /**
     * Sets order name.
     *
     * @param name Executable name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets order negation.
     *
     * @param negation True if is negation, false otherwise.
     */
    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    /**
     * Sets warning for order.
     */
    public void setWarning() {
        this.warning = true;
    }

    /**
     * @return Order name.
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
     * @return True if order has line warning, false otherwise.
     */
    public boolean isWarning() {
        return this.warning;
    }

    /**
     * @return Order line number.
     */
    public int getLineNum() {
        return this.lineNum;
    }
}

