/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.7
 *  Description: Account Class with demo
*/

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Cha9_7 {
  public static void main(String args[]) {
    int option; //store user selection
    ArrayList<Account> accounts = new ArrayList<>(); // store accounts
    Scanner choice = new Scanner(System.in);

    do {
      System.out.println("Select one of the following options:");
      System.out.println("1 - Create new account");
      System.out.println("2 - See balance");
      System.out.println("3 - Withdraw");
      System.out.println("4 - Deposit");
      System.out.println("5 - Get monthly interest");
      System.out.println("6 - Set annual interest rate");
      System.out.println("0 - Exit");

      option = choice.nextInt(); //get user selction

      //execute user seletion
      switch(option) {
        case 1:
          newAccount(choice, accounts);
          break;
        case 2:
          balance(choice, accounts);
          break;
        case 3:
          withdraw(choice, accounts);
          break;
        case 4:
          deposit(choice, accounts);
          break;
        case 5:
          interest(choice, accounts);
          break;
        case 6:
          monthlyRate(choice);
      }

    } while(option != 0);
  }

  //create new account
  public static void newAccount(Scanner choice, ArrayList<Account> accounts){
    int id;
    double startBal;
    System.out.println("Enter ID:");
    id = choice.nextInt();
    System.out.println("Enter starting balance");
    startBal = choice.nextDouble();
    accounts.add(new Account(id, startBal));
  }

  //get account balance from selected account
  public static void balance(Scanner choice, ArrayList<Account> accounts){
    int id;
    System.out.println("Enter ID:");
    id = choice.nextInt();
    for(int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if(a.getId() == id) {
        System.out.println("Balance: " + a.getBalance());
        return;
      }
    }
    System.out.println("Account not found");
  }

  // withdraw money from selected account
  public static void withdraw(Scanner choice, ArrayList<Account> accounts){
    int id;
    double amount;
    System.out.println("Enter ID:");
    id = choice.nextInt();
    for(int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if(a.getId() == id) {
        System.out.println("Withdraw amount:");
        amount = choice.nextDouble();
        a.withdraw(amount);
        return;
      }
    }
    System.out.println("Account not found");
  }

  //deposit money from selected account
  public static void deposit(Scanner choice, ArrayList<Account> accounts){
    int id;
    double amount;
    System.out.println("Enter ID:");
    id = choice.nextInt();
    for(int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if(a.getId() == id) {
        System.out.println("Deposit amount:");
        amount = choice.nextDouble();
        a.deposit(amount);
        return;
      }
    }
    System.out.println("Account not found");
  }

  //get monthly interest
  public static void interest(Scanner choice, ArrayList<Account> accounts){
    int id;
    System.out.println("Enter ID:");
    id = choice.nextInt();
    for(int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if(a.getId() == id) {
        System.out.println("Interest per month: " + a.getMonthlyInterest());
        return;
      }
    }
    System.out.println("Account not found");
  }

  //set annual interest rate
  public static void monthlyRate(Scanner choice){
    System.out.println("Enter annual interest rate:");
    Account.setAnnualInterestRate(choice.nextDouble());
  }
}

class Account {
  private int id;
  private double balance;
  private static double annualInterestRate = 0;
  private Date dateCreated;

  public Account(){
    id = 0;
    balance = 0.0;
    dateCreated = new Date();
  }

  public Account(int id, double balance) {
    this.id = id;
    this.balance = balance;
    dateCreated = new Date();
  }

  public int getId() {
    return id;
  }

  public double getBalance() {
    return balance;
  }

  public static double getAnnualInterestRate() {
    return annualInterestRate;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public static double getMonthlyInterestRate() {
    return annualInterestRate / 12.0;
  }

  public double getMonthlyInterest() {
    return getMonthlyInterestRate() * balance;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public static void setAnnualInterestRate(double rate) {
    annualInterestRate = rate/100.0;
  }

  public void withdraw(double amount) {
    balance -= amount;
  }

  public void deposit(double amount) {
    balance += amount;
  }
}
