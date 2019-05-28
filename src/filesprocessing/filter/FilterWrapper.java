package filesprocessing.filter;

import filesprocessing.manager.Wrapper;

import java.util.ArrayList;

public class FilterWrapper implements Wrapper {
    private String name;
    private boolean negation = false;
    private boolean warning = false;
    private int lineNum;
    private ArrayList<String> params = new ArrayList<>();


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

    @Override
    public String toString() {
        String args = "";
        for (String param : this.params)
            args += param + " ";
        return "FilterWrapper name: " + this.name + " / Params: " + args + " / Negation: " + this.negation +
                " / Warning: " + this.warning;
    }
}
