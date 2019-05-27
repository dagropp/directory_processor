package filesprocessing;

import filesprocessing.commandfileparser.CommandWrapper;
import filesprocessing.commandfileparser.CommandFileParser;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;

import java.io.File;

public class DirectoryProcessor {
    public static final String FILTER_HEADER = "FILTER"; // FilterWrapper command header.
    public static final String ORDER_HEADER = "ORDER"; // Order command header.
    public static final char SEPARATOR = '#';
    public static final String ORDER_BY_PATH = "abs";
    public static final String ORDER_BY_TYPE = "type";
    public static final String ORDER_BY_SIZE = "size";
    public static final String[] ORDER_COMMANDS = {ORDER_BY_PATH, ORDER_BY_TYPE, ORDER_BY_SIZE};
    public static final String ORDER_REVERSE = "REVERSE";
    public static final String FILTER_SIZE_GREATER = "greater_than";
    public static final String FILTER_SIZE_SMALLER = "smaller_than";
    public static final String FILTER_SIZE_BETWEEN = "between";
    public static final String FILTER_VALUE_FILE_NAME = "file";
    public static final String FILTER_VALUE_CONTAINS = "contains";
    public static final String FILTER_VALUE_PREFIX = "prefix";
    public static final String FILTER_VALUE_SUFFIX = "suffix";
    public static final String FILTER_PERMISSION_WRITE = "writable";
    public static final String FILTER_PERMISSION_EXECUTE = "executable";
    public static final String FILTER_HIDDEN = "hidden";
    public static final String FILTER_ALL = "all";
    public static final String[] FILTER_COMMANDS = {FILTER_SIZE_GREATER, FILTER_SIZE_SMALLER, FILTER_SIZE_BETWEEN,
            FILTER_VALUE_FILE_NAME, FILTER_VALUE_CONTAINS, FILTER_VALUE_PREFIX, FILTER_VALUE_SUFFIX,
            FILTER_PERMISSION_WRITE, FILTER_PERMISSION_EXECUTE, FILTER_HIDDEN, FILTER_ALL};
    public static final String FILTER_NOT = "NOT";
    public static final String FILTER_YES = "YES";
    public static final String FILTER_NO = "NO";


    private static final String ERROR_DEFAULT = "ERROR: ";
    public static final String WARNING_MSG = "Warning in line ";

    public static void main(String[] args) {
        String sourceDirPath = args[0];
        String commandFilePath = args[1];
        File[] files = setFilesList(sourceDirPath);
        CommandWrapper[] commands = setCommandsList(commandFilePath);
        generateOutput(commands, files);
    }

    private static void generateOutput(CommandWrapper[] commands, File[] files) {
        File[] filtered;
        for (CommandWrapper command : commands) {
            filtered = FilterFactory.execute(files, command);
            for (File file : new OrderFactory(filtered, command).getResult())
                System.out.println(file.getName());
            System.out.println();
        }

    }

    private static CommandWrapper[] setCommandsList(String commandFilePath) {
        try {
            return CommandFileParser.execute(commandFilePath);
        } catch (Exception e) {
            System.err.println(ERROR_DEFAULT + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    private static File[] setFilesList(String sourceDirPath) {
        try {
            return SourceDirectoryParser.execute(sourceDirPath);
        } catch (Exception e) {
            System.err.println(ERROR_DEFAULT + e.getMessage());
            System.exit(0);
            return null;
        }
    }
}
