import javafx.application.Application;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ContentDisplay;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Cha16_17 extends Application {
  private int rVal = 0;
  private int gVal = 0;
  private int bVal = 0;
  private double oVal = 1.0;

  public void start(Stage primaryStage) {
    //BorderPane pane = new BorderPane();
    GridPane slidePane = new GridPane();
    VBox pane = new VBox(50.0);

    // create Sliders
    Slider rSlider = new Slider(0,255,0);
    Slider gSlider = new Slider(0,255,0);
    Slider bSlider = new Slider(0,255,0);
    Slider oSlider = new Slider(0,1.0,1.0);

    // create labels
    Label showColors = new Label("Show Colors");
    Label red = new Label("Red  ");
    Label green = new Label("Green  ");
    Label blue = new Label("Blue  ");
    Label opacity = new Label("Opacity  ");

    // set label Colors
    red.setTextFill(Color.RED);
    green.setTextFill(Color.GREEN);
    blue.setTextFill(Color.BLUE);

    // Load labels and sliders into GridPane
    slidePane.add(red,0,0);
    slidePane.add(rSlider,1,0);
    slidePane.add(green,0,1);
    slidePane.add(gSlider,1,1);
    slidePane.add(blue,0,2);
    slidePane.add(bSlider,1,2);
    slidePane.add(opacity,0,3);
    slidePane.add(oSlider,1,3);

    // set listeners for sliders
    rSlider.valueProperty().addListener(e -> {
          rVal = (int)rSlider.getValue();
        showColors.setTextFill(Color.rgb(rVal,gVal,bVal,oVal));
      });

    gSlider.valueProperty().addListener(e -> {
          gVal = (int)gSlider.getValue();
        showColors.setTextFill(Color.rgb(rVal,gVal,bVal,oVal));
      });

    bSlider.valueProperty().addListener(e -> {
          bVal = (int)bSlider.getValue();
        showColors.setTextFill(Color.rgb(rVal,gVal,bVal,oVal));
      });

    oSlider.valueProperty().addListener(e -> {
          oVal = oSlider.getValue();
        showColors.setTextFill(Color.rgb(rVal,gVal,bVal,oVal));
      });
    // load pane and set aligments
    pane.getChildren().addAll(showColors,slidePane);
    slidePane.setVgap(10.0);
    slidePane.setAlignment(Pos.CENTER);
    pane.setAlignment(Pos.CENTER);


    // create and set Scene
    Scene scene = new Scene(pane,300,200);

    // set Stage
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
