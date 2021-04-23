/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 13, Question 13.4
 *  Description: Calendar
*/

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cha13_4 {
  public static void main(String[] args) {
    if(args.length < 2){
      System.out.println("Must specify month and year (i.e. 4 2016)");
    }
    else {
      Calendar calendar = new GregorianCalendar(Integer.valueOf(args[1]), Integer.valueOf(args[0])-1, 1);
      printMonth(calendar);
    }
  }

  // print Calendar
  private static void printMonth(Calendar cal) {
    // get month Name
    String month = getMonth(cal.get(Calendar.MONTH));

    printHeader(month, String.valueOf(cal.get(Calendar.YEAR)) );
    // Align start of month to proper DAY
    for(int d=1; d<cal.get(Calendar.DAY_OF_WEEK); d++) {
      if(d<cal.get(Calendar.DAY_OF_WEEK))
        System.out.print("    ");
    }
    // print days on calendar
    for(int i=1; i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
      System.out.printf("%3d ", i);
      if(cal.get(Calendar.DAY_OF_WEEK)%7 == 0)
        System.out.println();
      cal.add(Calendar.DAY_OF_MONTH, 1);
    }
    System.out.println("\n");

  }

  // print month year, and static header formating
  private static void printHeader(String month, String year) {
    System.out.println("        " + month + ", " + year);
    System.out.println("---------------------------");
    System.out.println("Sun Mon Tue Wed Thu Fri Sat");
  }

  private static String getMonth(int monthNum) {
    switch(monthNum) {
      case 0:
        return "January";
      case 1:
        return "Febraury";
      case 2:
        return "March";
      case 3:
        return "April";
      case 4:
        return "May";
      case 5:
        return "June";
      case 6:
        return "July";
      case 7:
        return "August";
      case 8:
        return "September";
      case 9:
        return "October";
      case 10:
        return "November";
      case 11:
        return "December";
      default:
        System.out.println("No matching month");
        System.exit(1);
    }
    return null;
  }
}
