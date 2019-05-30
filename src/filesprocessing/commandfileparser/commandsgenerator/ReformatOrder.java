package filesprocessing.commandfileparser.commandsgenerator;

import filesprocessing.order.OrderFactory;

/**
 * This class reformats the order String[], by checking if it contains correct and valid name and parameters, and by
 * replacing invalid order with default order (abs).
 */
public class ReformatOrder extends ReformatExecutable<OrderWrapper> {
    /* Class members - constant variables*/
    private static final String ORDER_REVERSE = "REVERSE";

    /* Constructors */

    /**
     * Constructor for ReformatOrder. Calls parent constructor to set order array, name and line number.
     *
     * @param order   Order array.
     * @param lineNum Order line number.
     */
    public ReformatOrder(String[] order, int lineNum) {
        super(order, lineNum);
    }

    /* Protected instance methods*/

    /**
     * Identifies the relevant order command. If not in order commands legend, assigns default order and sets
     * type 1 error warning.
     *
     * @return Wrapper with identified order.
     */
    protected OrderWrapper identify() {
        OrderWrapper result = new OrderWrapper(this.getLineNum()); // Initialises order object with line number.
        // Checks if the command is an ascending order command and exists in order commands legend.
        if (this.isOrderAscend())
            result.setNegation(false); // If so, sets ascend negation (reverse) to false.
            // Checks if the command is a reversed order command and exists in order commands legend.
        else if (this.isOrderReversed())
            result.setNegation(true); // If so, sets ascend negation (reverse) to true.
            // Some problems occurred, assign default order command (abs) and sets warning.
        else {
            result.setName(OrderFactory.getDefaultOrder());
            result.setWarning();
            return result;
        }
        result.setName(this.getName()); // Sets order command name to the specified name.
        return result; // All tests passed, return the order.

    }

    /* Private instance methods*/

    /**
     * @return True if command is in expected length and exists in order commands legend.
     */
    private boolean isOrderAscend() {
        return this.getExecutable().length == 1 && this.inArray(OrderFactory.getOrderCommands());
    }

    /**
     * @return True if command is in expected length, no fields are null and exists in order commands legend.
     */
    private boolean isOrderReversed() {
        return this.getExecutable().length == 2
                && this.inArray(OrderFactory.getOrderCommands())
                && this.getName() != null
                && this.getExecutable()[1] != null
                && this.getExecutable()[1].equals(ORDER_REVERSE);
    }
}
