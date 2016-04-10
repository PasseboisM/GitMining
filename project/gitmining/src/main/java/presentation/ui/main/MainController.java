package presentation.ui.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import common.exception.NetworkException;
import common.message.LoadProgress;
import common.util.Observable;
import common.util.Observer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.service.Loader;
import logic.service.LogicServiceFactory;
import logic.service.ServiceConfigure;
import presentation.component.WaitLoader;
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
		loadImgFile();
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("mainController.fxml"));
		mainAnchorPane = loader.load();
		MainController controller = loader.getController();
		controller.initial();
		Scene scene = new Scene(mainAnchorPane,1190,660);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void loadImgFile() {
		String imageFilename ="userSearchBackground.jpg";
		try {
			bgImage = ImageFactory.getImageByFileName(imageFilename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void initial() {
		initialImage();
		initialProgressBar();
		flowpane.getStylesheets().add(MainController.class.getResource("menu.css").toExternalForm());
		registerToLoader();
//		initialToggleButtonGroup();
		buttonUserType.setOnAction((ae)->{
			Button button = (Button) ae.getSource();
			rightComponentParent.getChildren().clear();
			WaitLoader waitLoader = new WaitLoader();
			rightComponentParent.getChildren().add(waitLoader);
			long time1 = System.currentTimeMillis();
			try {
				rightComponentParent.getChildren().add(MAP_BUTTON_TO_PANE.get(button.getId()).getInstance(rightComponentParent));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
			rightComponentParent.getChildren().remove(waitLoader);
		});
	}

	private void initialImage() {
		image = new ImageView();
		image.setImage(bgImage);
		image.setFitWidth(1200);
		image.setFitHeight(675);
		mainAnchorPane.getChildren().add(image);
	}
	
	private void initialProgressBar(){
		progressBar = new ProgressBar(0);
		progressBar.setLayoutX(100);
		progressBar.setLayoutY(625);
		progressBar.setPrefHeight(30);
		progressBar.setPrefWidth(972);
		mainAnchorPane.getChildren().add(progressBar);
	}
	
	
	private void registerToLoader() {
		Loader.getInstance().addObserver(this);
		Loader.getInstance().startLoading();
	}
	
	/*private void initialToggleButtonGroup() {
		toggleGroup = new ToggleGroup();
		buttonLocalMode.setToggleGroup(toggleGroup);
		buttonLocalMode.setSelected(true);
		buttonOnlineMode.setToggleGroup(toggleGroup);
		this.setOnlineOrLocalMode();
	}*/

	@Override
	public void update(Observable observable, Object obj) {
		update();
	}
	
	@Override
	public void update() {
		if(startViewing) return;
		LoadProgress lp = Loader.getProgress();
		double loadRate = (lp.getLoadedRepoNum()+lp.getLoadedUser())*1.0/(lp.getTotalRepoNum()+lp.getTotalUserNum())/LOADING_RATE;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setProgress(loadRate);}});
		if (loadRate >= 1.0) {
			startViewing = true;
			FadeTransition ft = new FadeTransition(Duration.millis(FADE_DURATION), progressBar);
			ft.setFromValue(1.0);
			ft.setToValue(0);
			ft.play();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					onRepoSearchClicked();}});
			try {
				Thread.sleep(FADE_DURATION);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						progressBar.setVisible(false);}});
				imageMoveFrame();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void imageMoveFrame() {
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
	private void onRepoSearchClicked() {
		rightComponentParent.getChildren().clear();
		long time1 = System.currentTimeMillis();
		try {
			rightComponentParent.getChildren().add(RepositorySearchController.getInstance(rightComponentParent));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
	}
	
	@FXML 
	private void onUserSearchClicked(){
		rightComponentParent.getChildren().clear();
		WaitLoader waitLoader = new WaitLoader();
		rightComponentParent.getChildren().add(waitLoader);
		long time1 = System.currentTimeMillis();
		try {
			rightComponentParent.getChildren().add(UserSearchController.getInstance(rightComponentParent));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
		rightComponentParent.getChildren().remove(waitLoader);
	}
	
	@FXML 
	private void onStatisticClicked(ActionEvent event){
		Button button = (Button) event.getSource();
		rightComponentParent.getChildren().clear();
		WaitLoader waitLoader = new WaitLoader();
		rightComponentParent.getChildren().add(waitLoader);
		long time1 = System.currentTimeMillis();
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							rightComponentParent.getChildren().add(MAP_BUTTON_TO_PANE.get(button.getId()).getInstance(rightComponentParent));
							rightComponentParent.getChildren().remove(waitLoader);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		};
		Thread t = new Thread(thread);
		t.start();
		Platform.runLater(thread);
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
	}
	
	/*class RunRunRun implements Runnable {
		private WaitLoader waitLoader;
		private Button button;
		public RunRunRun(WaitLoader waitLoader, Button button) {
			this.waitLoader = waitLoader;
			this.button = button;
		}
		@Override
		public void run() {
			try {
				rightComponentParent.getChildren().add(MAP_BUTTON_TO_PANE.get(button.getId()).getInstance(rightComponentParent));
				rightComponentParent.getChildren().remove(waitLoader);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}*/

	@FXML private AnchorPane rightComponentParent;
	@FXML private Button buttonRepoSearch;
	@FXML private Button buttonUserSearch;
	@FXML private Button buttonLanguage,buttonRepoCreateTime,buttonFork,buttonStar;
	@FXML private Button buttonUserType,buttonUserCreateTime,buttonInEachCompany,buttonBlogCount,buttonLocationCount,buttonEmailCount,buttonFollower,buttonFollowing;
	@FXML private ToggleButton buttonLocalMode;
	@FXML private ToggleButton buttonOnlineMode;
	@FXML private AnchorPane mainAnchorPane;
	@FXML private FlowPane flowpane;
	private boolean startViewing = false;
	private ToggleGroup toggleGroup;
	private ImageView image;
	private ProgressBar progressBar;
	
	private LogicServiceFactory logicServiceFactory;
	private ServiceConfigure serviceConfigure;
	
	private static Image bgImage = null;
	private static final int FADE_DURATION = 3000;
	private static final double LOADING_RATE = 1.0;
	private static final HashMap<String, StatisticsPane> MAP_BUTTON_TO_PANE = new HashMap<String,StatisticsPane>() {
		private static final long serialVersionUID = 1L;
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
	public static void main(String[] args) {
		launch(args);
	}

}
