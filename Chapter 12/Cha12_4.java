/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 12, Question 12.4
 *  Description: IllegalArgumentException
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cha12_4 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Loan loan = new Loan();
    boolean pass = false; // switch for moving on to next text

    System.out.println("test constructor");
    do {
      try{
        System.out.println("Enter interest rate, number of years, and loan amount");
        loan = new Loan(scanner.nextDouble(),scanner.nextInt(),scanner.nextDouble());
        System.out.println("constructor pass");
        pass = true;
      }
      catch(IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
      catch(InputMismatchException e) {
        System.out.println("improper input types"); // catch input missmatch
        scanner.nextLine();
      }
    }while(!pass);


    pass = false;
    System.out.println("test setAnnualInterestRate");
    do {
      try{
        System.out.println("Enter interest rate");
        loan.setAnnualInterestRate(scanner.nextDouble());
        System.out.println("setAnnualInterestRate pass");
        pass = true;
      }
      catch(IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
      catch(InputMismatchException e) {
        System.out.println("improper input types"); // catch input missmatch
        scanner.nextLine();
      }
    }while(!pass);


    pass = false;
    System.out.println("test setNumberOfYears");
    do {
      try{
        System.out.println("Enter number of years");
        loan.setNumberOfYears(scanner.nextInt());
        System.out.println("setNumberOfYear pass");
        pass = true;
      }
      catch(IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
      catch(InputMismatchException e) {
        System.out.println("improper input types"); // catch input missmatch
        scanner.nextLine();
      }
    }while(!pass);


    pass = false;
    System.out.println("test setLoanAmount");
    do {
      try{
        System.out.println("Enter loan amount");
        loan.setLoanAmount(scanner.nextDouble());
        System.out.println("setLoanAmount pass");
        pass = true;
      }
      catch(IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
      catch(InputMismatchException e) {
        System.out.println("improper input types"); // catch input missmatch
        scanner.nextLine();
      }
    }while(!pass);
  }
}

class Loan {
  private double annualInterestRate;
  private int numberOfYears;
  private double loanAmount;
  private java.util.Date loanDate;

  /** No-arg constructor */
  public Loan() {
    this(2.5, 1, 1000);
  }

  /** Construct a loan with specified annual interest rate,
      number of years, and loan amount
    */
  public Loan(double annualInterestRate, int numberOfYears,
      double loanAmount) throws IllegalArgumentException {
    if(annualInterestRate<=0 || numberOfYears<=0 || loanAmount<=0)
      throw new IllegalArgumentException("Amounts must be greater that 0");

    this.annualInterestRate = annualInterestRate;
    this.numberOfYears = numberOfYears;
    this.loanAmount = loanAmount;
    loanDate = new java.util.Date();
  }

  /** Return annualInterestRate */
  public double getAnnualInterestRate() {
    return annualInterestRate;
  }

  /** Set a new annualInterestRate */
  public void setAnnualInterestRate(double annualInterestRate) throws IllegalArgumentException {
    if(annualInterestRate<=0)
      throw new IllegalArgumentException("Interest rate must be greater than 0");
    this.annualInterestRate = annualInterestRate;
  }

  /** Return numberOfYears */
  public int getNumberOfYears() {
    return numberOfYears;
  }

  /** Set a new numberOfYears */
  public void setNumberOfYears(int numberOfYears)  throws IllegalArgumentException {
    if(numberOfYears<=0)
      throw new IllegalArgumentException("Number of years must be greater than 0");
    this.numberOfYears = numberOfYears;
  }

  /** Return loanAmount */
  public double getLoanAmount() {
    return loanAmount;
  }

  /** Set a newloanAmount */
  public void setLoanAmount(double loanAmount)  throws IllegalArgumentException {
    if(loanAmount<=0)
      throw new IllegalArgumentException("Loan amount must be greater than 0");
    this.loanAmount = loanAmount;
  }

  /** Find monthly payment */
  public double getMonthlyPayment() {
    double monthlyInterestRate = annualInterestRate / 1200;
    double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
      (1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));
    return monthlyPayment;
  }

  /** Find total payment */
  public double getTotalPayment() {
    double totalPayment = getMonthlyPayment() * numberOfYears * 12;
    return totalPayment;
  }

  /** Return loan date */
  public java.util.Date getLoanDate() {
    return loanDate;
  }
}
