/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 13, Question 13.6
 *  Description: ComparableCircle
*/

import java.util.Scanner;

public class ComparableCircle extends Circle implements Comparable<GeometricObject> {
  public ComparableCircle() {
    super();
  }

  public ComparableCircle(double radius) {
    super(radius);
  }

  public int compareTo(GeometricObject c) {
    if(this.getArea() > c.getArea())
      return 1;
    else if(this.getArea() == c.getArea())
      return 0;
    else
      return -1;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter radius of circle 1: ");
    ComparableCircle c1 = new ComparableCircle(scanner.nextDouble());
    System.out.print("Enter radius of circle 2: ");
    ComparableCircle c2 = new ComparableCircle(scanner.nextDouble());

    int comp = c1.compareTo(c2);
    if(comp>0) {
      System.out.println("Circle 1 is larger");
    }
    else if(comp==0) {
      System.out.println("Circle 1 and Circle 2 are of equal size");
    }
    else {
      System.out.println("Circle 2 is larger");
    }

    System.out.println("Enter width and height of a rectangle");
    Rectangle rect = new Rectangle(scanner.nextDouble(),scanner.nextDouble());

    int comp2 = c1.compareTo(rect);
    if(comp2>0) {
      System.out.println("Circle 1 is larger than Rectangle");
    }
    else if(comp2==0) {
      System.out.println("Circle 1 and Rectangle are of equal size");
    }
    else {
      System.out.println("Rectangle is larger than Circle 1");
    }

    int comp3 = c2.compareTo(rect);
    if(comp3>0) {
      System.out.println("Circle 2 is larger Rectangle");
    }
    else if(comp3==0) {
      System.out.println("Circle 2 and Rectangle are of equal size");
    }
    else {
      System.out.println("Rectangle is larger than Circle 2");
    }

  }
}

// COPIED
abstract class GeometricObject {
  private String color = "white";
  private boolean filled;
  private java.util.Date dateCreated;

  /** Construct a default geometric object */
  protected GeometricObject() {
    dateCreated = new java.util.Date();
  }

  /** Construct a geometric object with color and filled value */
  protected GeometricObject(String color, boolean filled) {
    dateCreated = new java.util.Date();
    this.color = color;
    this.filled = filled;
  }

  /** Return color */
  public String getColor() {
    return color;
  }

  /** Set a new color */
  public void setColor(String color) {
    this.color = color;
  }

  /** Return filled. Since filled is boolean,
   *  the get method is named isFilled */
  public boolean isFilled() {
    return filled;
  }

  /** Set a new filled */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /** Get dateCreated */
  public java.util.Date getDateCreated() {
    return dateCreated;
  }

  @Override
  public String toString() {
    return "created on " + dateCreated + "\ncolor: " + color +
      " and filled: " + filled;
  }

  /** Abstract method getArea */
  public abstract double getArea();

  /** Abstract method getPerimeter */
  public abstract double getPerimeter();
}

// COPIED
class Circle extends GeometricObject {
  private double radius;

  public Circle() {
  }

  public Circle(double radius) {
    this.radius = radius;
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  @Override /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }

  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }

  @Override /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /* Print the circle info */
  public void printCircle() {
    System.out.println("The circle is created " + getDateCreated() +
      " and the radius is " + radius);
  }
}

// COPIED
class Rectangle extends GeometricObject {
  private double width;
  private double height;

  public Rectangle() {
  }

  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /** Return width */
  public double getWidth() {
    return width;
  }

  /** Set a new width */
  public void setWidth(double width) {
    this.width = width;
  }

  /** Return height */
  public double getHeight() {
    return height;
  }

  /** Set a new height */
  public void setHeight(double height) {
    this.height = height;
  }

  @Override /** Return area */
  public double getArea() {
    return width * height;
  }

  @Override /** Return perimeter */
  public double getPerimeter() {
    return 2 * (width + height);
  }
}
