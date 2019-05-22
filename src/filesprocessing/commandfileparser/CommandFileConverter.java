package filesprocessing.commandfileparser;

import java.io.*;
import java.util.ArrayList;

/**
 * This class gets a File and converts its lines to ArrayList.
 */
class CommandFileConverter {
    /* Class members - constant variables */
    private static final String FILE_NOT_FOUND_ERROR = "File not found in location:\n";
    private static final String IO_ERROR = "IO error occurred.";
    /* Class members - variables */
    private ArrayList<String> lines; // List with file's lines.

    /* Constructors */

    /**
     * Constructor for CommandFileConverter. Initializes the lines list and converts the File lines to it.
     *
     * @param file The command file to convert to list.
     */
    CommandFileConverter(File file) {
        this.lines = new ArrayList<>();
        this.setLines(file);
    }

    /* Package-private instance methods */

    /**
     * @return ArrayList with file's lines.
     */
    ArrayList<String> getLines() {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Assign first line to String.
            while (line != null) { // As long as line not null, continue iterating over each line.
                if (!line.equals("")) // If line is not empty, add it to lines list.
                    this.lines.add(line);
                line = reader.readLine(); // Go to next line, and continue while-loop.
            }
            // If File not found, do nothing and print error message.
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND_ERROR);
            // If IO error occurs, do nothing and print error message.
        } catch (IOException e) {
            System.out.println(IO_ERROR);
            System.out.println(e.getMessage());
        }
    }
}
