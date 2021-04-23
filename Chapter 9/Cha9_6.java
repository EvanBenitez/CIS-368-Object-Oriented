/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 9, Question 9.6
 *  Description: StopeWatch Class
*/

import java.util.Date;
import java.util.Random;
public class Cha9_6 {
  public static void main(String args[]){
    int[] ran = new int[100000]; 
    Random r = new Random();
    StopWatch time = new StopWatch();

    //generate 100,000 random numbers
    for(int i=0;i<100000;i++) {
      ran[i] = r.nextInt();
    }

    time.start(); //start watch

    //sort Numbers
    for(int j=0; j<99999; j++) {
      int minIndex = j;
      for(int k=j+1;k<100000; k++) {
        if(ran[minIndex] < ran[k]) {
          minIndex = k;
        }
      }
      int temp = ran[j];
      ran[j] = ran[minIndex];
      ran[minIndex] = temp;
    }
    time.stop(); //stop watch

    //displays elapsed time
    System.out.println(time.getElapsedTime());
  }
}

class StopWatch {
  private Date  startTime;
  private Date endTime;

  public StopWatch() {
    startTime = new Date();
    endTime = new Date(startTime.getTime()); //incase time has elapsed since startTime was initialized
  }

  public void start() {
    startTime = new Date();
  }

  public void stop() {
    endTime = new Date();
  }
  public Date getStartTime() {
    return startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public long getElapsedTime() {
    return endTime.getTime() - startTime.getTime();
  }
}
