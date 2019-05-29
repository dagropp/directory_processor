package filesprocessing.commandfileparser;

import java.io.File;

import filesprocessing.type2errors.*;

/**
 * This class receives a command text file and parses it to a workable Command list.
 */
public class CommandFileParserFactory {

    /* Public static methods */

    /**
     * CHANGE :: Constructor for File Parser. Validates the file in the given path, converts its lines to ArrayList,
     * and extracts the commands to an ArrayList that holds each command (filter and order) in 1 index.
     *
     * @param path Actual path of command file in disk.
     * @throws FileNotFound     If file not found.
     * @throws NoReadPermission If there is no read permission to file.
     */
    public static CommandWrapper[] execute(String path) throws FileNotFound, NoReadPermission, InvalidCommandHeader {
        // Sets the command file in a method that checks file existence and readability.
        File commandFile = setCommandFile(path);
        // Converts file lines to Array.
        LinesConverter fileConverter = new LinesConverter(commandFile);
        LinesReformat reformatLines = new LinesReformat(fileConverter.getLines());
        // Generates commands Array from the converted lines array.
        CommandsGenerator commandsGenerator = new CommandsGenerator(reformatLines.getFormattedLines());
        // Assign the generated commands list to commands variable.
        return commandsGenerator.getCommands();
    }

    /* Private static methods */

    /**
     * Sets the command file. Checks file existence and readability - if not, throws relevant type2errors.
     *
     * @param path Actual path of command file in disk.
     * @throws FileNotFound     If file not found.
     * @throws NoReadPermission If there is no read permission to file.
     */
    private static File setCommandFile(String path) throws FileNotFound, NoReadPermission {
        File commandFile = new File(path); // Sets the command file.
        if (!commandFile.exists()) // If file doesn't exist, throws FileNotFound exception.
            throw new FileNotFound(commandFile);
        if (!commandFile.canRead()) // If can't read file, throws NoReadPermission exception.
            throw new NoReadPermission(commandFile);
        return commandFile;
    }
}
