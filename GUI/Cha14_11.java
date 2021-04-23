import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class Cha14_11 extends Application {
	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		StackPane stack = new StackPane();
		Scene scene = new Scene(pane,700,500);

		// Create face
		Circle face = new Circle(0,0,pane.getWidth()>pane.getHeight()? pane.getHeight()/4 : pane.getWidth()/4);
		face.setFill(Color.ORANGE);

		// Create smile
		Arc smile = new Arc(0,0,face.getRadius()*0.75,face.getRadius()*0.70,0,-180);
		smile.setType(ArcType.OPEN);
		smile.setFill(Color.ORANGE.brighter());
		smile.setStroke(Color.BLACK);
		smile.setStrokeWidth(3);

		//create eyes
		Ellipse left = new Ellipse(0,0,face.getRadius()*0.085,face.getRadius()*0.17);
		Ellipse right = new Ellipse(0,0,face.getRadius()*0.085,face.getRadius()*0.17);

		//add items to stack pane
		stack.getChildren().addAll(face,smile,left,right);
		//align smile
		stack.setMargin(smile,new Insets(face.getRadius()*0.6, 0, 0, 0));
		//allign eyes
		stack.setMargin(left, new Insets(0,face.getRadius()*0.65,face.getRadius()*0.50,0));
		stack.setMargin(right, new Insets(0,0,face.getRadius()*0.50,face.getRadius()*0.65));

		pane.setCenter(stack);
    primaryStage.setScene(scene);
    primaryStage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
