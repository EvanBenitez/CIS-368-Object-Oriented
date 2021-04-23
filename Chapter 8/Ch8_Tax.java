/*
* Name: Evan Benitez
* CSUID: 2486032
* Assignment Chapter 8 Exersice 8.12
* Description: Calculate tax
*/

import java.util.Scanner;

public class Ch8_Tax {
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);

    int[][] brackets =
      { {8350, 33950, 82250, 171550, 372950},    // Single filer
        {16700, 67900, 137050, 208850, 372950},  // Married Jointly or qualifying widow(er)
        {8350, 33950, 68525, 104425, 186475},     // Married filing separately
        {11950, 45500, 117450, 190200, 372950}    // Head of household
      };

    double[] rates = {0.1, 0.15, 0.25, 0.28, 0.33, 0.35};

    //get filing status
    System.out.println("Please enter your filing status: \n" +
                        "0 - Single filer \n" +
                        "1 - Married jointly or qualifying widow(er) \n" +
                        "2 - Married filing separately\n" +
                        "3 - Head of the household");
    int status = input.nextInt();

    //get income
    System.out.println("Enter income:");
    int income = input.nextInt();

    double tax = 0;

    //compute income
    for(int i = 4; i >= 0; i--){
      if( income > brackets[status][i]){ //determine if tax bracket applicable
        tax += (income - brackets[status][i]) * rates[i+1]; //calculate tax at spacified rate, add 1 to i to get proper tax rate
        income = brackets[status][i]; // the rest of the income that needs to be taxed
      }
    }
    tax += income * rates[0]; //get lowest tax bracket tax

    System.out.printf("Tax owed is $%.2f", tax);
  }
}
