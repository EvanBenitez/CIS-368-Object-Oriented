/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 12, Question 12.10
 *  Description: OurOfMemoryError
*/

// need to set the maximum memory in the command line
public class Cha12_10 {
  public static void main(String[] args) {
    try {
      long[] numbers = new long[99999999];
      for(int i=0; i<99999999; i++){
        numbers[i] = i;
        if(i%100 == 0)
          System.out.println(i);
      }
    }
    catch(OutOfMemoryError e) {
      System.out.println("Out of memory");
      System.out.println("Closing program");
    }
  }
}
