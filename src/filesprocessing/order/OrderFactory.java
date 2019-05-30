package filesprocessing.order;

import filesprocessing.commandfileparser.commandsgenerator.CommandWrapper;
import filesprocessing.commandfileparser.commandsgenerator.OrderWrapper;

import java.io.File;

/**
 * This class receives a Command object and orders a files list using the specified command.
 */
public class OrderFactory {
    /* Class members - constant variables */
    // String representation of each order command:
    private static final String ORDER_BY_PATH = "abs";
    private static final String ORDER_BY_TYPE = "type";
    private static final String ORDER_BY_SIZE = "size";

    /* Public static methods */

    /**
     * Executes the order method based on given command.
     *
     * @param files   File array to sort.
     * @param command Order command.
     * @return A sorted File array by specified Order command.
     */
    public static File[] execute(File[] files, CommandWrapper command) {
        return assignOrder(files, command.getOrder());
    }

    /**
     * @return Default order command (abs).
     */
    public static String getDefaultOrder() {
        return ORDER_BY_PATH;
    }

    /**
     * @return Array of all possible order commands.
     */
    public static String[] getOrderCommands() {
        return new String[]{ORDER_BY_PATH, ORDER_BY_TYPE, ORDER_BY_SIZE};
    }

    /* Private static methods */

    /**
     * Assigns the order command based on the given command.
     *
     * @param files File array to sort.
     * @param order Order command.
     * @return A sorted File array by specified Order command.
     */
    private static File[] assignOrder(File[] files, OrderWrapper order) {
        boolean reverse = order.isNegation(); // Assign boolean with command negation.
        switch (order.getName()) {
            case ORDER_BY_PATH: // If by path, executes order by path method.
                return OrderByAbsPath.execute(files, reverse);
            case ORDER_BY_TYPE: // If by type, executes order by path method.
                return OrderByType.execute(files, reverse);
            case ORDER_BY_SIZE: // If by size, executes order by path method.
                return OrderBySize.execute(files, reverse);
            default: // Default is null, though should not occur due to priot validation and reformatting.
                return null;
        }
    }
}
