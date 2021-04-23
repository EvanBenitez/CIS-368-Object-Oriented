import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.control.SelectionMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Cha16_16 extends Application {
  private ComboBox<String> mode = new ComboBox<>();
  ListView<String> listView;

  public void start(Stage primaryStage) {
    String[] countries = {"Canada", "Cuba", "Dominica", "Haiti", "Jamaica", "Mexico", "USA"};
    listView = new ListView<>(FXCollections.observableArrayList(countries));

    mode.getItems().add("Single");
    mode.getItems().add("Multiple");
    mode.getSelectionModel().select(0);
    mode.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        setSelectionMode();
      }
    });

    HBox topPane = new HBox(5.0);
    Label select = new Label("Selection Mode:");
    topPane.getChildren().addAll(select, mode);
    topPane.setAlignment(Pos.CENTER);

    HBox bottomPane = new HBox(5.0);
    Label output = new Label("Non Selected");
    bottomPane.getChildren().add(output);

    listView.getSelectionModel().selectedItemProperty().addListener(e -> {
      StringBuilder line = new StringBuilder();
      line.append("The selected item(s) are ");
      for(int i = 0; i<listView.getSelectionModel().getSelectedItems().size(); i++){
        line.append(listView.getSelectionModel().getSelectedItems().get(i) + " ");
      }
      output.setText(line.toString());
    });

    BorderPane pane = new BorderPane();
    pane.setTop(topPane);
    pane.setCenter(listView);
    pane.setBottom(bottomPane);

    Scene scene = new Scene(pane, 500, 300);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void setSelectionMode() {
    if(mode.getSelectionModel().getSelectedItem().equals("Multiple")){
      listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    else {
      listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
  }
}
