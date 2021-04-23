/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 11, Question 11.10
 *  Description:  MyStack
*/

import java.util.ArrayList;
import java.util.Scanner;

public class MyStack extends ArrayList<Object> {

  public MyStack() {
    super();
  }

  @Override
  public boolean isEmpty() {
    return this.isEmpty();
  }

  public int getSize() {
    return this.size();
  }

  public Object peek() {
    return this.get(this.size() - 1);
  }

  public Object pop() {
    Object o = this.get(this.size() - 1);
    this.remove(this.size() - 1);
    return o;
  }

  public void push(Object o) {
    this.add(o);
  }

  @Override
  public String toString() {
    return "stack: " + this.toString();
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    MyStack stack = new MyStack();

    System.out.println("Enter five strings");
    for(int i = 0; i < 5; i++) {
      stack.push(input.nextLine());
    }

    System.out.println("In reverse order, you entered:");
    for(int i = stack.getSize(); i > 0; i--) {
      System.out.println(stack.pop());
    }
  }
}
