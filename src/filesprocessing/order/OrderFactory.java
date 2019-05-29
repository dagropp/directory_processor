package filesprocessing.order;

import filesprocessing.commandfileparser.CommandWrapper;

import java.io.File;

/**
 * This class receives a Command object and orders a files list using the specified command.
 */
public class OrderFactory {
    private static final String ORDER_BY_PATH = "abs";
    private static final String ORDER_BY_TYPE = "type";
    private static final String ORDER_BY_SIZE = "size";

    public static File[] execute(File[] files, CommandWrapper command) {
        OrderWrapper order = ReformatOrder.execute(command);
        return assignOrder(files, order);
    }

    public static String getDefaultOrder() {
        return ORDER_BY_PATH;
    }

    public static String[] getOrderCommands() {
        return new String[]{ORDER_BY_PATH, ORDER_BY_TYPE, ORDER_BY_SIZE};
    }

    private static File[] assignOrder(File[] files, OrderWrapper order) {
        boolean reverse = order.isNegation();
        switch (order.getName()) {
            case ORDER_BY_PATH:
                return OrderByAbsPath.execute(files, reverse);
            case ORDER_BY_TYPE:
                return OrderByType.execute(files, reverse);
            case ORDER_BY_SIZE:
                return OrderBySize.execute(files, reverse);
            default:
                return null;
        }
    }
}
