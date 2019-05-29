package filesprocessing.commandfileparser.commandsgenerator;

import filesprocessing.order.OrderFactory;

/**
 * This class reformats the order String[], by checking if it contains correct and valid name and parameters, and by
 * replacing invalid order with default order (abs).
 */
public class ReformatOrder extends ReformatExecutable<OrderWrapper> {
    private static final String ORDER_REVERSE = "REVERSE";

    public ReformatOrder(String[] filter, int lineNum) {
        super(filter, lineNum);
    }

    protected OrderWrapper identify() {
        OrderWrapper result = new OrderWrapper(this.getLineNum());
        if (this.isOrderAscend())
            result.setNegation(false);
        else if (this.isOrderReversed())
            result.setNegation(true);
        else {
            result.setName(OrderFactory.getDefaultOrder());
            result.setWarning();
            return result;
        }
        result.setName(this.getName());
        return result;

    }

    private boolean isOrderAscend() {
        return this.getExecutable().length == 1 && this.inArray(OrderFactory.getOrderCommands());
    }

    private boolean isOrderReversed() {
        return this.getExecutable().length == 2
                && this.inArray(OrderFactory.getOrderCommands())
                && this.getName() != null
                && this.getExecutable()[1].equals(ORDER_REVERSE);
    }
}
