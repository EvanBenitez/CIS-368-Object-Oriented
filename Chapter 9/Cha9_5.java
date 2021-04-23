/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.5
 *  Description: Use Gregorian Calendar class
*/

import java.util.GregorianCalendar;

public class Cha9_5 {
  public static void main(String args[]) {
    GregorianCalendar date = new GregorianCalendar();
    System.out.println(date.get(GregorianCalendar.YEAR) + "/" + date.get(GregorianCalendar. MONTH) + "/" + date.get(GregorianCalendar.DAY_OF_MONTH));

    date.setTimeInMillis(1234567898765L);
    System.out.println(date.get(GregorianCalendar.YEAR) + "/" + date.get(GregorianCalendar. MONTH) + "/" + date.get(GregorianCalendar.DAY_OF_MONTH));
  }
}
