package presentation.ui;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class MainController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,1190,680);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML private ImageView image;
	@FXML private AnchorPane rightComponentParent;
	@FXML private Button buttonRepoSearch;
	@FXML private Button buttonUserSearch;
	@FXML private Button buttonAboutUs;
	
	
	@FXML
	private void imageMove() {
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
				.addAll(new KeyFrame(Duration.ZERO, // set start position at 0
						new KeyValue(image.translateXProperty(), 0),
						new KeyValue(image.translateYProperty(), 0)),
						new KeyFrame(new Duration(1000), // set middle position at 1s
						new KeyValue(image.translateXProperty(), 750),
						new KeyValue(image.translateYProperty(), 0)),
						new KeyFrame(new Duration(2000), // set end position at 2s
						new KeyValue(image.translateXProperty(), 1200),
						new KeyValue(image.translateYProperty(), 0)));
		timeline.play();
	}
	@FXML
	private void onRepoSearchClicked(MouseEvent event) {
		buttonRepoSearch.setDisable(true);
		buttonUserSearch.setDisable(false);
		buttonAboutUs.setDisable(false);
		rightComponentParent.getChildren().clear();
		try {
			rightComponentParent.getChildren().add(RepositorySearchController.getInstance());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("!!!");
		}
	}
	
	@FXML 
	private void onUserSearchClicked(MouseEvent event){
		buttonRepoSearch.setDisable(false);
		buttonUserSearch.setDisable(true);
		buttonAboutUs.setDisable(false);
		rightComponentParent.getChildren().clear();
		try {
			rightComponentParent.getChildren().add(UserSearchController.getInstance());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("???");
		}
	}
	
	@FXML
	private void onAboutUsClicked(MouseEvent event){
		buttonRepoSearch.setDisable(false);
		buttonUserSearch.setDisable(false);
		buttonAboutUs.setDisable(true);
		rightComponentParent.getChildren().clear();
	}

}
