/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 13, Question 13.15
 *  Description: Ractional class
*/

import java.util.Scanner;
import java.math.BigInteger;

public class Rational extends Number implements Comparable<Rational> {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long n1, d1, n2, d2;

    System.out.print("Enter the first rational number: ");
    n1 = scanner.nextInt();
    d1 = scanner.nextInt();
    System.out.print("Enter the second rational number: ");
    n2 = scanner.nextInt();
    d2 = scanner.nextInt();

    //BigNummber values

    Rational r1 = new Rational(new BigInteger(String.valueOf(n1)),new BigInteger(String.valueOf(d1)));
    Rational r2 = new Rational(new BigInteger(String.valueOf(n2)),new BigInteger(String.valueOf(d2)));

    System.out.println(n1 + "/" + d1 + " + " + n2 + "/" + d2 + " = " + r1.add(r2).toString() );
    System.out.println(n1 + "/" + d1 + " - " + n2 + "/" + d2 + " = " + r1.subtract(r2).toString() );
    System.out.println(n1 + "/" + d1 + " * " + n2 + "/" + d2 + " = " + r1.multiply(r2).toString() );
    System.out.println(n1 + "/" + d1 + " / " + n2 + "/" + d2 + " = " + r1.divide(r2).toString() );
    System.out.println(r2.toString() + " is " + r2.doubleValue() );
  }





  // Data fields for numerator and denominator
  private BigInteger numerator = new BigInteger("0");
  private BigInteger denominator = new BigInteger("1");

  /** Construct a rational with default properties */
  public Rational() {
    this(BigInteger.ZERO, BigInteger.ONE);
  }

  /** Construct a rational with specified numerator and denominator */
  public Rational(BigInteger numerator, BigInteger denominator) {
    BigInteger gcd = gcd(numerator, denominator);
    this.numerator = numerator.multiply(
      new BigInteger(
        String.valueOf(
          denominator.compareTo(
            new BigInteger("0")
          )
          > 0 ? 1 : -1
        )
      )
    ).divide(gcd);
    this.denominator = denominator.abs().divide(gcd);
  }

  /** Find GCD of two numbers */
  private static BigInteger gcd(BigInteger n, BigInteger d) {
    BigInteger n1 = n.abs();
    BigInteger n2 = d.abs();
    BigInteger gcd = new BigInteger("1");
    BigInteger k = new BigInteger("1");

    while(k.compareTo(n1) < 1 && k.compareTo(n2) < 1) {
      if(n1.mod(k).compareTo(BigInteger.ZERO) == 0 && n2.mod(k).compareTo(BigInteger.ZERO) == 0)
        gcd = k.abs(); // copy the value Over
        k = k.add(BigInteger.ONE);
    }

    return gcd;
  }

  /** Return numerator */
  public BigInteger getNumerator() {
    return numerator;
  }

  /** Return denominator */
  public BigInteger getDenominator() {
    return denominator;
  }

  /** Add a rational number to this rational */
  public Rational add(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getDenominator() ).add(
      denominator.multiply(secondRational.getNumerator()));
    BigInteger d = denominator.multiply(secondRational.getDenominator());
    return new Rational(n, d);
  }

  /** Subtract a rational number from this rational */
  public Rational subtract(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getDenominator() ).subtract(
      denominator.multiply(secondRational.getNumerator()));
    BigInteger d = denominator.multiply(secondRational.getDenominator());
    return new Rational(n, d);
  }

  /** Multiply a rational number to this rational */
  public Rational multiply(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getNumerator());
    BigInteger d = denominator.multiply(secondRational.getDenominator());
    return new Rational(n, d);
  }

  /** Divide a rational number from this rational */
  public Rational divide(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getDenominator());
    BigInteger d = denominator.multiply(secondRational.getNumerator());
    return new Rational(n, d);
  }

  @Override // Override toString()
  public String toString() {
    if (denominator.longValue() == 1)
      return numerator.toString() + "";
    else
      return numerator.toString() + "/" + denominator.toString();
  }

  @Override // Override the equals method in the Object class
  public boolean equals(Object other) {
    if ((this.subtract((Rational)(other))).getNumerator().longValue() == 0)
      return true;
    else
      return false;
  }

  @Override // Implement the abstract intValue method in Number
  public int intValue() {
    return (int)doubleValue();
  }

  @Override // Implement the abstract floatValue method in Number
  public float floatValue() {
    return (float)doubleValue();
  }

  @Override // Implement the doubleValue method in Number
  public double doubleValue() {
    return numerator.longValue() * 1.0 / denominator.longValue();
  }

  @Override // Implement the abstract longValue method in Number
  public long longValue() {
    return (long)doubleValue();
  }

  @Override // Implement the compareTo method in Comparable
  public int compareTo(Rational o) {
    if (this.subtract(o).getNumerator().longValue() > 0)
      return 1;
    else if (this.subtract(o).getNumerator().longValue() < 0)
      return -1;
    else
      return 0;
  }
}
