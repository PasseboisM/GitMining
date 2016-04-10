package presentation.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CSSTester extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		AnchorPane mainAnchorPane = loader.load();
		mainAnchorPane.getStylesheets().add(CSSTester.class.getResource("menu.css").toExternalForm());
		Scene scene = new Scene(mainAnchorPane,1190,660);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
