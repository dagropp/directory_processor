package filesprocessing.filter;

import java.util.ArrayList;
import java.util.Arrays;

public class Filter {
    private String name;
    private ArrayList<String> params = new ArrayList<>();
    private boolean negation = false;
    private boolean warning = false;
    private int lineNum;

    Filter(int lineNum) {
        this.lineNum = lineNum;
    }

    void setName(String name) {
        this.name = name;
    }

    void setNegation(boolean negation) {
        this.negation = negation;
    }

    void setWarning() {
        this.warning = true;
    }

    void addParam(String param) {
        this.params.add(param);
    }

    String getName() {
        return this.name;
    }

    public boolean getNegation() {
        return this.negation;
    }

    public String[] getParams() {
        String[] result = new String[this.params.size()];
        return this.params.toArray(result);
    }

    public boolean getWarning() {
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
        return "Filter name: " + this.name + " / Params: " + args + " / Negation: " + this.negation +
                " / Warning: " + this.warning;
    }
}
