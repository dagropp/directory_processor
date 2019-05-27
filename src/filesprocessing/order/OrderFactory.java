package filesprocessing.order;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.Command;

import java.io.File;

public class OrderFactory {
    private Command command;
    private boolean reverse;
    private File[] result;

    public OrderFactory(File[] files, Command command) {
        this.command = command;
        OrderCommandValidator.validate(this.command);
        this.reverse = this.setReverse();
        this.setResult(files);
    }

    public File[] getResult() {
        return this.result;
    }

    private boolean setReverse() {
        String[] order = this.command.getOrder();
        return order.length == 2 && order[1].equals(DirectoryProcessor.ORDER_REVERSE);
    }

    private void setResult(File[] files) {
        switch (this.command.getOrder()[0]) {
            case DirectoryProcessor.ORDER_BY_PATH:
                this.result = OrderByAbsPath.execute(files, this.reverse);
                break;
            case DirectoryProcessor.ORDER_BY_TYPE:
                this.result = OrderByType.execute(files, this.reverse);
                break;
            case DirectoryProcessor.ORDER_BY_SIZE:
                this.result = OrderBySize.execute(files, this.reverse);
                break;
        }
    }
}
