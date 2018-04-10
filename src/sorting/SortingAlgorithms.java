package sorting;

import java.io.*;
import java.util.Random;

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
        for (int i = lowindex; i <= highindex; i++) {
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
    private static void merge(Comparable[] arr, Comparable[] temp, int low, int mid, int high) {
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
        quickSort(array, lowindex, highindex);
    }

    /**
     * Quick sort method that takes low and high indices
     * @param arr array to sort
     * @param low the index where we should start sorting the array
     * @param high the index where we want to finish sorting the array
     */
    private void quickSort(Comparable[] arr, int low, int high) {
        int pivot; // index of the pivot
        if (low < high) {
            pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    /**
     * Helper method for quickSort.
     * @param arr array of integers
     * @param low the starting value of i
     * @param high the starting value of j
     * @return
     */
    private int partition(Comparable[] arr, int low, int high) {
        Comparable pivot;
        Comparable tmp;
        Random random = new Random();
        int p1 = low + random.nextInt(high - low + 1);
        int p2 = low + random.nextInt(high - low + 1);
        int p3 = low + random.nextInt(high - low + 1);
        int max = high;
        int pivotIndex = findTheMidian(p1, p2, p3);

        tmp = arr[pivotIndex];
        arr[pivotIndex] = arr[high];
        arr[high] = tmp;
        pivot = arr[high];
        low--;
        do {
            while ((low < high) && (arr[++low].compareTo(pivot) < 0))
                ;
            while ((low < high) && (arr[--high].compareTo(pivot) > 0))
                ;
            // swap values at low and high
            tmp = arr[low];
            arr[low] = arr[high];
            arr[high] = tmp;
        } while (low < high);
        tmp = arr[low];
        arr[low] = arr[max];
        arr[max] = tmp;
        return low;
    }

    /**
     * find the secound largest number of the three input
     * @param i1
     * @param i2
     * @param i3
     * @return
     */
    public int findTheMidian(int i1, int i2, int i3){
        int median = i3;
        if(i1 <= i2){
            if(i1 >= i3) median = i1;
            if(i2 <= i3) median = i2;
        }else {
            if(i2 >= i3) median = i2;
            if(i1 <= i3) median = i1;
        }
        return median;
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
        if(highindex - lowindex + 1 <= 10){
            insertionSort(array, lowindex, highindex, false);
            return;
        }
        if (lowindex < highindex) {
            quickSort(array, lowindex, highindex);
        }
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
        int bucketSize = (highindex - lowindex + 1) / 2;
        LinkedList[] bucket = new LinkedList[bucketSize];
        for(int i = 0; i < bucket.length; i++){
            bucket[i] = new LinkedList();
        }
        int max = -1;
        for(int i = lowindex; i <= highindex; i++){
            int temp = array[i].key();
            if(temp >= max) max = temp;
        }
        double rangeTemp = (max + 1) / (double) bucketSize;
        int range =(int) Math.ceil(rangeTemp);
        for(int i = lowindex; i <= highindex; i++){
            int temp = array[i].key();
            int dest = temp / range;
            if(!reversed){
                bucket[dest].insert(array[i]);
            }else {
                bucket[dest].insertReverse(array[i]);
            }
        }
        int k = lowindex;
        if(!reversed) {
            for (int i = 0; i < bucketSize; i++) {
                Elem temp = bucket[i].removeFirst();
                while (temp != null) {
                    array[k] = temp;
                    k++;
                    temp = bucket[i].removeFirst();
                }
            }
        }else {
            for (int i = bucketSize - 1; i >= 0; i--) {
                Elem temp = bucket[i].removeFirst();
                while (temp != null) {
                    array[k] = temp;
                    k++;
                    temp = bucket[i].removeFirst();
                }
            }
        }

    }

    /**
     * Implements external sort method
     * @param inputFile The file that contains the input list
     * @param outputFile The file where to output the sorted list
     * @param n number of elements to sort
     * @param m number of elements that fit into memory at once
     */
    public void externalSort(String inputFile, String outputFile, int n, int m) {
        Comparable[] temp = new Comparable[m];
        double dTimes = (double) n / m;
        int times = (int) Math.ceil(dTimes);
        int i, j;
        try (FileReader f = new FileReader(inputFile);
             BufferedReader br = new BufferedReader(f)) {
            for (i = 0; i < times; i++) {
                for (j = 0; j < m; j++) {
                    String s = br.readLine();
                    if (s == null) break;
                    temp[j] = Integer.parseInt(s);
                }
                quickSort(temp, 0, m - 1);
                PrintWriter pw = new PrintWriter("temp" + i + ".txt");
                for (int k = 0; k < j; k++) pw.println(temp[k]);
                pw.close();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int[] arr = new int[times];
            BufferedReader[] bufferArray = new BufferedReader[times];
            for (i = 0; i < times; i++) {
                bufferArray[i] = new BufferedReader(new FileReader("temp" + i + ".txt"));
                String t = bufferArray[i].readLine();
                if (t != null) {
                    arr[i] = Integer.parseInt(t);
                } else {
                    arr[i] = Integer.MAX_VALUE;
                }
            }
            PrintWriter pw = new PrintWriter(outputFile);
            for (i = 0; i < n; i++) {
                int min = arr[0];
                int file = 0;
                for (j = 0; j < times; j++) {
                    if (min > arr[j]) {
                        min = arr[j];
                        file = j;
                    }
                }
                pw.println(min);
                String t = bufferArray[file].readLine();
                if (t != null) {
                    arr[file] = Integer.parseInt(t);
                } else {
                    arr[file] = Integer.MAX_VALUE;
                }
            }
            for (i = 0; i < times; i++) bufferArray[i].close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * the inner class MaxHeap
     */
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

        /**
         * build the heap from bottom up
         */
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

    /** LinkedList class - implementation of a singly linked list.
     * Each node stores an element of type Object,
     * and a reference to the next node. The first element is stored
     * in the head (no dummy head in this implementation).
     * */
    private class LinkedList {

        private Node head;

        /** Constructor of the class.
         * The list is empty (head and tail are null). */
        LinkedList() {
            head = null;

        }

        /**
         * insert the Elem into the sorted linkedlist
         * @param e the element to insert;
         */
        public void insert(Elem e){
            if(head == null){
                head = new Node(e, null);
                return;
            }
            if(head.element.key() >= e.key()){
                Node newNode = new Node(e, head);
                head = newNode;
                return;
            }
            Node cur = head;
            while(cur.next != null){
                if(cur.next.element.key() >= e.key()){
                    cur.next = new Node(e, cur.next);
                    return;
                }
                cur = cur.next;
            }
            cur.next = new Node(e, null);
        }

        /**
         * insert the Elem into the sorted linkedlist in decreasing order
         * @param e the element to insert;
         */
        public void insertReverse(Elem e){
            if(head == null){
                head = new Node(e, null);
                return;
            }
            if(head.element.key() <= e.key()){
                Node newNode = new Node(e, head);
                head = newNode;
                return;
            }
            Node cur = head;
            while(cur.next != null){
                if(cur.next.element.key() <= e.key()){
                    cur.next = new Node(e, cur.next);
                    return;
                }
                cur = cur.next;
            }
            cur.next = new Node(e, null);
        }

        /**
         * remove the first element in the linked list
         * @return the first element
         */
        public Elem removeFirst(){
            if(head == null) return null;
            Elem temp = head.element;
            head = head.next;
            return temp;
        }
        // -------------------------------------------
        /** Inner class Node. Represents a single node in a linked list.
         *  Since Node is an inner class, we can access all its data fields
         *  from the outer class LinkedList (even private ones).
         *  */
        private class Node {
            Elem element;
            Node next;

            Node() {
                this.element = null;
                this.next = null;
            }

            Node(Elem elem, Node next) {
                this.element = elem;
                this.next = next;
            }

            Node next() {
                return next;
            }

            Object element() {
                return element;
            }

        }

    }
}
