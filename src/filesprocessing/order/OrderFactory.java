package filesprocessing.order;

import filesprocessing.DirectoryProcessor;
import filesprocessing.commandfileparser.Commands;

import java.io.File;

public class OrderFactory {
    private Commands command;
    private boolean reverse;
    private File[] result;

    public OrderFactory(File[] files, Commands command, int index) {
        this.command = command;
        ValidateOrderCommand.validate(this.command, index);
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
                this.result = SortFilesPath.execute(files, this.reverse);
                break;
            case DirectoryProcessor.ORDER_BY_TYPE:
                this.result = SortFilesType.execute(files, this.reverse);
                break;
            case DirectoryProcessor.ORDER_BY_SIZE:
                this.result = SortFilesSize.execute(files, this.reverse);
                break;
        }
    }
}
