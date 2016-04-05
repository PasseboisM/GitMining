package presentation.ui.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import common.exception.NetworkException;
import common.message.LoadProgress;
import common.util.Observable;
import common.util.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import presentation.ui.statistics.StatisticsPane;
import presentation.ui.statistics.repo.RepoCreateTimeStatistic;
import presentation.ui.statistics.repo.RepoForkStatistic;
import presentation.ui.statistics.repo.RepoLanguageStatistic;
import presentation.ui.statistics.repo.RepoStarStatistic;
import presentation.ui.statistics.user.UserBlogCountStatisticsPane;
import presentation.ui.statistics.user.UserCreateTimeStatisticsPane;
import presentation.ui.statistics.user.UserEmailCountStatisticsPane;
import presentation.ui.statistics.user.UserFollowerStatisticsPane;
import presentation.ui.statistics.user.UserFollowingStatisticsPane;
import presentation.ui.statistics.user.UserInEachCompanyStatisticsPane;
import presentation.ui.statistics.user.UserLocationCountStatisticsPane;
import presentation.ui.statistics.user.UserTypeStatisticsPane;


public class MainController extends Application implements Observer{

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		mainAnchorPane = loader.load();
//		System.out.println(mainAnchorPane.getChildren().get(1));
		MainController controller = loader.getController();
		controller.setMainAnchorPane(mainAnchorPane);
		
		Scene scene = new Scene(mainAnchorPane,1190,660);
		
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		controller.initial();
		primaryStage.show();
		Loader.getInstance().addObserver(controller);
		Loader.getInstance().startLoading();
	}
	
	public static Scene getScene() throws Exception{
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		Parent root = loader.load();
		MainController controller = loader.getController();
		controller.initial();
		Scene scene = new Scene(root,1190,660);
		return scene;
	}

	

	private void initialImage() {
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
//		Platform.setImplicitExit(false);
		launch(args);
	}
	
	@FXML private ImageView image;
	@FXML private AnchorPane rightComponentParent;
	@FXML private Button buttonRepoSearch;
	@FXML private Button buttonUserSearch;
	@FXML private Button buttonLanguage,buttonRepoCreateTime,buttonFork,buttonStar;
	@FXML private Button buttonUserType,buttonUserCreateTime,buttonInEachCompany,buttonBlogCount,buttonLocationCount,buttonEmailCount,buttonFollower,buttonFollowing;
				 private ProgressBar progressBar; 	
	@FXML private ToggleButton buttonLocalMode;
	@FXML private ToggleButton buttonOnlineMode;
	@FXML private AnchorPane mainAnchorPane;
				 private ToggleGroup toggleGroup;
				 
				 private LogicServiceFactory logicServiceFactory;
				 private ServiceConfigure serviceConfigure;
	
	public AnchorPane getMainAnchorPane() {
		return mainAnchorPane;
	}

	public void setMainAnchorPane(AnchorPane mainAnchorPane) {
		this.mainAnchorPane = mainAnchorPane;
	}

	@FXML
	private void setOnlineOrLocalMode(){
		logicServiceFactory = LogicServiceFactory.getInstance();
		serviceConfigure = logicServiceFactory.getServiceConfigure();
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
//			if (ableToGetData) {
				rightComponentParent.getChildren().add(RepositorySearchController.getInstance(rightComponentParent));
//			}
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
	private void onStatisticClicked(ActionEvent event){
		Button button = (Button) event.getSource();
//		System.out.println(button.getId());
		rightComponentParent.getChildren().clear();
		try {
			rightComponentParent.getChildren().add(MAP.get(button.getId()).getInstance(rightComponentParent));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("serial")
	private  static final  HashMap<String, StatisticsPane> MAP = new HashMap<String,StatisticsPane>() {
		{
			put("buttonLanguage", new RepoLanguageStatistic());
			put("buttonRepoCreateTime", new RepoCreateTimeStatistic());
			put("buttonFork", new RepoForkStatistic());
			put("buttonStar", new RepoStarStatistic());
			
			put("buttonUserType", new UserTypeStatisticsPane());
			put("buttonUserCreateTime", new UserCreateTimeStatisticsPane());
			put("buttonInEachCompany", new UserInEachCompanyStatisticsPane());
			put("buttonBlogCount", new UserBlogCountStatisticsPane());
			put("buttonLocationCount", new UserLocationCountStatisticsPane());
			put("buttonEmailCount", new UserEmailCountStatisticsPane());
			put("buttonFollower", new UserFollowerStatisticsPane());
			put("buttonFollowing", new UserFollowingStatisticsPane());
		}
	};

	private void initial() {
		initialImage();
		initialProgressBar();
		setToggleButtonGroup();
	}
	@Override
	public void update() {
		LoadProgress lp = Loader.getProgress();
		double loadRate = (lp.getLoadedRepoNum()+lp.getLoadedUser())*1.0/(lp.getTotalRepoNum()+lp.getTotalUserNum());
		System.out.println(loadRate);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setProgress(loadRate);
			}
		});
	}
	
	private void initialProgressBar(){
		progressBar = new ProgressBar(0);
		progressBar.setLayoutX(100);
		progressBar.setLayoutY(625);
		progressBar.setPrefHeight(30);
		progressBar.setPrefWidth(972);
		mainAnchorPane.getChildren().add(progressBar);
	}

	@Override
	public void update(Observable observable, Object obj) {
		update();
	}

}
