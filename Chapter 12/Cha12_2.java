/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 12, Question 12.2
 *  Description: InputMismatchException
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class Cha12_2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean done = false; //set to true if first and second are integers

    System.out.println("Enter two integers");
    do {
      try {
        int first, second;
        first = scanner.nextInt();
        second = scanner.nextInt();
        System.out.println(first + " + " + second + " = " + (first+second) );
        done = true;
      }
      catch(InputMismatchException e){
        System.out.println("please enter two integers");
        scanner.nextLine();
      }
    } while(!done);
  }
}
