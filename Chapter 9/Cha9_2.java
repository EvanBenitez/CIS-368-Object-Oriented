/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.2
 *  Description: Stock Class
*/

public class Cha9_2 {
  public static void main(String args[]) {
    Stock stock = new Stock("ORCL", "OracleCorporation");
    stock.previousClosingPrice = 34.5;
    stock.currentPrice = 34.35;
    System.out.println(stock.symbol + " stock change: " + stock.getChangedPercent());
  }
}

class Stock {
  String symbol;
  String name;
  double previousClosingPrice;
  double currentPrice;

  Stock(String symbol, String name) {
    this.symbol = symbol;
    this.name = name;
  }

  public double getChangedPercent() {
    return (currentPrice / previousClosingPrice - 1) * 100;
  }
}
