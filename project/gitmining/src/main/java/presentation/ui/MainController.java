package presentation.ui;

import java.io.IOException;

import common.message.LoadProgress;
import common.util.Observable;
import common.util.Observer;
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
import logic.service.Loader;


public class MainController extends Application implements Observer{

	
	private boolean ableToGetData = true;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root,1190,680);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
//		Loader.getInstance().addObserver(this);
	}

	public static void main(String[] args) {
//		Loader.getInstance().startLoading();
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
			if (ableToGetData) {
				rightComponentParent.getChildren().add(RepositorySearchController.getInstance(rightComponentParent));
			}
		} catch (IOException e) {
			e.printStackTrace();
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
		}
	}
	
	@FXML
	private void onAboutUsClicked(MouseEvent event){
		buttonRepoSearch.setDisable(false);
		buttonUserSearch.setDisable(false);
		buttonAboutUs.setDisable(true);
		rightComponentParent.getChildren().clear();
	}

	@Override
	public void update() {
		LoadProgress loadProgress = Loader.getProgress();
		if (loadProgress.getLoadedRepoNum()>50) {
			this.ableToGetData = true;
		}
		
		System.out.println(loadProgress.getLoadedRepoNum());
		
	}

	@Override
	public void update(Observable observable, Object obj) {
		update();
	}
	

	

}
