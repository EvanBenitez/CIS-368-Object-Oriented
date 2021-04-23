/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.4
 *  Description: Use Random class
*/

import java.util.Random;

public class Cha9_4 {
  public static void main(String args[]) {
    Random r = new Random(1000);
    //generate 50 random numbers between 0 an 100 exclusive
    for(int i = 0; i<50; i++) {
      System.out.println(r.nextInt(100));
    }
  }
}
