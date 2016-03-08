package presentation.ui;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class RepoMain extends Application{

	
	@FXML private ImageView image;
	@FXML private AnchorPane pane;
	@FXML private VBox repoSearchVBox;
	@FXML private Button buttonRepoSearch;
	@FXML private Button buttonUserSearch;
	@FXML private Button buttonAboutUs;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(RepoMain.class.getResource("test.fxml"));
		Parent root = loader.load();
//		RepoMain main = loader.getController();
//		main.initial();
		Scene scene = new Scene(root,1190,680);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void initial() throws Exception {
		repoSearchVBox = Box.getInstance();
		pane.getChildren().add(repoSearchVBox);
	}
	public static void main(String[] args) {
		launch(args);
	}
	@FXML
	private void imageMove() {
		Timeline timeline = new Timeline();
		timeline.getKeyFrames()
				.addAll(new KeyFrame(Duration.ZERO, // set start position at 0
						new KeyValue(image.translateXProperty(), 0),
						new KeyValue(image.translateYProperty(), 0)),
						new KeyFrame(new Duration(4000), // set end position at 4s
						new KeyValue(image.translateXProperty(), 1200),
						new KeyValue(image.translateYProperty(), 0)));
		timeline.play();
	}
	@FXML
	private void onRepoSearchClicked(MouseEvent event) throws Exception {
		buttonRepoSearch.setDisable(true);
		buttonUserSearch.setDisable(false);
		buttonAboutUs.setDisable(false);
		this.initial();
	}
	
	@FXML 
	private void onUserSearchClicked(MouseEvent event){
		buttonRepoSearch.setDisable(false);
		buttonUserSearch.setDisable(true);
		buttonAboutUs.setDisable(false);
	}
	
	@FXML
	private void onAboutUsClicked(MouseEvent event){
		buttonRepoSearch.setDisable(false);
		buttonUserSearch.setDisable(false);
		buttonAboutUs.setDisable(true);
	}

}
