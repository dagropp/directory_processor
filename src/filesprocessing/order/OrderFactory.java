package filesprocessing.order;

import filesprocessing.manager.DirectoryProcessorFactory;
import filesprocessing.commandfileparser.CommandWrapper;

import java.io.File;

public class OrderFactory {
    public static File[] execute(File[] files, CommandWrapper command) {
        OrderWrapper order = ReformatOrder.execute(command);
        return assignOrder(files, order);
    }

    private static File[] assignOrder(File[] files, OrderWrapper order) {
        boolean reverse = order.isNegation();
        switch (order.getName()) {
            case DirectoryProcessorFactory.ORDER_BY_PATH:
                return OrderByAbsPath.execute(files, reverse);
            case DirectoryProcessorFactory.ORDER_BY_TYPE:
                return OrderByType.execute(files, reverse);
            case DirectoryProcessorFactory.ORDER_BY_SIZE:
                return OrderBySize.execute(files, reverse);
            default:
                return null;
        }
    }
}
