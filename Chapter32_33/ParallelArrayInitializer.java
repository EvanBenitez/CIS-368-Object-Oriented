/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 32, Question task 1
 *  Description: Parallel Array Initializer
*/

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class ParallelArrayInitializer {
  public static void main(String [] args) {
    double[] list1 = new double[9000000];
    double[] list2 = new double[9000000];
    long start, end;

    System.out.println("\t\t\tRun a \t\t\tRun b");

    for(int i = 0; i < 10; i++) {
      start = System.currentTimeMillis();
      sequentialAssignValues(list1);
      end = System.currentTimeMillis();

      System.out.printf("Time of Round %d \t%d", i+1,end - start);
      start = System.currentTimeMillis();
      parallelAssignValues(list2);
      end = System.currentTimeMillis();

      System.out.println("\t\t\t" + (end - start));
    }
  }

  public static void sequentialAssignValues(double[] list){
    for(int i = 0; i < list.length; i++) {
      list[i] = Math.random()*1000000;
    }
  }

  public static void parallelAssignValues(double[] list){
    RecursiveAction task = new AssignTask(list, 0, list.length);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(task);
  }

  private static class AssignTask extends RecursiveAction {
    private final int MAX = 1000000;
    private final int THRESHOLD = 2000000;
    private double[] list;
    private Random ran = new Random();
    private int start, len;

    AssignTask(double[] list, int start, int len){
      this.list = list;
      this.start = start;
      this.len = len;
    }

    @Override
    protected void compute() {
      if(len <= THRESHOLD){
        for(int i=start; i<start + len; i++){
          list[i] = ran.nextDouble()*MAX;
        }
      }
      else {
        invokeAll(new AssignTask(list,start, len / 2), new AssignTask(list, len / 2, len - len / 2));
      }
    }
  }
}

/*
private static class AssignTask extends RecursiveAction {
  private final int MAX = 1000000;
  private final int THRESHOLD = 500;
  private double[] list;
  private Random ran = new Random();

  AssignTask(double[] list){
    this.list = list;
  }

  @Override
  protected void compute() {
    if(list.length <= THRESHOLD){
      for(int i=0; i<list.length; i++){
        list[i] = ran.nextDouble()*MAX;
      }
    }
    else {
      double[] first = new double[list.length / 2];
      double[] second = new double[list.length - list.length / 2];

      invokeAll(new AssignTask(first), new AssignTask(second));

      // place both halves into list
      System.arraycopy(first, 0, list, 0, first.length);
      System.arraycopy(second, 0, list, first.length, second.length);
    }
  }
}
*/
