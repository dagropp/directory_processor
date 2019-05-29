package filesprocessing.manager;

import filesprocessing.commandfileparser.CommandWrapper;
import filesprocessing.commandfileparser.CommandFileParserFactory;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;
import filesprocessing.type2errors.*;

import java.io.File;

/**
 * This class runs the program, by validating the command line args, listing the file in the source folder, parsing
 * the command file, and then prints output with the specified FILTER and ORDER commands.
 */
public class DirectoryProcessorFactory {
    private static final String ERROR_DEFAULT = "ERROR: ";
    private static final String WARNING_MSG = "Warning in line ";

    public static void execute(String[] args) {
        try {
            String[] validatedArgs = validateArgs(args);
            String sourceDirPath = validatedArgs[0];
            String commandFilePath = validatedArgs[1];
            File[] files = SourceDirectoryParser.execute(sourceDirPath);
            CommandWrapper[] commands = CommandFileParserFactory.execute(commandFilePath);
            printOutput(commands, files);
        } catch (InputException | FileException e) {
            System.err.println(ERROR_DEFAULT + e.getMessage());
        }
    }

    public static String getWarningMsg() {
        return WARNING_MSG;
    }

    private static void printOutput(CommandWrapper[] commands, File[] files) {
        File[] filtered;
        File[] ordered;
        for (CommandWrapper command : commands) {
            filtered = FilterFactory.execute(files, command);
            ordered = OrderFactory.execute(filtered, command);
            for (File file : ordered)
                System.out.println(file.getName());
        }

    }

    private static String[] validateArgs(String[] args) throws InvalidArgs {
        if (args.length != 2)
            throw new InvalidArgs(args.length);
        return args;
    }
}
