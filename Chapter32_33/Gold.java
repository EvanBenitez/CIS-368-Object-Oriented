/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 32, Question task 3
 *  Description: Gold stash
*/

import java.util.concurrent.*;


public class Gold {
  private static int gold = 5000;

  public static void main(String[] args) {
    ExecutorService banker = Executors.newCachedThreadPool();
    for(int i = 0; i<1000; i++) {
      banker.execute(new Runnable() {
        public void run() {
          gold -= 5;
          System.out.println("Gold Remaining: " + gold);
        }
      });
    }
    banker.shutdown();
  }

}
