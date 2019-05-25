package filesprocessing.filter;

import java.io.File;
import java.util.ArrayList;

public class FileSizeFilter {
    private File[] files;

    public FileSizeFilter(File[] files) {
        this.files = files;
    }

    public ArrayList<File> smallerThan(double num, boolean negation) {
        ArrayList<File> result = new ArrayList<>();
        for (File file : this.files)
            if (file.length() < num ^ negation)
                result.add(file);
        return result;
    }

    public ArrayList<File> greaterThan(double num, boolean negation) {
        ArrayList<File> result = new ArrayList<>();
        for (File file : this.files)
            if (file.length() > num ^ negation)
                result.add(file);
        return result;
    }

    public ArrayList<File> between(double min, double max, boolean negation) {
        ArrayList<File> result = new ArrayList<>();
        for (File file : this.files)
            if ((file.length() >= min && file.length() <= max) ^ negation)
                result.add(file);
        return result;
    }
}
