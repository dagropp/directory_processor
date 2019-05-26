package filesprocessing.filter;

import java.util.ArrayList;

public class Filter {
    private String name;
    private ArrayList<String> params = new ArrayList<>();
    private boolean negation = false;
    private boolean warning = false;

    Filter() {
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

    boolean getNegation() {
        return this.negation;
    }

    ArrayList<String> getParams() {
        return this.params;
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
