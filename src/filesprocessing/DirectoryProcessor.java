package filesprocessing;

import filesprocessing.commandfileparser.Commands;
import filesprocessing.commandfileparser.CommandFileParser;
import filesprocessing.filter.Filter;
import filesprocessing.filter.ReformatFilter;
import filesprocessing.order.OrderFactory;

import java.io.File;

public class DirectoryProcessor {
    public static final String FILTER_HEADER = "FILTER"; // Filter command header.
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
    private static final File[] FILES = {
            new File("filesprocessing/file_tester"),
            new File("filesprocessing/FileFilter.java"),
            new File("filesprocessing/Tester.java"),
            new File("filesprocessing/DirectoryProcessor.java")
    };

    public static void main(String[] args) {
        String sourceDirPath = args[0];
        String commandFilePath = args[1];
        Commands[] commands = setCommandsList(commandFilePath);
        ReformatFilter reformatFilter = new ReformatFilter(commands);
        for (Filter filter : reformatFilter.getFilters())
            System.out.println(filter);
    }

    private static void generateOutput(Commands[] commands) {
        for (int i = 0; i < commands.length; i++)
            for (File file : new OrderFactory(FILES, commands[i], i).getResult())
                System.out.println(file.getName());
    }

    private static Commands[] setCommandsList(String commandFilePath) {
        try {
            return new CommandFileParser(commandFilePath).getCommands();
        } catch (Exception e) {
            System.err.println(ERROR_DEFAULT + e.getMessage());
            System.exit(0);
            return null;
        }
    }

}
