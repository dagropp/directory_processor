package filesprocessing.order;

import java.io.File;

class OrderByAbsPath extends OrderFiles {
    private static OrderByAbsPath sortFilesPath = new OrderByAbsPath();

    private OrderByAbsPath() {
        super();
    }

    static File[] execute(File[] files, boolean reverse) {
        return sortFilesPath.runSort(files, reverse);
    }

    protected boolean compareFiles(File leftFile, File rightFile) {
        return leftFile.getAbsolutePath().compareTo(rightFile.getAbsolutePath()) < 0;
    }
}
