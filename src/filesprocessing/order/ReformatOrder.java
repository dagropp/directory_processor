package filesprocessing.order;

import filesprocessing.manager.DirectoryProcessorFactory;
import filesprocessing.commandfileparser.CommandWrapper;

/**
 * This class reformats the order String[], by checking if it contains correct and valid name and parameters, and by
 * replacing invalid order with default order (abs).
 */
public class ReformatOrder {
    private static final String ORDER_REVERSE = "REVERSE";

    public static OrderWrapper execute(CommandWrapper command) {
        OrderWrapper order = identifyOrder(command.getOrder(), command.getOrderLine());
        if (order.isWarning())
            System.err.println(DirectoryProcessorFactory.getWarningMsg() + order.getLineNum());
        return order;
    }

    private static OrderWrapper identifyOrder(String[] order, int lineNum) {
        OrderWrapper result = new OrderWrapper(lineNum);
        if (isOrderAscend(order))
            result.setNegation(false);
        else if (isOrderReversed(order))
            result.setNegation(true);
        else {
            result.setName(OrderFactory.getDefaultOrder());
            result.setWarning();
            return result;
        }
        result.setName(order[0]);
        return result;

    }

    private static boolean isOrderAscend(String[] order) {
        return order.length == 1 && inArray(order[0]);
    }

    private static boolean isOrderReversed(String[] order) {
        return order.length == 2 &&
                inArray(order[0]) &&
                order[1] != null &&
                order[1].equals(ORDER_REVERSE);
    }

    private static boolean inArray(String string) {
        for (String item : OrderFactory.getOrderCommands())
            if (string.equals(item))
                return true;
        return false;
    }
}
