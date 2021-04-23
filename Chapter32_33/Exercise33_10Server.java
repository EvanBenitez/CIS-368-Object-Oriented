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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Exercise33_10Server extends Application{
  private TextArea text = new TextArea();
  private ArrayList<DataOutputStream> clients = new ArrayList<>();

  @Override
  public void start(Stage primaryStage) {

    ScrollPane pane = new ScrollPane(text);
    Scene scene = new Scene(pane,475,200);
    primaryStage.setTitle("Chat server");
    primaryStage.setScene(scene);
    primaryStage.show();

    new Thread( () -> {
      try {
        ServerSocket server = new ServerSocket(8000);
        Platform.runLater(() -> text.appendText("MultiThreadServer started at " + new Date() + "\n"));

        while(true){
          Socket socket = server.accept();
          new Thread(new chat(socket)).start();
        }
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  private class chat implements Runnable{
    private Socket socket;

    public chat(Socket socket){
      this.socket = socket;
    }
    @Override
    public void run() {
      try {
        DataInputStream userInput = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        clients.add(output);
        InetAddress address = socket.getInetAddress();

        Platform.runLater(() -> text.appendText("Connection from Socket[addr=/" + address.getHostAddress() + ",host=" +
              address.getHostName() + ",time=" + new Date() + "]\n"));

        while(true) {
          String name = userInput.readUTF();
          String text = userInput.readUTF();

          for(int i=0; i<clients.size(); i++) {
            clients.get(i).writeUTF(name + ": " + text + "\n");
          }
        }
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
