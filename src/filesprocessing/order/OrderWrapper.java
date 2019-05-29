package filesprocessing.order;

import filesprocessing.manager.Wrapper;

/**
 * This class creates a wrapper object with order's name, negation, warning and its original line number.
 */
public class OrderWrapper implements Wrapper {
    private String name;
    private boolean negation = false;
    private boolean warning = false;
    private int lineNum;


    public OrderWrapper(int lineNum) {
        this.lineNum = lineNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNegation(boolean negation) {
        this.negation = negation;
    }

    public void setWarning() {
        this.warning = true;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNegation() {
        return this.negation;
    }

    public boolean isWarning() {
        return this.warning;
    }

    public int getLineNum() {
        return this.lineNum;
    }
}

