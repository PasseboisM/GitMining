package presentation.ui;

import java.io.IOException;
import java.net.MalformedURLException;

import common.exception.NetworkException;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import logic.service.Loader;
import logic.service.LogicServiceFactory;
import logic.service.ServiceConfigure;
import presentation.image.ImageFactory;
import presentation.ui.search.RepositorySearchController;
import presentation.ui.search.UserSearchController;


public class MainController extends Application implements Observer{

	
	private boolean ableToGetData = true;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		Parent root = loader.load();
		MainController controller = loader.getController();
		controller.initial();
		Scene scene = new Scene(root,1190,660);
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void initial() {
		logicServiceFactory = LogicServiceFactory.getInstance();
		serviceConfigure = logicServiceFactory.getServiceConfigure();
		setToggleButtonGroup();
		
		
		String imageFilename ="userSearchBackground.jpg";
		Image bgImage = null;
		try {
			bgImage = ImageFactory.getImageByFileName(imageFilename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		image.setImage(bgImage);
	}

	private void setToggleButtonGroup() {
		toggleGroup = new ToggleGroup();
		buttonLocalMode.setToggleGroup(toggleGroup);
		buttonLocalMode.setSelected(true);
		buttonOnlineMode.setToggleGroup(toggleGroup);
		this.setOnlineOrLocalMode();
	}

	public static void main(String[] args) {
//		Loader.getInstance().startLoading();
		launch(args);
	}
	
	@FXML private ImageView image;
	@FXML private AnchorPane rightComponentParent;
	@FXML private Button buttonRepoSearch;
	@FXML private Button buttonUserSearch;
	@FXML private Button buttonRepoStatistic1,buttonRepoStatistic2,buttonRepoStatistic3,buttonRepoStatistic4;
	@FXML private Button buttonUserStatistic1,buttonUserStatistic2,buttonUserStatistic3,buttonUserStatistic4,buttonUserStatistic5;
	@FXML private ProgressBar progressBar;
	@FXML private ToggleButton buttonLocalMode;
	@FXML private ToggleButton buttonOnlineMode;
				 private ToggleGroup toggleGroup;
				 
				 private LogicServiceFactory logicServiceFactory;
				 private ServiceConfigure serviceConfigure;
	
	@FXML
	private void setOnlineOrLocalMode(){
		boolean isOnlineMode = (toggleGroup.getSelectedToggle()==buttonOnlineMode);
		try {
			serviceConfigure.setOnlineActive(isOnlineMode);
		} catch (NetworkException e) {
			e.printStackTrace();
		}
	}
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
		rightComponentParent.getChildren().clear();
		try {
			rightComponentParent.getChildren().add(UserSearchController.getInstance(rightComponentParent));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onRepoStatisticClicked1(MouseEvent event) {
		
		rightComponentParent.getChildren().clear();
		try {
			System.out.println("do!");
			rightComponentParent.getChildren().add(RepoStatistic_1Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onRepoStatisticClicked2(MouseEvent event) {
		
		rightComponentParent.getChildren().clear();
		try {
			System.out.println("do2!");
			rightComponentParent.getChildren().add(RepoStatistic_2Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onRepoStatisticClicked3(MouseEvent event) {
		
		rightComponentParent.getChildren().clear();
		try {
//			System.out.println("do!");
			rightComponentParent.getChildren().add(RepoStatistic_3Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onRepoStatisticClicked4(MouseEvent event) {
		
		rightComponentParent.getChildren().clear();
		try {
//			System.out.println("do!");
			rightComponentParent.getChildren().add(RepoStatistic_4Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML 
	private void onUserStatisticClicked1(MouseEvent event){
	
		rightComponentParent.getChildren().clear();
		try {
			System.out.println("did!");
			rightComponentParent.getChildren().add(UserStatistic_1Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML 
	private void onUserStatisticClicked2(MouseEvent event){
	
		rightComponentParent.getChildren().clear();
		try {
			System.out.println("did2!");
			rightComponentParent.getChildren().add(UserStatistic_2Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML 
	private void onUserStatisticClicked3(MouseEvent event){
	
		rightComponentParent.getChildren().clear();
		try {

			rightComponentParent.getChildren().add(UserStatistic_3Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@FXML 
	private void onUserStatisticClicked4(MouseEvent event){
	
		rightComponentParent.getChildren().clear();
		try {

			rightComponentParent.getChildren().add(UserStatistic_4Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML 
	private void onUserStatisticClicked5(MouseEvent event){
	
		rightComponentParent.getChildren().clear();
		try {

			rightComponentParent.getChildren().add(UserStatistic_5Controller.getInstance(rightComponentParent));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	@Override
	public void update() {
		LoadProgress loadProgress = Loader.getProgress();
		if (loadProgress.getLoadedRepoNum()>50) {
			this.ableToGetData = true;
		}
//		progressBar.setProgress(1.0*loadProgress.getLoadedRepoNum()/loadProgress.getTotalRepoNum());
//		if (this.progressBar == null) {
//			progressBar = new ProgressBar();
//			progressBar.setPrefWidth(152.0);
//			progressBar.setPrefHeight(11.0);
//		}
//		progressBar.setProgress(1.0*loadProgress.getLoadedRepoNum()/loadProgress.getTotalRepoNum());
//		System.out.println(progressBar.getLayoutX()+"           "+progressBar.getLayoutY());
	}
	@Override
	public void update(Observable observable, Object obj) {
		update();
	}

}
