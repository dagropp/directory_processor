package filesprocessing.commandfileparser;

import java.io.*;
import java.util.ArrayList;

import filesprocessing.exceptions.*;

/**
 * This class parses a command file to a workable commands list.
 */
public class FileParser {
    /* Class members - variables */
    private File commandFile; // Command file representation.
    private ArrayList<Commands> commands; // Commands list as ArrayList of Commands objects.

    /* Constructors */

    /**
     * Constructor for File Parser. Validates the file in the given path, converts its lines to ArrayList,
     * and extracts the commands to an ArrayList that holds each command (filter and order) in 1 index.
     *
     * @param path Actual path of command file in disk.
     * @throws FileNotFoundException If file not found.
     * @throws NoReadPermission      If there is no read permission to file.
     */
    public FileParser(String path) throws FileNotFoundException, NoReadPermission {
        this.setCommandFile(path); // Sets the command file in a method that checks file existence and readability.
        // Converts file lines to ArrayList.
        CommandFileConverter fileConverter = new CommandFileConverter(this.commandFile);
        // Generates commands ArrayList from the converted lines array.
        CommandsGenerator commandsGenerator = new CommandsGenerator(fileConverter.getLines());
        this.commands = commandsGenerator.getCommands(); // Assign the generated commands list to commands variable.
    }

    /* Public instance methods */

    /**
     * @return Commands ArrayList, with each index holding Commands object with FILTER and ORDER commands.
     */
    public ArrayList<Commands> getCommands() {
        return this.commands;
    }

    /**
     * @param index Of the specific Commands object.
     * @return Specific Commands object with FILTER and ORDER commands.
     */
    public Commands getCommands(int index) {
        if (index >= 0 && index < this.commands.size()) // Checks if index is on ArrayList range.
            return this.commands.get(index); // If so, return the specified Commands object.
        return null; // If not, return null.
    }

    /* Private instance methods */

    /**
     * Sets the command file. Checks file existence and readability - if not, throws relevant exceptions.
     *
     * @param path Actual path of command file in disk.
     * @throws FileNotFoundException If file not found.
     * @throws NoReadPermission      If there is no read permission to file.
     */
    private void setCommandFile(String path) throws FileNotFoundException, NoReadPermission {
        this.commandFile = new File(path); // Sets the command file.
        if (!this.commandFile.exists()) // If file doesn't exist, throws FileNotFound exception.
            throw new FileNotFoundException("File not found in: " + this.commandFile.getAbsolutePath());
        if (!this.commandFile.canRead()) // If can't read file, throws NoReadPermission exception.
            throw new NoReadPermission(this.commandFile);
    }
}
