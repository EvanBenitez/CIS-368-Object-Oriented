/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 12, Question 12.31
 *  Description: Baby names
*/

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Cha12_31 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    File file;
    String gender, name, year;

    System.out.print("Enter the year: ");

    // get the year and open the file
    while ( !(file = new File("babynamesranking" + (year = scanner.nextLine()) + ".txt" )).exists() ) {
      System.out.println("Year not found. Try again.");
    }

    // get gender
    System.out.print("Enter the gender: ");
    while( !(gender = scanner.nextLine().toUpperCase()).equals("M") && !gender.equals("F") ) {
      System.out.println("Enter 'M' for male and 'F' for female");
      System.out.println(gender.length());
    }

    System.out.print("Enter the name: ");
    name = scanner.nextLine();


    //find the rank
    try (Scanner fileStream = new Scanner(file)) {
      String lineName;
      int rank=1;

      while(fileStream.hasNext()) {
        if(gender.equals("M")) {
          lineName = fileStream.nextLine().split("\t")[1].toUpperCase(); //check male column
        }
        else {
          lineName = fileStream.nextLine().split("\t")[3].toUpperCase(); //check female column
        }
        // check for match
        if(lineName.equals(name.toUpperCase())){
          System.out.println(name + " is ranked #" + rank + " in year " + year);
          break;
        }
        rank++;
      }
      // print not found if name not found
      if(rank > 1000)
        System.out.println("Name not found in records");
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }
}
