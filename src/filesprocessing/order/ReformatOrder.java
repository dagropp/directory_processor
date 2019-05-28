package filesprocessing.order;

import filesprocessing.manager.DirectoryProcessorFactory;
import filesprocessing.commandfileparser.CommandWrapper;

public class ReformatOrder {
    private static String[] orderCommands = {
            DirectoryProcessorFactory.ORDER_BY_PATH,
            DirectoryProcessorFactory.ORDER_BY_SIZE,
            DirectoryProcessorFactory.ORDER_BY_TYPE
    };

    public static OrderWrapper execute(CommandWrapper command) {
        OrderWrapper order = identifyOrder(command.getOrder(), command.getOrderLine());
        if (order.isWarning())
            System.err.println(DirectoryProcessorFactory.WARNING_MSG + order.getLineNum());
        return order;
    }

    private static OrderWrapper identifyOrder(String[] order, int lineNum) {
        OrderWrapper result = new OrderWrapper(lineNum);
        if (isOrderAscend(order))
            result.setNegation(false);
        else if (isOrderReversed(order))
            result.setNegation(true);
        else {
            result.setName(DirectoryProcessorFactory.ORDER_BY_PATH);
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
                order[1].equals(DirectoryProcessorFactory.ORDER_REVERSE);
    }

    private static boolean inArray(String string) {
        for (String item : orderCommands)
            if (string.equals(item))
                return true;
        return false;
    }
}
