package filesprocessing.order;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.Commands;

class ValidateOrderCommand {
    private static final String WARNING_MSG = "Warning in line ";
    private static final int COMMAND_INDEX_SHIFT = 4;

    static void validate(Commands command, int index) {
        if (!validateCommand(command)) {
            command.setOrder(DirectoryProcessor.ORDER_BY_PATH);
            System.err.println(WARNING_MSG + (index + COMMAND_INDEX_SHIFT));
        }
    }

    private static boolean validateCommand(Commands command) {
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
