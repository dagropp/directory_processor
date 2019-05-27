package filesprocessing.order;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.CommandWrapper;

class OrderCommandValidator {
    static void validate(CommandWrapper command) {
        if (!validateCommand(command)) {
            command.setOrder(DirectoryProcessor.ORDER_BY_PATH, command.getOrderLine());
            System.err.println(DirectoryProcessor.WARNING_MSG + command.getOrderLine());
        }
    }

    private static boolean validateCommand(CommandWrapper command) {
        String[] order = command.getOrder();
        boolean type1 = order.length == 1 && inArray(order[0]);
        boolean type2 = order.length == 2 && order[1] != null && order[1].equals(DirectoryProcessor.ORDER_REVERSE);
        return (type1 || type2) && inArray(order[0]);
    }

    private static boolean inArray(String string) {
        for (String item : DirectoryProcessor.ORDER_COMMANDS)
            if (string.equals(item))
                return true;
        return false;
    }
}
