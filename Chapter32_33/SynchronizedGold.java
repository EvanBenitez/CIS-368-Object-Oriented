/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 32, Question task 3
 *  Description: Synchronized Gold stash
*/

import java.util.concurrent.*;

public class SynchronizedGold {
  private static Integer gold = 5000;

  public static void main(String[] args) {
    Thread[] t = new Thread[1000];
    for(int i = 0; i<1000; i++) {
      t[i] = new Thread(new Looter());
      t[i].start();
    }

  }

  private static class Looter implements Runnable {
    public void run() {
      loot();
    }

    private static synchronized void loot() {
          gold -= 5;
          System.out.println("Gold Remaining: " + gold);
    }
  }

}
