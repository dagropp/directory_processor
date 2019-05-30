package filesprocessing.order;

import java.io.File;

/**
 * This singleton class sorts Files according to type, and if equal, according to absolute path.
 */
public class OrderByType extends OrderFiles {
    /* Class members - constant variables */
    private static final char TYPE_SEPARATOR = '.'; // Character where file type extension starts.
    private static final String NO_TYPE = ""; // Return value for when file has no type.
    /* Class members - variables */
    private static OrderByType sortFilesType = new OrderByType(); // Static member containing class instance.

    /* Constructors */

    /**
     * Private Constructor to OrderByType. Calls parent default constructor and initialized as static member.
     */
    private OrderByType() {
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
        return sortFilesType.runSort(files, reverse);
    }

    /* Protected instance methods */

    /**
     * This class comparator: Compares files based on type. If equal types, compares by absolute path.
     *
     * @param leftFile  File from the left array.
     * @param rightFile File from the right array.
     * @return True if left file type should come before right file type, false otherwise.
     */
    protected boolean compareFiles(File leftFile, File rightFile) {
        // Assign int value to the String comparison.
        int comparison = this.fileType(leftFile).compareTo(this.fileType(rightFile));
        // If comparison shows equal values (0), compares by absolute path.
        if (comparison == 0)
            // if left file path should come before right file path, method will return less than 0.
            return leftFile.getAbsolutePath().compareTo(rightFile.getAbsolutePath()) < 0;
        return comparison < 0; // Same as path comparison.
    }

    /* Private instance methods */

    /**
     * Extracts file type name from File.
     *
     * @param file File to extract type from.
     * @return File type, or empty String if no type.
     */
    private String fileType(File file) {
        String fileName = file.getName(); // Assign var with file name.
        int separatorIdx = fileName.lastIndexOf(TYPE_SEPARATOR); // Finds last index of type separator (.).
        // If found type separator, return sub-string that comes after it, otherwise return an empty String.
        return separatorIdx > 0 ? fileName.substring(separatorIdx + 1) : NO_TYPE;
    }
}
