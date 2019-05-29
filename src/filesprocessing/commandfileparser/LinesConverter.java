package filesprocessing.commandfileparser;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;

/**
 * This class gets a text file and converts its lines to LineWrapper array.
 */
public class LinesConverter {
    /* Class members - constant variables */
    private static final String IO_ERROR = "IO error occurred."; // IO Error message.
    /* Class members - variables */
    private LineWrapper[] lines; // Array with file's lines.

    /* Constructors */

    /**
     * Constructor for LinesConverter. Initializes the lines array and converts the File lines to it.
     *
     * @param file The command file to convert to list.
     */
    public LinesConverter(File file) {
        this.setLines(file);
    }

    /* Public instance methods */

    /**
     * @return Array with file's lines.
     */
    public LineWrapper[] getLines() {
        return this.lines;
    }

    /* Private instance methods */

    /**
     * Sets lines array with each file's line String value and its line number.
     *
     * @param file The command file to convert.
     */
    private void setLines(File file) {
        // Tries to create a buffered line reader with the command file. If successful does:
        try (LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
            this.lines = new LineWrapper[this.countLines(file)]; // Assign new array, the length of the lines.
            // Iterates over this array and assign each index with File line and line number.
            for (int i = 0; i < this.lines.length; i++)
                this.lines[i] = new LineWrapper(reader.readLine(), reader.getLineNumber());
            // If IO exception occurs, print error message.
        } catch (IOException e) {
            System.err.println(IO_ERROR);
            System.err.println(e.getMessage());
        }
    }

    /**
     * Counts line numbers of given file.
     *
     * @param file The command file to count lines of.
     * @return Line numbers of the given file.
     */
    private int countLines(File file) {
        // Tries to create a buffered line reader with the command file. If successful does:
        try (LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
            return (int) reader.lines().count(); // Count line numbers and returns its value.
        } catch (IOException e) {
            return 0; // If IO exception occurs.
        }
    }
}
