package presentation.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RepoMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(RepoMain.class.getResource("test.fxml"));
		VBox vbox = Box.getInstance();
		//look up a anchorPane
		HBox pane = (HBox) root.lookup("#hbox");
		pane.getChildren().add(vbox);
		Scene scene = new Scene(root,1200,600);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(1000);
		primaryStage.setMinHeight(500);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
