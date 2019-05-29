package filesprocessing.filter;

import filesprocessing.manager.Wrapper;

import java.util.ArrayList;

/**
 * This class creates a wrapper object with filter's name, parameters, negation, warning and its original line number.
 */
public class FilterWrapper implements Wrapper {
    private String name;
    private ArrayList<String> params = new ArrayList<>();
    private boolean negation = false;
    private boolean warning = false;
    private int lineNum;

    public FilterWrapper(int lineNum) {
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

    public void addParam(String param) {
        this.params.add(param);
    }

    public String getName() {
        return this.name;
    }

    public boolean isNegation() {
        return this.negation;
    }

    ArrayList<String> getParams() {
        return this.params;
    }

    public boolean isWarning() {
        return this.warning;
    }

    public int getLineNum() {
        return this.lineNum;
    }
}
