/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.13
 *  Description: Location Class
*/

import java.util.Scanner;

public class Cha9_13 {
  public static void main(String args[]) {
    int row, col; //matrix size
    double[][] matrix; //nubmer matrix
    Scanner in = new Scanner(System.in);
    System.out.println("Enter the number of rows and column in the array: ");
    row = in.nextInt();
    col = in.nextInt();
    matrix = new double[row][col];
    System.out.println("Enter the array: ");
    //get numbers
    for(int i=0; i<row; i++) { //get row numbers
      for(int j=0; j<col; j++) { //get indivitual number;
        matrix[i][j] = in.nextDouble();
      }
    }
    //get location of largest number
    Location loc = Location.locateLargest(matrix);
    //output result
    System.out.println("The location of the largest element is " + loc.maxValue + " at (" + loc.row + ", " + loc.column + ")");
  }
}

class Location {
  public int row;
  public int column;
  public double maxValue;

  private Location(int row, int col, double max) {
    this.row = row;
    this.column = col;
    this.maxValue = max;
  }
  public static Location locateLargest(double[][] a) {
    double max = a[0][0];
    int row = 0;
    int col = 0;

    for(int i=0; i<a.length; i++) {
      for(int j=0; j<a[i].length; j++) {
        if(max < a[i][j]) {
          max = a[i][j];
          //store mox location
          row = i;
          col = j;
        }
      }
    }
    return new Location(row, col, max);
  }
}
