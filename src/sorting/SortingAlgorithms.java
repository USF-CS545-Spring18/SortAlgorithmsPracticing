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
        Comparable curr;
        int j;
        for (int i = lowindex; i < highindex; i++) {
            curr = array[i];
            j = i - 1;
            if(reversed){
                while (j >= lowindex && array[j].compareTo(curr) < 0) {
                    array[j + 1] = array[j]; // shift elems to the right
                    j--;
                }
            }else {
                while (j >= lowindex && array[j].compareTo(curr) > 0) {
                    array[j + 1] = array[j]; // shift elems to the right
                    j--;
                }
            }
            array[j + 1] = curr;
        }
    }

    /**
     * Sorts a given array of 2^k elements using iterative
     * (non-recursive) merge sort.
     * @param array array to sort
     */
    @Override
    public void iterativeMergeSort(Comparable[] array) {
        // FILL ON CODE
        Comparable[] temp = new Comparable[array.length];
        mergeSort(array, temp);
    }

    /**
     * A private mergeSort method - takes an array, a temporary array, and the
     * indices that specify what part of the list we are working with (we need
     * to sort the part from low to high)
     *
     * @param arr
     * @param temp
     */
    private static void mergeSort(Comparable[] arr, Comparable[] temp) {
        int length = 2;
        while(length <= arr.length){
            int times = arr.length / length;
            for(int i = 0; i < times; i++){
                int low = i * length;
                int high = low + length - 1;
                int mid = (low + high) / 2;
                merge(arr, temp, low, mid, high);
            }
            length = length * 2;
        }
    }

    /**
     * Merge two sorted sublists together, one that goes from low to mid another
     * goes from mid+1 to high. Uses a temporary array.
     *
     * @param arr
     * @param temp
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(Comparable[] arr, Comparable[] temp, int low, int mid, int high) {
        int k = low;
        int i = low;
        int j = mid + 1;
        while (k <= high) {
            if (i > mid) {// ran out of elements in the i sublist
                temp[k] = arr[j];
                k++;
                j++;
            } else if (j > high) {// ran out of elements in the j sublist
                temp[k] = arr[i];
                k++;
                i++;
            } else if (arr[i].compareTo(arr[j]) < 0) { // place arr[i] in temp, move i
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j]; // place arr[j] in temp, move j
                k++;
                j++;
            }
        }
        // copy the result from temp back to arr
        for (k = low; k <= high; k++)
            arr[k] = temp[k];
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
        MaxHeap mh = new MaxHeap(array, lowindex, highindex);
        int size = highindex - lowindex;
        for(int i = 0; i <= size - 1; i++) mh.removeMax();
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

    private class MaxHeap {
        private Comparable[] heap; // the array to store the heap
//        private int maxsize; // the size of the array
        private int size; // the current number of elements in the array
        private int low;
        private int high;

        /**
         * the constructor
         * @param arr
         */
        public MaxHeap(Comparable[] arr, int low, int high) {
//            maxsize = max;
            heap = arr;
            this.low = low;
            this.high = high;
            size = high - low;
            buildBottomUp();
            // Note: no actual data is stored at heap[0].
            // Assigned MIN_VALUE so that it's easier to bubble up
        }

        private void buildBottomUp(){
            int end = high - 1;
            int start = parent(end);
            while(start != end){
                for(int i = start + 1; i <= end; i++) pushdown(i);
                end = start;
                start = parent(end);
            }
            pushdown(low);
        }

        /** Return the index of the left child of the element at index pos
         *
         * @param pos the index of the element in the heap array
         * @return the index of the left child
         */
        private int leftChild(int pos) {
            return 2 * (pos - low) + 1 + low;
        }

        /** Return the index of the right child
         *
         * @param pos the index of the element in the heap array
         * @return the index of the right child
         */
        private int rightChild(int pos) {
            return 2 * (pos - low) + 2 + low;
        }

        /** Return the index of the parent
         *
         * @param pos the index of the element in the heap array
         * @return the index of the parent
         */
        private int parent(int pos) {
            return (((pos - low) - 1) / 2) + low;
        }

        /** Returns true if the node in a given position is a leaf
         *
         * @param pos the index of the element in the heap array
         * @return true if the node is a leaf, false otherwise
         */
        private boolean isLeaf(int pos) {
            return ((pos > ((size / 2) - 1 + low)) && ((pos - low + 1) <= size));
        }

        /** Swap given elements: one at index pos1, another at index pos2
         *
         * @param pos1 the index of the first element in the heap
         * @param pos2 the index of the second element in the heap
         */
        private void swap(int pos1, int pos2) {
            Comparable tmp;
            tmp = heap[pos1];
            heap[pos1] = heap[pos2];
            heap[pos2] = tmp;
        }

        /** Print the array that stores the heap */
        public void print() {
            int i;
            for (i = 1; i <= size; i++)
                System.out.print(heap[i] + " ");
            System.out.println();
        }

        /** Remove minimum element (it is at the top of the minHeap)
         *
         * @return the smallest element in the heap
         */
        public void removeMax() {
            System.out.println("low is " + low + " high is " + high + " size is " + size);
            swap(low, size + low - 1); // swap the end of the heap into the root
            size--;  	   // removed the end of the heap
            // fix the heap property - push down as needed
            if (size != 0)
                pushdown(low);
        }

        /** Push the value down the heap if it does not satisfy the heap property
         *
         * @param position the index of the element in the heap
         */
        private void pushdown(int position) {
            int largestChild;
            while (!isLeaf(position)) {
                largestChild = leftChild(position); // set the smallest child to left child
                if ((largestChild < size + low - 1) && (heap[largestChild].compareTo(heap[largestChild + 1]) < 0))
                    largestChild = largestChild + 1; // right child was smaller, so smallest child = right child

                // the value of the smallest child is less than value of current,
                // the heap is already valid
                if (heap[position].compareTo(heap[largestChild]) >= 0)
                    return;
                swap(position, largestChild);
                position = largestChild;
            }
        }

    }
}
