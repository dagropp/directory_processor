package filesprocessing.commandfileparser;

import java.io.*;

/**
 * This class gets a File and converts its lines to ArrayList.
 */
class LinesConverter {
    /* Class members - constant variables */
    private static final String IO_ERROR = "IO error occurred.";
    /* Class members - variables */
    private Line[] lines; // List with file's lines.

    /* Constructors */

    /**
     * Constructor for LinesConverter. Initializes the lines list and converts the File lines to it.
     *
     * @param file The command file to convert to list.
     */
    LinesConverter(File file) {
        this.setLines(file);
    }

    /* Package-private instance methods */

    /**
     * @return ArrayList with file's lines.
     */
    Line[] getLines() {
        return this.lines;
    }

    /* Private instance methods */

    /**
     * Sets lines list with each file's line String value.
     *
     * @param file The command file to convert to list.
     */
    private void setLines(File file) {
        // Tries to create a buffered reader with the command file. If successful does:
        try (LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
            this.lines = new Line[this.countLines(file)];
            for (int i = 0; i < this.lines.length; i++)
                this.lines[i] = new Line(reader.readLine(), reader.getLineNumber());
        } catch (IOException e) {
            System.err.println(IO_ERROR);
            System.err.println(e.getMessage());
        }
    }

    private int countLines(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return (int) reader.lines().count();
        } catch (IOException e) {
            return 0;
        }
    }
}
