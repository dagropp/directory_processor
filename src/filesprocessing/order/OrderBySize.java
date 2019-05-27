package filesprocessing.order;

import java.io.File;

class OrderBySize extends OrderFiles {
    private static OrderBySize sortFilesSize = new OrderBySize();

    private OrderBySize() {
        super();
    }

    static File[] execute(File[] files, boolean reverse) {
        return sortFilesSize.runSort(files, reverse);
    }

    protected boolean compareFiles(File leftFile, File rightFile) {
        return leftFile.length() < rightFile.length();
    }
}
