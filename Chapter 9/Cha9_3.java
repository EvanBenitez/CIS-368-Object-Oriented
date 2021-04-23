/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.3
 *  Description: Use Date class
*/

import java.util.Date;

public class Cha9_3 {
  public static void main(String args[]) {
    long time = 10000L;
    Date date = new Date(time);
    System.out.println("time:" + date.toString());
    for(int i=0; i<7;i++) {
      time *=10; //increase ten fold
      date.setTime(time);
      System.out.println("time:" + date.toString());
    }
  }
}
