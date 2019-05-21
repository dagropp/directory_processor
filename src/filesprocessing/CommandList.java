package filesprocessing;

import java.util.*;

public class CommandList {
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final int FILTER_IDX = 0;
    private static final int ORDER_IDX = 1;
    private ArrayList<ArrayList<String>> filterCommands = new ArrayList<>();
    private ArrayList<ArrayList<String>> orderCommands = new ArrayList<>();


    public CommandList(ArrayList<String> lines) {
        this.fillCommands(lines);
    }

    private void fillCommands(ArrayList<String> lines) {
        boolean filterToggle = false;
        int commandNum = -1;
        for (String line : lines) {
            if (line.equals(FILTER)) {
                commandNum++;
                this.filterCommands.add(new ArrayList<>());
                this.orderCommands.add(new ArrayList<>());
                filterToggle = true;
            } else if (line.equals(ORDER)) {
                filterToggle = false;
            } else if (filterToggle) {
                this.filterCommands.get(commandNum).add(line);
            } else if (!filterToggle) {
                this.orderCommands.get(commandNum).add(line);
            }
        }
    }

    public ArrayList<ArrayList<String>> getFilterCommands() {
        return this.filterCommands;
    }

    public ArrayList<ArrayList<String>> getOrderCommands() {
        return this.orderCommands;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getCommandsList() {
        ArrayList<ArrayList<ArrayList<String>>> result = new ArrayList<>();
        for (int i = 0; i < this.filterCommands.size(); i++) {
            result.add(new ArrayList<>());
            result.get(i).add(this.filterCommands.get(i));
            result.get(i).add(this.orderCommands.get(i));
        }
        return result;
    }
}
