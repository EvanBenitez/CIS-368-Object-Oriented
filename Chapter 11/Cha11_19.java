/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 11, Question 11.19
 *  Description:  Bin Problem
*/

import java.util.Scanner;
import java.util.ArrayList;

public class Cha11_19 {
  public static void main(String[] args) {
    ArrayList<Integer> weights = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int numItems;

    // Get number of itmes
    System.out.print("Enter the number of objects: ");
    numItems = scanner.nextInt();

    //get weights
    System.out.print("Enter the weights of the objects: ");
    for(int i=0; i<numItems; i++) { // get number of items ins for loop
      weights.add(scanner.nextInt());
    }

    // display weights for each container
    for(int j = 1; weights.size() > 0; j++) {
      int total = 0;
      System.out.print("Container " + j + " contains objects with weights ");
      while( !weights.isEmpty() && total + weights.get(0) <= 10) {
        total += weights.get(0); // add to tatal weight
        System.out.print(weights.remove(0) + " "); // removes the weight from the list
      }
      System.out.println();
    }
  }
}
