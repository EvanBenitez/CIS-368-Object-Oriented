/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 10, Question 10.20
 *  Description: First 10 square numbers bigger than Long.MAX_VALUE
*/

import java.math.BigDecimal;
import java.math.MathContext;

public class Cha10_20 {
  public static void main(String[] args) {
    // Set max deximal places
    MathContext max = new MathContext(25);

    // get BigDecimal 1
    BigDecimal one = new BigDecimal("1.0", max);

    // compute e for various i
    for(int i = 100; i <= 1000; i = i+100) {
      // compute e for given bigI value
      BigDecimal e = new BigDecimal("1.0", max);
      for(int j = 1; j <= i; j++) {

        // get factorial
        BigDecimal fact = new BigDecimal("1.0", max);
        for(int k=2; k<=j; k++) {
          fact = fact.multiply(new BigDecimal(String.valueOf(k)));
        }

        //divide one by the factorial and then add it to e
        BigDecimal fraction = one.divide(fact, max);
        e = e.add(fraction, max);
      }
      System.out.println("e for i = " + i + " is " + e.toString());
    }
  }
}
718281828459045235360289
