package filesprocessing.commandfileparser;

import filesprocessing.order.OrderFactory;
import filesprocessing.type2errors.InvalidCommandHeader;

import java.util.ArrayList;

/**
 * This class reformats the lines array, by checking if it contains correct and valid command headers, and by adding
 * default commands to missing lines.
 */
public class LinesReformat {
    private static final String FILTER_HEADER = "FILTER"; // FilterWrapper command header.
    private static final String ORDER_HEADER = "ORDER"; // Order command header.
    private int filterCounter = 0;
    private int orderCounter = 0;
    private LineWrapper[] formattedLines;

    private enum LineSymbols {FILTER, ORDER, COMMAND}

    public LinesReformat(LineWrapper[] lines) throws InvalidCommandHeader {
        ArrayList<LineLegend> linesLegend = setLinesLegend(lines);
        if (linesLegend == null) throw new InvalidCommandHeader();
        this.formattedLines = this.reformatLines(linesLegend);
    }

    public LineWrapper[] getFormattedLines() {
        return this.formattedLines;
    }

    private LineWrapper[] reformatLines(ArrayList<LineLegend> linesLegend) {
        LineWrapper[] result = new LineWrapper[this.filterCounter * 4];
        for (int i = 0, j = 0; i < result.length; i += 4) {
            if (linesLegend.get(j).SYMBOL == LineSymbols.FILTER &&
                    linesLegend.get(j + 2).SYMBOL == LineSymbols.ORDER) {
                result[i] = new LineWrapper(linesLegend.get(j).TEXT, linesLegend.get(j).LINE_NUM);
                result[i + 1] = new LineWrapper(linesLegend.get(j + 1).TEXT, linesLegend.get(j + 1).LINE_NUM);
                result[i + 2] = new LineWrapper(linesLegend.get(j + 2).TEXT, linesLegend.get(j + 2).LINE_NUM);
                if (j + 3 >= linesLegend.size() || linesLegend.get(j + 3).SYMBOL.equals(LineSymbols.FILTER)) {
                    result[i + 3] = new LineWrapper(OrderFactory.getDefaultOrder(), -1);
                    j += 3;
                } else {
                    result[i + 3] = new LineWrapper(linesLegend.get(j + 3).TEXT, linesLegend.get(j + 3).LINE_NUM);
                    j += 4;
                }
            } else {
                return null;
            }
        }
        return result;
    }

    private ArrayList<LineLegend> setLinesLegend(LineWrapper[] lines) {
        ArrayList<LineLegend> linesLegend = new ArrayList<>();
        for (LineWrapper line : lines) {
            if (line.equals(FILTER_HEADER)) {
                linesLegend.add(new LineLegend(LineSymbols.FILTER, line.toString(), line.getLineNum()));
                this.filterCounter++;
            } else if (line.equals(ORDER_HEADER)) {
                linesLegend.add(new LineLegend(LineSymbols.ORDER, line.toString(), line.getLineNum()));
                this.orderCounter++;
            } else
                linesLegend.add(new LineLegend(LineSymbols.COMMAND, line.toString(), line.getLineNum()));
        }
        if (this.filterCounter == this.orderCounter)
            return linesLegend;
        return null;
    }

    private class LineLegend {
        private LineSymbols SYMBOL;
        private final String TEXT;
        private final int LINE_NUM;

        private LineLegend(LineSymbols linesSymbol, String lineString, int lineNum) {
            this.SYMBOL = linesSymbol;
            this.TEXT = lineString;
            this.LINE_NUM = lineNum;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("*FORMATTED LINES*\n");
        for (LineWrapper line : this.formattedLines) {
            String add = line + "\n";
            result.append(add);
        }
        return result.toString();
    }
}
