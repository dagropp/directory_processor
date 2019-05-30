package filesprocessing.order;

import java.io.File;

/**
 * This singleton class sorts Files according to absolute path.
 */
public class OrderByAbsPath extends OrderFiles {
    /* Class members - variables */
    private static OrderByAbsPath sortFilesPath = new OrderByAbsPath(); // Static member containing class instance.

    /* Constructors */

    /**
     * Private Constructor to OrderByAbsPath. Calls parent default constructor and initialized as static member.
     */
    private OrderByAbsPath() {
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
    public static File[] execute(File[] files, boolean reverse) {
        return sortFilesPath.runSort(files, reverse);
    }

    /* Protected instance methods */

    /**
     * This class comparator: Compares files based on absolute path.
     *
     * @param leftFile  File from the left array.
     * @param rightFile File from the right array.
     * @return True if left file path should come before right file path, false otherwise.
     */
    protected boolean compareFiles(File leftFile, File rightFile) {
        // if left file path should come before right file path, method will return less than 0.
        return leftFile.getAbsolutePath().compareTo(rightFile.getAbsolutePath()) < 0;
    }
}
