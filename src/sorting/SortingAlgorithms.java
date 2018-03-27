package sorting;

/**  A class that implements SortInterface. Has various methods
 *   to sort a list of elements. */
public class SortingAlgorithms  implements SortInterface {

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using insertion sort
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */
    @Override
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        // FILL ON CODE
    }

    /**
     * Sorts a given array of 2^k elements using iterative
     * (non-recursive) merge sort.
     * @param array array to sort
     */
    @Override
    public void iterativeMergeSort(Comparable[] array) {
        // FILL ON CODE
    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using in-place heap sort
     *
     * @param array array to sort
     * @param lowindex  the beginning index of a sublist
     * @param highindex the end index of a sublist
     */
    @Override
    public void heapSort(Comparable[] array, int lowindex, int highindex) {
        // FILL ON CODE

    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using the randomizedQuickSort
     * @param array array to sort
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     */
    @Override
    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex) {
        // FILL ON CODE
    }

    /**
     * Sorts a given sublist using hybrid sort, where the list is sorted
     * using randomized quick sort; when sublists get small (size=10 ?),
     * they are sorted using insertion sort.
     * @param array array of Comparable-s to sort
     * @param lowindex the beginning index of the sublist
     * @param highindex the end index of the sublist
     */
    @Override
    public void hybridSort(Comparable[] array, int lowindex, int highindex) {
        // FILL ON CODE
    }

    /**
     * Sorts a sub-array of records using bucket sort.
     * @param array array of records
     * @param lowindex the beginning index of the sublist to sort
     * @param highindex the end index of the sublist to sort
     * @param reversed if true, sort in descending (decreasing) order, otherwise ascending
     */
    @Override
    public void bucketSort(Elem[] array, int lowindex, int highindex, boolean reversed) {
        // FILL IN CODE
    }

    /**
     * Implements external sort method
     * @param inputFile The file that contains the input list
     * @param outputFile The file where to output the sorted list
     * @param n number of elements to sort
     * @param m number of elements that fit into memory at once
     */
    public void externalSort(String inputFile, String outputFile, int n, int m) {
        // FILL IN CODE

    }
}
