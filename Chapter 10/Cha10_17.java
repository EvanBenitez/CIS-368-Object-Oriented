/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 10, Question 10.17
 *  Description: First 10 square numbers bigger than Long.MAX_VALUE
*/

import java.math.BigInteger;

public class Cha10_17 {
  public static void main(String[] args) {
    // find squar root of max long value
    long big = (long) Math.sqrt((double) Long.MAX_VALUE);
    BigInteger start = new BigInteger(String.valueOf(big));

    // Get comparison value
    BigInteger compare = new BigInteger(String.valueOf(Long.MAX_VALUE));

    // Print Long.MAX_VALUE
    System.out.println("Long.MAX_VALUE is: " + Long.MAX_VALUE);

    //find first 10 larger than Long.MAX_VALUE
    for(int i=0; i<10;){
      BigInteger test = start.multiply(start); //calculate next square number
      if(test.compareTo(compare) == 1) {
        System.out.println((i+1) + ": " + test.toString());
        i++;
      }
      start = start.add(new BigInteger("1"));
    }
  }
}
