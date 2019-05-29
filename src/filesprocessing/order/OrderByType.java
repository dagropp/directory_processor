package filesprocessing.order;

import java.io.File;

/**
 * This singleton class sorts Files according to type, and if equal, according to absolute path.
 */
public class OrderByType extends OrderFiles {
    private static OrderByType sortFilesType = new OrderByType();
    private static final char TYPE_SEPARATOR = '.'; // Symbol where file type extension starts.
    private static final String NO_TYPE = ""; // Return value for when file has no type.

    private OrderByType() {
        super();
    }

    static File[] execute(File[] files, boolean reverse) {
        return sortFilesType.runSort(files, reverse);
    }

    protected boolean compareFiles(File leftFile, File rightFile) {
        int comparison = this.fileType(leftFile).compareTo(this.fileType(rightFile));
        if (comparison == 0)
            return leftFile.getAbsolutePath().compareTo(rightFile.getAbsolutePath()) < 0;
        return comparison < 0;
    }

    private String fileType(File file) {
        String fileName = file.getName();
        int separatorIdx = fileName.lastIndexOf(TYPE_SEPARATOR);
        return separatorIdx > 0 ? fileName.substring(separatorIdx + 1) : NO_TYPE;
    }
}
