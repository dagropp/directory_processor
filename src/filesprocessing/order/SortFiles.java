package filesprocessing.order;

import java.io.File;

/**
 * This static class sorts Files array, using MergeSort algorithm.
 */
abstract class SortFiles {
    private File[] sortedArray;

    protected File[] runSort(File[] files, boolean reverse) {
        this.sortedArray = files.clone();
        this.sort(this.sortedArray);
        return reverse ? this.reverse(this.sortedArray) : this.sortedArray;
    }

    protected abstract boolean compareFiles(File leftFile, File rightFile);

    /**
     * @param input The array to sort.
     */
    private void sort(File[] input) {
        if (input.length >= 2) {
            File[] left = new File[input.length / 2];
            File[] right = new File[input.length - left.length];
            System.arraycopy(input, 0, left, 0, left.length);
            System.arraycopy(input, left.length, right, 0, right.length);
            this.sort(left);
            this.sort(right);
            this.merge(input, left, right);
        }
    }

    /* Private static methods */

    /**
     * @param input The array to sort.
     * @param left  The first indexes (start-middle) of the current array, divided by half.
     * @param right The last indexes (middle-end) of the current array, divided by half.
     */
    private void merge(File[] input, File[] left, File[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < input.length; i++) {
            if (b >= right.length || (a < left.length && this.compareFiles(left[a], right[b]))) {
                input[i] = left[a];
                a++;
            } else {
                input[i] = right[b];
                b++;
            }
        }
    }

    private File[] reverse(File[] input) {
        File[] result = new File[input.length];
        for (int i = 0, j = input.length - 1; i < input.length; i++, j--)
            result[i] = input[j];
        return result;
    }
}
