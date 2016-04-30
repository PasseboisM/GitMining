package presentation.ui.main;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
//		primaryStage.setResizable(false);
		controller.initial();
		Scene scene = new Scene(mainAnchorPane,1190,660);
		primaryStage.setMinHeight(640);
		primaryStage.setMinWidth(960);
		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void loadImgFile() {
		try {
			bgImage = ImageFactory.getImageByFileName(ImageFactory.LOADING_BACKGROUND);
			icon = ImageFactory.getImageByFileName(ImageFactory.GIT_LOGO);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void initial() {
		initialImage();
		initialProgressBar();
		registerToLoader();
//		initialToggleButtonGroup();
	}

	private void initialImage() {
		image = new ImageView();
		image.setImage(bgImage);
		image.fitWidthProperty().bind(mainAnchorPane.widthProperty());
		image.fitHeightProperty().bind(mainAnchorPane.heightProperty());
		mainAnchorPane.getChildren().add(image);
		gitLogoIV.setImage(icon);
	}
	
	private void initialProgressBar(){
		progressBar = new ProgressBar(0);
		
		mainAnchorPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				double apWidth = newValue.doubleValue();
				double pbWidth = apWidth*0.8;
				double pbLayoutX = apWidth*0.1;
				progressBar.setPrefWidth(pbWidth);
				progressBar.setLayoutX(pbLayoutX);
				rightComponentParent.setMinWidth(apWidth-150);
				rightComponentParent.setMaxWidth(apWidth-150);
			}
		});
		
		mainAnchorPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				double apHeight = newValue.doubleValue();
				progressBar.setPrefHeight(30);
				progressBar.setLayoutY(apHeight-45);
				rightComponentParent.setMinHeight(apHeight);
				rightComponentParent.setMaxHeight(apHeight);
			}
		});
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
				progressBar.setProgress(loadRate);
				}});
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
						progressBar.setVisible(false);
						}});
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
						new KeyValue(image.translateXProperty(), mainAnchorPane.getWidth()*0.6),
						new KeyValue(image.translateYProperty(), 0)),
				new KeyFrame(new Duration(2000), // set end position at 2s
						new KeyValue(image.translateXProperty(), mainAnchorPane.getWidth()),
						new KeyValue(image.translateYProperty(), 0)));
		timeline.play();
		try {
			Thread.sleep(2000);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					image.setVisible(false);
					}});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		rightComponentParent.getChildren().add(RepositorySearchController.getInstance(rightComponentParent));
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
	}
	
	@FXML 
	private void onUserSearchClicked(){
		rightComponentParent.getChildren().clear();
		WaitLoader waitLoader = new WaitLoader();
		rightComponentParent.getChildren().add(waitLoader);
		rightComponentParent.getChildren().add(UserSearchController.getInstance(rightComponentParent));
		rightComponentParent.getChildren().remove(waitLoader);
	}
	
	@FXML 
	private void onStatisticClicked(ActionEvent event){
		Button button = (Button) event.getSource();
		rightComponentParent.getChildren().clear();
		WaitLoader waitLoader = new WaitLoader();
		rightComponentParent.getChildren().add(waitLoader);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				AnchorPane anchorPane = MAP_BUTTON_TO_PANE.get(button.getId()).getInstance(rightComponentParent);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						List<Node> childred = rightComponentParent.getChildren();
						if (childred.get(childred.size()-1).equals(waitLoader)) {
							childred.add(anchorPane);
						}
						childred.remove(waitLoader);
					}
				});
			}
		};
		Thread t = new Thread(runnable);
		t.start();
	}
	

	@FXML private AnchorPane rightComponentParent;
	@FXML private Button buttonRepoSearch;
	@FXML private Button buttonUserSearch;
	@FXML private Button buttonLanguage,buttonRepoCreateTime,buttonFork,buttonStar;
	@FXML private Button buttonUserType,buttonUserCreateTime,buttonInEachCompany,buttonBlogCount,buttonLocationCount,buttonEmailCount,buttonFollower,buttonFollowing;
	@FXML private ToggleButton buttonLocalMode;
	@FXML private ToggleButton buttonOnlineMode;
	@FXML private AnchorPane mainAnchorPane;
//	@FXML 
//	@FXML private FlowPane flowpane;
	@FXML private VBox menu;
	@FXML private ImageView gitLogoIV;
	private boolean startViewing = false;
	private ToggleGroup toggleGroup;
	private ImageView image;
	private ProgressBar progressBar;
	
	private LogicServiceFactory logicServiceFactory;
	private ServiceConfigure serviceConfigure;
	
	private static Image bgImage = null;
	private static Image icon = null;
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
