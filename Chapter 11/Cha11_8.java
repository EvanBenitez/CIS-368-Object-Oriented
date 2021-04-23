/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 11, Question 11.8
 *  Description: Account Class with demo
*/

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Cha11_8 {
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
      System.out.println("7 - Get transaction information");
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
          break;
        case 7:
          transactions(choice, accounts);
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

  // Display Transaction information
  public static void transactions(Scanner choice, ArrayList<Account> accounts) {
    int id;
    System.out.println("Enter ID:");
    id = choice.nextInt();
    for(int i = 0; i < accounts.size(); i++) {
      Account a = accounts.get(i);
      if(a.getId() == id) {
        System.out.println(a.getName() + " ID: " + a.getId());
        ArrayList<Transaction> trans = a.getTransactions();
        for(int t=0; t<a.getTransactions().size(); t++) {
          Transaction line = trans.get(t);
          System.out.println("    " + line.getDate() + " Type: " + line.getType() + " Amount: " +
          line.getAmount() + " Balance: " + line.getBalance() + " Description: " + line.getDescription());
        }

        return;
      }
    }
    System.out.println("Account not found");
  }

}

class Account {
  private int id;
  private String name;
  private double balance;
  private static double annualInterestRate = 0;
  private Date dateCreated;
  private ArrayList<Transaction> transactions;

  public Account(){
    id = 0;
    balance = 0.0;
    dateCreated = new Date();
    name = "";
    transactions = new ArrayList<>();
  }

  public Account(int id, double balance) {
    this();
    this.id = id;
    this.balance = balance;
  }

  public Account(int id, String name, double balance) {
    this(id,balance);
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
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

  public ArrayList<Transaction> getTransactions() {
    return transactions;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public static void setAnnualInterestRate(double rate) {
    annualInterestRate = rate/100.0;
  }

  public void withdraw(double amount) {
    balance -= amount;
    transactions.add(new Transaction('W', amount, balance, "comment:"));
  }

  public void deposit(double amount) {
    balance += amount;
    transactions.add(new Transaction('D', amount, balance, "comment:"));
  }

}

class Transaction {
  private Date date;
  private char type;
  private double amount;
  private double balance;
  private String description;

  public Transaction(char type, double amount, double balance, String description) {
    date = new Date();
    this.type = type;
    this.amount = amount;
    this.balance = balance;
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public char getType() {
    return type;
  }

  public double getAmount() {
    return amount;
  }

  public double getBalance() {
    return balance;
  }

  public String getDescription() {
    return description;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setType(char type) {
    this.type = type;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
