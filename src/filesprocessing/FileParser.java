package filesprocessing;

import java.io.*;
import java.util.*;

/**
 * Receives file and parse each line to String array.
 */
public class FileParser {
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final int FILTER_IDX = 0;
    private static final int ORDER_IDX = 1;
    private File file;
    private ArrayList<ArrayList<ArrayList<String>>> commands;


    public FileParser(String path) throws Exception {
        this.file = new File(path);
        if (!this.file.exists())
            throw new FileNotFoundException("No file in path");
        if (!this.file.canRead())
            throw new Exception("File is read only");
        this.commands = this.setCommands();
    }

    public ArrayList<ArrayList<ArrayList<String>>> getCommands() {
        return this.commands;
    }

    private ArrayList<ArrayList<ArrayList<String>>> setCommands() {
        ArrayList<String> lines = this.linesToArray();
        CommandList commandList = new CommandList(lines);
        return commandList.getCommandsList();
    }

    private ArrayList<String> linesToArray() {
        ArrayList<String> tempList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals(""))
                    tempList.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return null;
        } catch (IOException e) {
            System.out.println("IO Error occurred:");
            System.out.println(e.getMessage());
            return null;
        } finally {
            return tempList;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (ArrayList<ArrayList<String>> outerList : this.commands) {
            result += "*new command*\n";
            for (ArrayList<String> innerList : outerList) {
                result += "*type*\n";
                for (String command : innerList)
                    result += command + "\n";
            }
        }
        return result;
    }
}
