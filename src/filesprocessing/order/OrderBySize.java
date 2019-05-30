package filesprocessing.order;

import java.io.File;

/**
 * This singleton class sorts Files according to size, and if equal, according to absolute path.
 */
public class OrderBySize extends OrderFiles {
    /* Class members - variables */
    private static OrderBySize sortFilesSize = new OrderBySize(); // Static member containing class instance.

    /* Constructors */

    /**
     * Private Constructor to OrderBySize. Calls parent default constructor and initialized as static member.
     */
    private OrderBySize() {
        super();
    }

    /* Public static methods */

    /**
     * Executes the sorting method in parent class.
     *
     * @param files   File array to sort.
     * @param reverse If true reverse order, if false ascend.
     * @return A sorted File array.
     */
    static File[] execute(File[] files, boolean reverse) {
        return sortFilesSize.runSort(files, reverse);
    }

    /* Protected instance methods */

    /**
     * This class comparator: Compares files based on size. If equal sizes, compares by absolute path.
     *
     * @param leftFile  File from the left array.
     * @param rightFile File from the right array.
     * @return True if left file size is smaller than right file size, false otherwise.
     */
    protected boolean compareFiles(File leftFile, File rightFile) {
        // If files lengths (sizes) are same, compares by absolute path:
        if (leftFile.length() == rightFile.length())
            // if left file path should come before right file path, method will return less than 0.
            return leftFile.getAbsolutePath().compareTo(rightFile.getAbsolutePath()) < 0;
        return leftFile.length() < rightFile.length(); // Compares file lengths.
    }
}
