package filesprocessing.order;

import java.io.File;

class SortFilesSize extends SortFiles {
    private static SortFilesSize sortFilesSize = new SortFilesSize();

    private SortFilesSize() {
        super();
    }

    static File[] execute(File[] files, boolean reverse) {
        return sortFilesSize.runSort(files, reverse);
    }

    protected boolean compareFiles(File leftFile, File rightFile) {
        return leftFile.length() < rightFile.length();
    }
}
