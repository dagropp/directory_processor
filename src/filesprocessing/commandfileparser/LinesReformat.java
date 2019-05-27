package filesprocessing.commandfileparser;

import filesprocessing.DirectoryProcessor;
import filesprocessing.type2errors.InvalidCommandHeader;

import java.util.ArrayList;

class LinesReformat {
    private static final String SYMMETRY_ERR = "FILTER and ORDER commands amount is asymmetric.";
    private static final String HEADER_ERR = "Command headers are invalid.";
    private int filterCounter = 0;
    private int orderCounter = 0;
    private Line[] formattedLines;

    private enum LineSymbols {FILTER, ORDER, COMMAND}

    LinesReformat(Line[] lines) throws InvalidCommandHeader {
        ArrayList<LineLegend> linesLegend = setLinesLegend(lines);
        if (linesLegend == null) throw new InvalidCommandHeader(SYMMETRY_ERR);
        this.formattedLines = this.reformatLines(linesLegend);
        if (this.formattedLines == null) throw new InvalidCommandHeader(HEADER_ERR);
    }

    Line[] getFormattedLines() {
        return this.formattedLines;
    }

    private Line[] reformatLines(ArrayList<LineLegend> linesLegend) {
        Line[] result = new Line[this.filterCounter * 4];
        for (int i = 0, j = 0; i < result.length; i += 4) {
            if (linesLegend.get(j).SYMBOL == LineSymbols.FILTER &&
                    linesLegend.get(j + 2).SYMBOL == LineSymbols.ORDER) {
                result[i] = new Line(linesLegend.get(j).TEXT, linesLegend.get(j).LINE_NUM);
                result[i + 1] = new Line(linesLegend.get(j + 1).TEXT, linesLegend.get(j + 1).LINE_NUM);
                result[i + 2] = new Line(linesLegend.get(j + 2).TEXT, linesLegend.get(j + 2).LINE_NUM);
                if (j + 3 >= linesLegend.size() || linesLegend.get(j + 3).SYMBOL.equals(LineSymbols.FILTER)) {
                    result[i + 3] = new Line(DirectoryProcessor.ORDER_BY_PATH, -1);
                    j += 3;
                } else {
                    result[i + 3] = new Line(linesLegend.get(j + 3).TEXT, linesLegend.get(j + 3).LINE_NUM);
                    j += 4;
                }
            } else {
                return null;
            }
        }
        return result;
    }

    private ArrayList<LineLegend> setLinesLegend(Line[] lines) {
        ArrayList<LineLegend> linesLegend = new ArrayList<>();
        for (Line line : lines) {
            if (line.equals(DirectoryProcessor.FILTER_HEADER)) {
                linesLegend.add(new LineLegend(LineSymbols.FILTER, line.toString(), line.getLineNum()));
                this.filterCounter++;
            } else if (line.equals(DirectoryProcessor.ORDER_HEADER)) {
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
        for (Line line : this.formattedLines) {
            String add = line + "\n";
            result.append(add);
        }
        return result.toString();
    }
}
