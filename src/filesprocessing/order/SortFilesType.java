package filesprocessing.order;

import java.io.File;

class SortFilesType extends SortFiles {
    private static SortFilesType sortFilesType = new SortFilesType();
    private static final String TYPE_SEPARATOR = "."; // Symbol where file type extension starts.
    private static final String NO_TYPE = ""; // Return value for when file has no type.

    private SortFilesType() {
        super();
    }

    static File[] execute(File[] files, boolean reverse) {
        return sortFilesType.runSort(files, reverse);
    }

    protected boolean compareFiles(File leftFile, File rightFile) {
        return this.fileType(leftFile).compareTo(this.fileType(rightFile)) < 0;
    }

    private String fileType(File file) {
        String fileName = file.getName();
        int separatorIdx = fileName.lastIndexOf(TYPE_SEPARATOR);
        return separatorIdx > 0 ? fileName.substring(separatorIdx + 1) : NO_TYPE;
    }
}
