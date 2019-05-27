package filesprocessing.commandfileparser;

public class LineWrapper {
    private String text;
    private int lineNum;

    LineWrapper(String text, int lineNum) {
        this.text = text;
        this.lineNum = lineNum;
    }

    public int getLineNum() {
        return this.lineNum;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public boolean equals(String input) {
        return this.text.equals(input);
    }
}
