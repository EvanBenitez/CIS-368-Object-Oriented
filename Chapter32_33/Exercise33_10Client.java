/*
 *  Name: Evan Benitez
 *  CSU ID: 2486032
 *  CIS 368: Programming Assignent Chapter 33, Question task 4
 *  Description: Chat server
*/

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Exercise33_10Client extends Application{
  @Override
  public void start(Stage primaryStage) {
    VBox pane = new VBox();
    GridPane grid = new GridPane();

    // name box
    Text name = new Text("Name:");
    TextField nameInput = new TextField();

    // input box
    Text enter = new Text("Enter text:");
    TextField enterInput = new TextField();

    // place in grid pane
    grid.add(name, 0,0);
    grid.add(nameInput, 1, 0);
    grid.add(enter, 0, 1);
    grid.add(enterInput, 1, 1);

    TextArea text = new TextArea();
    ScrollPane chatWindow = new ScrollPane(text);

    pane.getChildren().addAll(grid, chatWindow);

    Scene scene = new Scene(pane,450,200);
    primaryStage.setTitle("Chat server");
    primaryStage.setScene(scene);
    primaryStage.show();

    try {
      Socket socket = new Socket("localhost", 8000);
      DataInputStream input = new DataInputStream(socket.getInputStream());
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());

      enterInput.setOnKeyPressed(e -> {
        if(e.getCode().equals(KeyCode.ENTER)){
          String userName = nameInput.getText().trim();
          String userText = enterInput.getText().trim();

          try {
            output.writeUTF(userName);
            output.writeUTF(userText);
          }
          catch(IOException ex) {
            ex.printStackTrace();
          }
        }
      });

      new Thread(() ->{
        try {
          while(true) {
            String message = input.readUTF();
            Platform.runLater(() -> {
              text.appendText(message);
            });
          }
        }
        catch(IOException ex) {
          ex.printStackTrace();
        }
      }).start();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
