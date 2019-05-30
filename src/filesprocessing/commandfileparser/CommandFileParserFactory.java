package filesprocessing.commandfileparser;

import java.io.File;

import filesprocessing.commandfileparser.commandsgenerator.CommandWrapper;
import filesprocessing.commandfileparser.commandsgenerator.CommandsGenerator;
import filesprocessing.type2errors.*;

/**
 * This class receives a command text file and parses it to a workable Command list.
 */
public class CommandFileParserFactory {

    /* Public static methods */

    /**
     * Receives a command text file and parses it to a workable Command list.
     *
     * @param path Path to command file.
     * @return Commands array with FILTER and ORDER commands.
     * @throws FileNotFound         If file not found.
     * @throws NoReadPermission     If can't read file.
     * @throws InvalidCommandHeader If FILTER/ORDER command headers are not in expected format.
     */
    public static CommandWrapper[] execute(String path) throws FileNotFound, NoReadPermission, InvalidCommandHeader {
        // Sets the command file in a method that checks file existence and readability.
        File commandFile = setCommandFile(path);
        LinesConverter fileConverter = new LinesConverter(commandFile); // Converts file lines to Array.
        ReformatLines reformatLines = new ReformatLines(fileConverter.getLines()); // Reformats lines.
        // Generates commands Array from the converted lines array.
        CommandsGenerator commandsGenerator = new CommandsGenerator(reformatLines.getResult());
        return commandsGenerator.getCommands(); // Returns the commands.
    }

    /* Private static methods */

    /**
     * Sets the command file. Checks file existence and readability.
     *
     * @param path Actual path of command file in disk.
     * @throws FileNotFound     If file not found.
     * @throws NoReadPermission If can't read file.
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
