/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 10, last question
 *  Description: Phone nubmer matcher
*/

import java.util.Scanner;

public class Cha10_LastQuestion {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String phone;

    System.out.println("Enter a phone number: ");
    phone = scan.nextLine();

    if(phone.matches("\\d{3}-\\d{3}-\\d{4}") ||
      phone.matches("\\(\\d{3}\\)\\d{3}-\\d{4}") ||
      phone.matches("\\d{3}\\d{3}\\d{4}") ||
      phone.matches("\\d{3}\\.\\d{3}\\.\\d{4}")){
      System.out.println("it belongs to the four phone formats");
    }
    else {
      System.out.println("it does not belong to the above phone formats");
    }

  }
}
