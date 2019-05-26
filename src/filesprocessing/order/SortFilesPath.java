package filesprocessing.order;

import java.io.File;

class SortFilesPath extends SortFiles {
    private static SortFilesPath sortFilesPath = new SortFilesPath();

    private SortFilesPath() {
        super();
    }

    static File[] execute(File[] files, boolean reverse) {
        return sortFilesPath.runSort(files, reverse);
    }

    protected boolean compareFiles(File leftFile, File rightFile) {
        return leftFile.getAbsolutePath().compareTo(rightFile.getAbsolutePath()) < 0;
    }
}
