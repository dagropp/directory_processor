package filesprocessing.commandfileparser;

import filesprocessing.DirectoryProcessor;
import filesprocessing.type2exceptions.InvalidCommandHeader;

import java.util.ArrayList;

class LinesReformat {
    private static final String SYMMETRY_ERR = "FILTER and ORDER commands amount is asymmetric.";
    private static final String HEADER_ERR = "Command headers are invalid.";
    private int filterCounter = 0;
    private int orderCounter = 0;
    private String[] formattedLines;

    private enum LineSymbols {FILTER, ORDER, COMMAND}

    LinesReformat(String[] lines) throws InvalidCommandHeader {
        ArrayList<LineLegend> linesLegend = setLinesLegend(lines);
        if (linesLegend == null) throw new InvalidCommandHeader(SYMMETRY_ERR);
        this.formattedLines = this.reformatLines(linesLegend);
        if (this.formattedLines == null) throw new InvalidCommandHeader(HEADER_ERR);
    }

    String[] getFormattedLines() {
        return this.formattedLines;
    }

    private String[] reformatLines(ArrayList<LineLegend> linesLegend) {
        String[] result = new String[this.filterCounter * 4];
        for (int i = 0, j = 0; i < result.length; i += 4) {
            if (linesLegend.get(j).SYMBOL == LineSymbols.FILTER &&
                    linesLegend.get(j + 2).SYMBOL == LineSymbols.ORDER) {
                result[i] = linesLegend.get(j).TEXT;
                result[i + 1] = linesLegend.get(j + 1).TEXT;
                result[i + 2] = linesLegend.get(j + 2).TEXT;
                if (j + 3 >= linesLegend.size() || linesLegend.get(j + 3).SYMBOL.equals(LineSymbols.FILTER)) {
                    result[i + 3] = DirectoryProcessor.ORDER_BY_PATH;
                    j += 3;
                } else {
                    result[i + 3] = linesLegend.get(j + 3).TEXT;
                    j += 4;
                }
            } else {
                return null;
            }
        }
        return result;
    }

    private ArrayList<LineLegend> setLinesLegend(String[] lines) {
        ArrayList<LineLegend> linesLegend = new ArrayList<>();
        for (String line : lines) {
            if (line.equals(DirectoryProcessor.FILTER_HEADER)) {
                linesLegend.add(new LineLegend(LineSymbols.FILTER, line));
                this.filterCounter++;
            } else if (line.equals(DirectoryProcessor.ORDER_HEADER)) {
                linesLegend.add(new LineLegend(LineSymbols.ORDER, line));
                this.orderCounter++;
            } else
                linesLegend.add(new LineLegend(LineSymbols.COMMAND, line));
        }
        if (this.filterCounter == this.orderCounter)
            return linesLegend;
        return null;
    }

    private class LineLegend {
        private LineSymbols SYMBOL;
        private final String TEXT;

        private LineLegend(LineSymbols linesSymbol, String lineString) {
            this.SYMBOL = linesSymbol;
            this.TEXT = lineString;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("*FORMATTED LINES*\n");
        for (String line : this.formattedLines) {
            String add = line + "\n";
            result.append(add);
        }
        return result.toString();
    }
}
