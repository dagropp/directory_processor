package filesprocessing.order;

import java.io.File;

/**
 * This abstract class sorts Files array, using MergeSort algorithm, while the actual comparator is
 * implemented in each child class.
 */
public abstract class OrderFiles {
    /* Class members - variables */
    private File[] sortedArray; // The sorted File array.

    /* Protected instance methods */

    /**
     * Runs the sorting algorithm - receives a File array and returns a sorted File array.
     *
     * @param files   File array to sort.
     * @param reverse If true reverse order, if false ascend.
     * @return A sorted File array.
     */
    protected File[] runSort(File[] files, boolean reverse) {
        this.sortedArray = files.clone(); // Clones the array, as to not change the original array.
        this.divide(this.sortedArray); // Runs the sorting method.
        return reverse ? this.reverse(this.sortedArray) : this.sortedArray; // If reverse, reverse the result.
    }

    /**
     * Compares files according to specified parameters. Implemented on each child class.
     *
     * @param leftFile  File from the left array.
     * @param rightFile File from the right array.
     * @return True if left file should come before right file, false otherwise.
     */
    protected abstract boolean compareFiles(File leftFile, File rightFile);

    /* Private instance methods */

    /**
     * Recursive method that sorts the input array.
     *
     * @param input The array to sort.
     */
    private void divide(File[] input) {
        if (input.length >= 2) { // As long as input array length is 2 and more:
            // Divide the input array to 2 arrays (left/right).
            File[] left = new File[input.length / 2];
            // Copies the first indexes (start-middle) of the current array to the left array.
            System.arraycopy(input, 0, left, 0, left.length);
            File[] right = new File[input.length - left.length];
            // Copies the last indexes (middle-end) of the current array to the right array.
            System.arraycopy(input, left.length, right, 0, right.length);
            this.divide(left); // Calls method again with left array.
            this.divide(right); // Calls method again with right array.
            this.mergeSort(input, left, right); // Left with smallest arrays, calls actual sorting method.
        }
    }

    /**
     * @param input The array to sort.
     * @param left  The first indexes (start-middle) of the current array, divided by half.
     * @param right The last indexes (middle-end) of the current array, divided by half.
     */
    private void mergeSort(File[] input, File[] left, File[] right) {
        int l = 0;
        int r = 0;
        // Iterates over the input array.
        for (int i = 0; i < input.length; i++) {
            // If left file should come before right file: assign it to input array.
            if (r >= right.length || (l < left.length && this.compareFiles(left[l], right[r]))) {
                input[i] = left[l];
                l++;
                // Right file should come before left file: assign it to input array.
            } else {
                input[i] = right[r];
                r++;
            }
        }
    }

    /**
     * Reverses the sorted array.
     *
     * @param input The array to reverse.
     * @return Reversed File array.
     */
    private File[] reverse(File[] input) {
        File[] result = new File[input.length]; // Creates empty results array.
        // Iterates over the array: forward on the result, backwards on the input.
        for (int i = 0, j = input.length - 1; i < input.length; i++, j--)
            result[i] = input[j]; // Assign each array index in its opposite index.
        return result;
    }
}
