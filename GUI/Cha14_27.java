import javafx.application.Application;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Cha14_27 extends Application {
  @Override
  public void start(Stage primaryStage) {
    ClockPane clock = new ClockPane();
    Scene scene = new Scene(clock,700,500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class ClockPane extends Pane {
  private int hour;
  private int minute;
  private int second;

  /** Construct a default clock with the current time*/
  public ClockPane() {
    setCurrentTime();
  }

  /** Construct a clock with specified hour, minute, and second */
  public ClockPane(int hour, int minute, int second) {
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  /** Return hour */
  public int getHour() {
    return hour;
  }

  /** Set a new hour */
  public void setHour(int hour) {
    this.hour = hour;
    paintClock();
  }

  /** Return minute */
  public int getMinute() {
    return minute;
  }

  /** Set a new minute */
  public void setMinute(int minute) {
    this.minute = minute;
    paintClock();
  }

  /** Return second */
  public int getSecond() {
    return second;
  }

  /** Set a new second */
  public void setSecond(int second) {
    this.second = second;
    paintClock();
  }

  /* Set the current time for the clock */
  public void setCurrentTime() {
    // Construct a calendar for the current date and time
    Calendar calendar = new GregorianCalendar();

    // Set current hour, minute and second
    this.hour = calendar.get(Calendar.HOUR_OF_DAY);
    this.minute = calendar.get(Calendar.MINUTE);
    this.second = calendar.get(Calendar.SECOND);

    paintClock(); // Repaint the clock
  }

  /** Paint the clock */
  private void paintClock() {
    // Initialize clock parameters
    double clockRadius =
      Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
    double centerX = getWidth() / 2;
    double centerY = getHeight() / 2;

    // Draw circle
    Circle circle = new Circle(centerX, centerY, clockRadius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);

    // Draw hour markings
    Line[] clockMarks = new Line[12];
    for(int i=0; i<12; i++) {
      double startX = centerX + (clockRadius - clockRadius / 15)* Math.sin(i * Math.PI / 6);
      double endX = centerX + (clockRadius - clockRadius / 100) * Math.sin(i * Math.PI / 6);
      double startY = centerY + (clockRadius - clockRadius / 15)* Math.cos(i * Math.PI / 6);
      double endY = centerY + (clockRadius - clockRadius / 100) * Math.cos(i * Math.PI / 6);
      clockMarks[i] = new Line(startX, startY, endX, endY);
      clockMarks[i].setStrokeWidth(4);
    }

    // print numbers
    Font font =  Font.font("", FontWeight.BOLD, 42);
    //Text t12 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
    //Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
    //Text t6 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
    //Text t9 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
    Text[] t = new Text[12];
    for(int i = 1; i<=12; i++) {
      double X = centerX + (clockRadius - clockRadius / 5.5) * Math.sin(i * Math.PI / 6);
      double Y = centerY - (clockRadius - clockRadius / 5.5) * Math.cos(i * Math.PI / 6);
      // Correct text misalignment
      double Xcorr = i>=10 ? clockRadius/9 : clockRadius/18;
      double Ycorr = clockRadius/12;
      t[i-1] = new Text(X-Xcorr,Y+Ycorr,String.valueOf(i));
      t[i-1].setFont(font);
    }

    // Draw second hand
    double sLength = clockRadius * 0.8;
    double secondX = centerX + sLength *
      Math.sin(second * (2 * Math.PI / 60));
    double secondY = centerY - sLength *
      Math.cos(second * (2 * Math.PI / 60));
    Line sLine = new Line(centerX, centerY, secondX, secondY);
    sLine.setStroke(Color.RED);
    sLine.setStrokeWidth(4);

    // Draw minute hand
    double mLength = clockRadius * 0.65;
    double xMinute = centerX + mLength *
      Math.sin(minute * (2 * Math.PI / 60));
    double minuteY = centerY - mLength *
      Math.cos(minute * (2 * Math.PI / 60));
    Line mLine = new Line(centerX, centerY, xMinute, minuteY);
    mLine.setStroke(Color.BLACK);
    mLine.setStrokeWidth(4);

    // Draw hour hand
    double hLength = clockRadius * 0.5;
    double hourX = centerX + hLength *
      Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    double hourY = centerY - hLength *
      Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
    Line hLine = new Line(centerX, centerY, hourX, hourY);
    hLine.setStroke(Color.BLACK);
    hLine.setStrokeWidth(4);

    // Draw inner circle
    Circle innerCircle = new Circle(centerX,centerY,clockRadius/20);
    innerCircle.setFill(Color.RED);

    getChildren().clear(); // Clear the pane
    getChildren().addAll(circle, clockMarks[0], clockMarks[1], clockMarks[2],
    clockMarks[3], clockMarks[4], clockMarks[5], clockMarks[6], clockMarks[7],
    clockMarks[8], clockMarks[9], clockMarks[10], clockMarks[11], t[0],
    t[1], t[2], t[3], t[4], t[5], t[6], t[7], t[8], t[9], t[10], t[11], sLine, mLine, hLine, innerCircle);
  }

  @Override
  public void setWidth(double width) {
    super.setWidth(width);
    paintClock();
  }

  @Override
  public void setHeight(double height) {
    super.setHeight(height);
    paintClock();
  }
}
