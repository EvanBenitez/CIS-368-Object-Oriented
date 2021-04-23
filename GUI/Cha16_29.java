import java.util.GregorianCalendar;
import java.util.Calendar;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.shape.Polyline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

public class Cha16_29 extends Application {

  public void start(Stage primaryStage) {

    // create CalendarPane
    CalendarPane pane = new CalendarPane();

    Scene scene = new Scene(pane,500,350);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class CalendarPane extends GridPane {
  private GregorianCalendar calendar;
  private Label[] dates;
  Text month = new Text();

  public CalendarPane() {
    // create calendar
    calendar = new GregorianCalendar();
    calendar.set(Calendar.DAY_OF_MONTH,1);

    // set pane settings
    setHgap(40);
    setVgap(20);
    setAlignment(Pos.CENTER);
    setStyle("-fx-background: #222222");

    //create Fonts
    Font font = new Font(12);
    Font dFont = new Font(16);

    // create labels for calendar
    month = new Text();
    month.setFill(Color.WHITE);
    month.setFont(dFont);
    Text[] days = new Text[7];
    days[0] = new Text("Su");
    days[1] = new Text("Mo");
    days[2] = new Text("Tu");
    days[3] = new Text("We");
    days[4] = new Text("Th");
    days[5] = new Text("Fr");
    days[6] = new Text("Sa");
    for(int i=0; i<7; i++) {
      days[i].setFont(font);
      days[i].setFill(Color.WHITE);
    }
    dates = new Label[42];

    // create buttons
    Polyline up = new Polyline(0,7,7,0,14,7);
    Polyline down = new Polyline(0,0,7,7,14,0);
    up.setStroke(Color.WHITE);
    down.setStroke(Color.WHITE);
    Button upBtn = new Button("",up);
    Button downBtn = new Button("",down);
    upBtn.setStyle("-fx-background-color: #222222");
    downBtn.setStyle("-fx-background-color: #222222");

    // load up GridPane
    // insert month
    add(month,0,0);
    setColumnSpan(month,5);

    // Place label buttons
    add(upBtn,5,0);
    add(downBtn,6,0);

    // Place days texts
    for(int i=0; i<7; i++){
      add(days[i],i,1);
      setHalignment(days[i],HPos.CENTER);
    }

    // place dates texts
    for(int i=0; i<42; i++) {
      dates[i] = new Label();
      add(dates[i],(i%7),(i/7)+2);
      dates[i].setFont(dFont);
      dates[i].setPadding(new Insets(0,5,0,5));
      setHalignment(dates[i],HPos.CENTER);
    }

    // wire up event listeners
    downBtn.setOnAction(e -> {
      monthInc();
      print();
      System.out.println(calendar.getTime());
    });

    upBtn.setOnAction(e -> {
      monthDec();
      print();
      System.out.println(calendar.getTime());
    });

    // print current Calendar
    print();
  }

  private void print() {
    Color gray = new Color(1,1,1,0.25);
    int beforeDays = calendar.get(Calendar.DAY_OF_WEEK)-2; //last grid spot for previous month
    //get comparison month
    monthDec();
    GregorianCalendar comp = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
    monthInc();
    // get current Calendar.
    GregorianCalendar current = new GregorianCalendar();

    // print previous month days
    for(int i=0; i<=beforeDays; i++) {
      dates[i].setText(String.valueOf(comp.getActualMaximum(Calendar.DAY_OF_MONTH) - beforeDays + i));
      dates[i].setTextFill(gray);
      // highlight current day
      if( ((calendar.get(Calendar.MONTH) == current.get(Calendar.MONTH)+1 && calendar.get(Calendar.YEAR) == current.get(Calendar.YEAR)) ||
          (calendar.get(Calendar.MONTH) == 0 && current.get(Calendar.MONTH) == 11 && calendar.get(Calendar.YEAR) == current.get(Calendar.YEAR)+1) ) &&
          (comp.getActualMaximum(Calendar.DAY_OF_MONTH) - beforeDays + i == current.get(Calendar.DAY_OF_MONTH)) ){
            dates[i].setStyle("-fx-background-color: #1122ff; -fx-border-color: #000000; -fx-border-width: 2px; -fx-border-style: solid;");
          }
      else {
        dates[i].setStyle("");
      }
    }

    // print current days
    for(int i=1; i<=calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
      dates[i+beforeDays].setText(String.valueOf(i));
      dates[i+beforeDays].setTextFill(Color.WHITE);
      // highlight current day
      if( ((calendar.get(Calendar.MONTH) == current.get(Calendar.MONTH) && calendar.get(Calendar.YEAR) == current.get(Calendar.YEAR)) ) &&
          i == current.get(Calendar.DAY_OF_MONTH) ){
            dates[i+beforeDays].setStyle("-fx-background-color: #1122ff; -fx-border-color: #000000; -fx-border-width: 2px; -fx-border-style: solid;");
          }
      else {
        dates[i+beforeDays].setStyle("");
      }
    }

    // fill in next month days
    for(int i = 1; i<=(42-calendar.getActualMaximum(Calendar.DAY_OF_MONTH)-beforeDays-1); i++){
      dates[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+beforeDays+i].setText(String.valueOf(i));
      dates[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+beforeDays+i].setTextFill(gray);
      // highlight current day
      if( ((calendar.get(Calendar.MONTH) == current.get(Calendar.MONTH)-1 && calendar.get(Calendar.YEAR) == current.get(Calendar.YEAR)) ||
          (calendar.get(Calendar.MONTH) == 11 && current.get(Calendar.MONTH) == 0 && calendar.get(Calendar.YEAR) == current.get(Calendar.YEAR)-1) ) &&
          (comp.getActualMaximum(Calendar.DAY_OF_MONTH) - beforeDays + i == current.get(Calendar.DAY_OF_MONTH)) ){
            dates[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+beforeDays+i].setStyle("-fx-background-color: #1122ff; -fx-border-color: #000000; -fx-border-width: 2px; -fx-border-style: solid;");
          }
      else {
        dates[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+beforeDays+i].setStyle("");
      }
    }

    // get month and YEAR
    month.setText(getMonth(calendar.get(Calendar.MONTH))+ " " + calendar.get(Calendar.YEAR));

  }

  // move to the next month
  private void monthInc() {
    int year, month, day;
    day = 1;
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) + 1;
    // role over month and year if necessary.
    if(month>11) {
      month = 0;
      year++;
    }
    calendar.set(year,month,day);
  }

  // move to the previoud month
  private void monthDec() {
    int year, month, day;
    day = 1;
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH) - 1;
    // role over month and year if necessary.
    if(month<0) {
      month = 11;
      year--;
    }
    calendar.set(year,month,day);
  }

  private static String getMonth(int monthNum) {
    switch(monthNum) {
      case 0:
        return "January";
      case 1:
        return "Febraury";
      case 2:
        return "March";
      case 3:
        return "April";
      case 4:
        return "May";
      case 5:
        return "June";
      case 6:
        return "July";
      case 7:
        return "August";
      case 8:
        return "September";
      case 9:
        return "October";
      case 10:
        return "November";
      case 11:
        return "December";
      default:
        System.out.println("No matching month");
        System.exit(1);
    }
    return null;
  }
}
