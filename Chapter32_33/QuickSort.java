/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 32, Question task 2
 *  Description: Parallel QuickSort
*/

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class QuickSort {

  public static void parallelQuickSort(int[] list) {
    RecursiveAction task = new ParallelSorter(list, 0, list.length - 1);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(task);
  }

  private static class ParallelSorter extends RecursiveAction {
    private int[] list;
    private int start;
    private int end;

    ParallelSorter(int[] list, int start, int end) {
      this.list = list;
      this.start = start;
      this.end = end;
    }
    @Override
    protected void compute() {
      if(start<end){
        int pivotIndex = partition(list, start, end);
        invokeAll(new ParallelSorter(list, start, pivotIndex-1), new ParallelSorter(list, pivotIndex+1, end));
      }
    }

    private int partition(int[] list, int first, int last) {
      int pivot = list[first]; // Choose the first element as the pivot
      int low = first + 1; // Index for forward search
      int high = last; // Index for backward search

      while (high > low) {
        // Search forward from left
        while (low <= high && list[low] <= pivot)
          low++;

        // Search backward from right
        while (low <= high && list[high] > pivot)
          high--;

        // Swap two elements in the list
        if (high > low) {
          int temp = list[high];
          list[high] = list[low];
          list[low] = temp;
        }
      }

      while (high > first && list[high] >= pivot)
        high--;

      // Swap pivot with list[high]
      if (pivot > list[high]) {
        list[first] = list[high];
        list[high] = pivot;
        return high;
      }
      else {
        return first;
      }
    }
  }

  public static void quickSort(int[] list) {
    quickSort(list, 0, list.length - 1);
  }

  private static void quickSort(int[] list, int first, int last) {
    if (last > first) {
      int pivotIndex = partition(list, first, last);
      quickSort(list, first, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, last);
    }
  }

  /** Partition the array list[first..last] */
  private static int partition(int[] list, int first, int last) {
    int pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && list[low] <= pivot)
        low++;

      // Search backward from right
      while (low <= high && list[high] > pivot)
        high--;

      // Swap two elements in the list
      if (high > low) {
        int temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && list[high] >= pivot)
      high--;

    // Swap pivot with list[high]
    if (pivot > list[high]) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }

  /** A test method */
  public static void main(String[] args) {
    long start, end;

    System.out.println("\t\t\tRun a \t\t\tRun b");

    for(int i=0; i<10; i++) {
      int[] list1 = new int[9000000];
      int[] list2 = new int[9000000];

      for(int j=0; j<list1.length; j++) {
        list1[j] = list2[j] = (int)(Math.random()*9000000);
      }

      start = System.currentTimeMillis();
      quickSort(list1);
      end = System.currentTimeMillis();

      System.out.printf("Time of Round %d \t%d", i+1,end - start);

      start = System.currentTimeMillis();
      parallelQuickSort(list2);
      end = System.currentTimeMillis();

      System.out.println("\t\t\t" + (end - start));
    }
  }
}
