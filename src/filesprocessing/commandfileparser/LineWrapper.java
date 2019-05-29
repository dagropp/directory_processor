package filesprocessing.commandfileparser;

/**
 * This class creates a wrapper object with line's text and its original line number.
 */
public class LineWrapper {
    private String text; // Line text.
    private int lineNum; // Line number.

    /**
     * Constructor for LineWrapper. Initialises the text and line number.
     *
     * @param text    Line text.
     * @param lineNum Line number.
     */
    public LineWrapper(String text, int lineNum) {
        this.text = text;
        this.lineNum = lineNum;
    }

    /**
     * @return This line number.
     */
    public int getLineNum() {
        return this.lineNum;
    }

    /**
     * @return This line text
     */
    @Override
    public String toString() {
        return this.text;
    }

    /**
     * Overloading Object equals to compare strings to line.
     *
     * @param input To compare.
     * @return True if input equals line's text, false otherwise.
     */
    public boolean equals(String input) {
        return this.text.equals(input);
    }
}
