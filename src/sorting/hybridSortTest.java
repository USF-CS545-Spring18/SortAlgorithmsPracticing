package sorting;

import java.util.Collections;
import java.util.Random;

public class hybridSortTest {

    public static void main(String[] args){
        SortingAlgorithms sa = new SortingAlgorithms();

        Comparable[] array101 = new Comparable[10];
        Comparable[] array102 = new Comparable[10];
        Comparable[] array1001 = new Comparable[100];
        Comparable[] array1002 = new Comparable[100];
        Comparable[] array10000001 = new Comparable[1000000];
        Comparable[] array10000002 = new Comparable[1000000];
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            int temp = random.nextInt(10);
            array101[i] = temp;
            array102[i] = temp;
        }
        for(int i = 0; i < 100; i++){
            int temp = random.nextInt(100);
            array1001[i] = temp;
            array1002[i] = temp;
        }
        for(int i = 0; i < 1000000; i++){
            int temp = random.nextInt(1000000);
            array10000001[i] = temp;
            array10000002[i] = temp;
        }

        System.out.println("==========Test results==========");

        System.out.println("Testing input size 10, random sort");
        long startTime = System.nanoTime();
        sa.randomizedQuickSort(array101, 0, 9);
        long endTime = System.nanoTime();
        System.out.println("\tRandomized quicksort: " + (endTime - startTime));
        startTime = System.nanoTime();
        sa.hybridSort(array102, 0, 9);
        endTime = System.nanoTime();
        System.out.println("\tHybrid sort: " + (endTime - startTime));

        System.out.println("Testing input size 100, random sort");
        startTime = System.nanoTime();
        sa.randomizedQuickSort(array1001, 0, 99);
        endTime = System.nanoTime();
        System.out.println("\tRandomized quicksort: " + (endTime - startTime));
        startTime = System.nanoTime();
        sa.hybridSort(array1002, 0, 99);;
        endTime = System.nanoTime();
        System.out.println("\tHybrid sort: " + (endTime - startTime));

        System.out.println("Testing input size 1000000, random sort");
        startTime = System.nanoTime();
        sa.randomizedQuickSort(array10000001, 0, 999999);
        endTime = System.nanoTime();
        System.out.println("\tRandomized quicksort: " + (endTime - startTime));
        startTime = System.nanoTime();
        sa.hybridSort(array10000002, 0, 999999);
        endTime = System.nanoTime();
        System.out.println("\tHybrid sort: " + (endTime - startTime));

        System.out.println("Testing input size 1000000, sorted");
        startTime = System.nanoTime();
        sa.randomizedQuickSort(array10000001, 0, 999999);
        endTime = System.nanoTime();
        System.out.println("\tRandomized quicksort: " + (endTime - startTime));
        startTime = System.nanoTime();
        sa.hybridSort(array10000002, 0, 999999);
        endTime = System.nanoTime();
        System.out.println("\tHybrid sort: " + (endTime - startTime));

        for(int i = 0; i < 1000000; i++){
            array10000002[i] = array10000001[999999 - i];
        }
        for(int i = 0; i < 1000000; i++){
            array10000001[i] = array10000002[i];
        }
        System.out.println("Testing input size 1000000, reverse sorted");
        startTime = System.nanoTime();
        sa.randomizedQuickSort(array10000001, 0, 999999);
        endTime = System.nanoTime();
        System.out.println("\tRandomized quicksort: " + (endTime - startTime));
        startTime = System.nanoTime();
        sa.hybridSort(array10000002, 0, 999999);
        endTime = System.nanoTime();
        System.out.println("\tHybrid sort: " + (endTime - startTime));

    }
}
