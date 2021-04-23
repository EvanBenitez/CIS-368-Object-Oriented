/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 10, Question 10.26
 *  Description: Calculator
*/

public class Cha10_26 {
  public static void main(String[] args) {
    if(args.length == 1) {
      String input = args[0].replace(" ", ""); // remove white space
      String[] operands;

      // find operation
      if(input.contains("+")){
        operands = input.split("\\+");
         System.out.println( operands[0] + " + "  + operands[1] + " = " + ( Integer.valueOf(operands[0]) + Integer.valueOf(operands[1]) ) );
      }
      else if(input.contains("-")){
        operands = input.split("-");
         System.out.println( operands[0] + " - " + operands[1] + " = " + ( Integer.valueOf(operands[0]) - Integer.valueOf(operands[1]) ) );
      }
      else if(input.contains("/") || input.contains("\\")){
        operands = input.split("[/\\\\]");
         System.out.println( operands[0] + " / " + operands[1] + " = " + ( Integer.valueOf(operands[0]) / Integer.valueOf(operands[1]) ) );
      }
      else if(input.contains("*")){
        operands = input.split("\\*");
         System.out.println( operands[0] + " * " + operands[1] + " = " + ( Integer.valueOf(operands[0]) * Integer.valueOf(operands[1]) ) );
      }
      else {
        System.out.println("Unsuported operation");
      }
    }
    else {
      System.out.println("incorrect number of arguments");
    }
  }
}
