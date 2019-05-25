package filesprocessing.order;

import java.io.File;

/**
 * This static class sorts Files array, using MergeSort algorithm.
 */
public class SortFiles {
    public static String[] execute(File[] files, boolean ascend) {
        if (!containsNull(files)) {
            String[] sortedArray = new String[files.length];
            for (int i = 0; i < sortedArray.length; i++)
                sortedArray[i] = files[i].getAbsolutePath();
            sort(sortedArray);
            return ascend ? sortedArray : reverse(sortedArray);
        }
        return null;
    }

    /**
     * @param input The array to sort.
     */
    private static void sort(String[] input) {
        if (input.length >= 2) {
            String[] left = new String[input.length / 2];
            String[] right = new String[input.length - (input.length / 2)];
            for (int i = 0; i < left.length; i++)
                left[i] = input[i];
            for (int i = 0; i < right.length; i++)
                right[i] = input[i + input.length / 2];
            sort(left);
            sort(right);
            merge(input, left, right);
        }
    }

    /* Private static methods */

    /**
     * @param input The array to sort.
     * @param left  The first indexes (start-middle) of the current array, divided by half.
     * @param right The last indexes (middle-end) of the current array, divided by half.
     */
    private static void merge(String[] input, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < input.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareToIgnoreCase(right[b]) < 0)) {
                input[i] = left[a];
                a++;
            } else {
                input[i] = right[b];
                b++;
            }
        }
    }

    /**
     * Checks the array to sort contains null values, if so does not sort it.
     *
     * @param sortArray The array to check if contains null values.
     * @return True if array contains null values, false otherwise.
     */
    private static boolean containsNull(Object[] sortArray) {
        for (Object item : sortArray)
            if (item == null)
                return true;
        return false;
    }

    private static String[] reverse(String[] input) {
        String[] result = new String[input.length];
        for (int i = 0, j = input.length - 1; i < input.length; i++, j--)
            result[i] = input[j];
        return result;
    }

}
