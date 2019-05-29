package filesprocessing.manager;

import filesprocessing.commandfileparser.commandsgenerator.CommandWrapper;
import filesprocessing.commandfileparser.CommandFileParserFactory;
import filesprocessing.commandfileparser.commandsgenerator.ExecutableWrapper;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;
import filesprocessing.type2errors.*;

import java.io.File;

/**
 * This class runs the program, by validating the command line args, listing the file in the source folder, parsing
 * the command file, and then prints output with the specified FILTER and ORDER commands.
 */
public class DirectoryProcessorFactory {
    /* Class members - constant variables*/
    private static final String WARNING_MSG = "Warning in line "; // Default warning message (Type 1 errors).
    private static final String ERROR_DEFAULT = "ERROR: "; // Default error message (Type 2 errors).

    /* Public static methods */

    /**
     * Factory execution method: delegates methods from all program packages, to receive source directory and command
     * file and generate proper filtered and ordered output.
     *
     * @param args Command line args consisting of [sourcedir, commandfile].
     */
    public static void execute(String[] args) {
        // Tries to validate args, list source folder files, parse command file and print output.
        try {
            validateArgs(args); // Validate command line args, and throw Exception if invalid.
            // Creates File array from source folder files, using SourceDirectoryParser from this package.
            File[] files = SourceDirectoryParser.execute(args[0]);
            // Creates CommandWrapper array commands from command file, using 'commandfileparser' package factory.
            CommandWrapper[] commands = CommandFileParserFactory.execute(args[1]);
            // Prints filtered and ordered files output, using 'filter' and 'order' package factories.
            printOutput(commands, files);
        } catch (InputException | FileException e) { // Some line failed, catches Exception and prints its message.
            System.err.println(ERROR_DEFAULT + e.getMessage());
        }
    }

    /* Private static methods */

    /**
     * Prints filtered and ordered files output, using 'filter' and 'order' package factories.
     *
     * @param commands Commands to execute, including both filters and orders.
     * @param files    Files to perform commands on.
     */
    private static void printOutput(CommandWrapper[] commands, File[] files) {
        File[] filtered;
        File[] ordered;
        // Iterates over each command, and print its results and warnings (if any) individually.
        for (CommandWrapper command : commands) {
            // Creates filtered files list, using 'filter' package factory.
            checkErrorType1(command.getFilter());
            filtered = FilterFactory.execute(files, command);
            // Orders the filtered files list, using 'order' package factory.
            checkErrorType1(command.getOrder());
            ordered = OrderFactory.execute(filtered, command);
            // Prints the ordered filtered files list one-by-one.
            for (File file : ordered)
                System.out.println(file.getName());
        }
    }

    /**
     * Validate command line args, and throws InvalidArgs Exception if invalid.
     *
     * @param args Command line args to validate, consisting of [sourcedir, commandfile].
     * @throws InvalidArgs If args is not in expected length.
     */
    private static void validateArgs(String[] args) throws InvalidArgs {
        if (args.length != 2)
            throw new InvalidArgs(args.length);
    }

    /**
     * Checks if command's warning flag is true. If so it means type 1 error: prints warning message.
     *
     * @param command FILTER/ORDER command to check warning.
     */
    private static void checkErrorType1(ExecutableWrapper command) {
        if (command.isWarning())
            System.err.println(WARNING_MSG + command.getLineNum());
    }
}
