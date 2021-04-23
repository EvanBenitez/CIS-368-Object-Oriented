/*
* Name: Evan Benitez
* CSUID: 2486032
* Assignment Chapter 8 Exersice 8.20
* Description: Connect four game
*/

import java.util.Scanner;

public class Ch8_Connect {
  public static void main(String[] args){
    Grid game = new Grid(); //create a grid to play on
    int player = 2; //repersents the player
    Scanner move = new Scanner(System.in);

    do{
      player = (player % 2) + 1; //get next player
      game.print();
      System.out.print("\nDrop a " + (player==1 ? "red" : "blue") + " disk at column (0-6): ");
      game.drop(move.nextInt(),player);
    }while(!game.check());

    System.out.println("\n\n" + (player==1 ? "Red" : "Blue") + " Wins. Game Ends!");
  }
}

// Grid object
// 0 = empty
// 1 = red
//2 = blue
class Grid {
  private int[][] grid; //representation of the board
  private int[] lastMove = new int[3]; //keep track of last move, used to speed up checking
                                       //O index for row, 1 for column, 2 for color
  final int rowSize = 6;
  final int colSize = 7;

  //create empty grid (0 represents empty)
  public Grid(){
    grid = new int[rowSize][colSize];

    //inicialize with zero's
    for(int r = 0; r<6; r++){
      for(int c = 0; c<7; c++){
        grid[r][c] = 0;
      }
    }

  }

  //drop a token into the Grid
  //returns 0 if token was not placed
  // 1 for red, 2 for blue
  public boolean drop(int col, int color){
    if(color != 1 && color != 2)
      return false; //invalid token

    //Place token in grid, starts at high index
    for(int i = 5; i>=0; i--){
      if(grid[i][col] == 0){
        grid[i][col] = color;
        lastMove[0] = i;
        lastMove[1] = col;
        lastMove[2] = color;
        return true;
      }
    }
    return false; //overflow or not a column
  }

  //get the value at a specific spot
  public String get(int row, int col){
    if(grid[row][col] == 2)
      return "blue";
    if(grid[row][col] == 1)
      return "red";
    return "empty"; // if not red or blue, space is empty
  }

  //print the Grid
  public void print(){
    StringBuilder print = new StringBuilder();

    for(int row = 0; row < 6; row++){ //cycle through rows
      print.append('|');
      for(int col = 0; col < 7; col++){ //cycle through columns
        if(grid[row][col]==0)
          print.append(' ');
        else if(grid[row][col]==1)
          print.append('R');
        else
          print.append('B');
        print.append('|');
      }
      print.append('\n'); //breakline for next row
    }
    System.out.println(print.toString());
  }

  //check for four in a rows for last player to place a token
  //this methed works by starting at the newly added token and moving from
  //there, first forwards, then in the reverse direction starting at the newly
  //added token, accept in the case of vertical match,in which case reverse is
  //not necessary
  public boolean check(){
    int connect = 1; //number of tokens in a line

    //check straight down
    if(lastMove[0] <= 2){ //check to see if there are at least 4 tokens down
      for(int i = lastMove[0]+1; i<=lastMove[0]+3;i++){
        if(grid[i][lastMove[1]] == lastMove[2]){
          connect++;
        }
        else{
          break;
        }
      }
      if(connect >= 4)
        return true;

      connect = 1; //reset connect
    }

    //check horizontal
    //first check right
    for(int i = lastMove[1]+1; i<=lastMove[1]+3 && !(i>=colSize); i++){
      if(grid[lastMove[0]][i] == lastMove[2]){
        connect++;
      }
      else{
        break;
      }
    }
    if(connect >= 4)
      return true;

    //then check left
    for(int i = lastMove[1]-1; i>=lastMove[1]-3 && !(i<0); i--){
      if(grid[lastMove[0]][i] == lastMove[2]){
        connect++;
      }
      else{
        break;
      }
    }
    if(connect >= 4)
      return true;

    connect = 1; //reset connect

    //check diagnals
    //rising diagnal
    for(int i=1; i<=3; i++){
      if((lastMove[0]-i >= 0 && lastMove[1]+i < colSize) && grid[lastMove[0]-i][lastMove[1]+i] == lastMove[2]){ //check for bounds at begining
        connect++;
      }
      else{
        break;
      }
    }

    for(int i=1; i<=3; i++){
      if((lastMove[0]+i < rowSize && lastMove[1]-i >= 0) && grid[lastMove[0]+i][lastMove[1]-i] == lastMove[2]){ //check for bounds at begining
        connect++;
      }
      else{
        break;
      }
    }
    if(connect >= 4)
      return true;

    connect = 1; //reset connect

    //falling diagnal
    for(int i=1; i<=3; i++){
      if((lastMove[0]+i < rowSize && lastMove[1]+i < colSize) && grid[lastMove[0]+i][lastMove[1]+i] == lastMove[2]){ //check for bounds at begining
        connect++;
      }
      else{
        break;
      }
    }

    for(int i=1; i<=3; i++){
      if((lastMove[0]-i >= 0 && lastMove[1]-i >= 0) && grid[lastMove[0]-i][lastMove[1]-i] == lastMove[2]){ //check for bounds at begining
        connect++;
      }
      else{
        break;
      }
    }
    if(connect >= 4)
      return true;

    return false; //no four found
  }
}
