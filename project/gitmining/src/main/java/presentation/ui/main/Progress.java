//package presentation.ui.main;
//
//
//import common.message.LoadProgress;
//import common.util.Observable;
//import common.util.Observer;
//import javafx.animation.FadeTransition;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ProgressBar;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import logic.data.MinInfoManager;
//import logic.service.Loader;
//
//public class Progress extends Application implements Observer{
//	private boolean a = false;
//	private Stage stage = null;
//	private Button button = null;
//	private ProgressBar pb;
//	private AnchorPane anchorPane;
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		stage = primaryStage;
//		pb = new ProgressBar(-1.0f);
//		anchorPane = new AnchorPane();
//		button = new Button("start");
//		button.setOnAction((action)->{
//			try {
//				primaryStage.setScene(MainController.getScene());
//				primaryStage.centerOnScreen();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		anchorPane.getChildren().addAll(button,pb);
//		Scene scene = new Scene(anchorPane,800,600);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		Loader.getInstance().addObserver(this);
//		Loader.getInstance().startLoading();
//	}
//	public static void main(String[] args) {
//		launch(args);
//	}
//	@Override
//	public void update() {
//		MinInfoManager manager = MinInfoManager.getInstance();
//		LoadProgress progress = manager.getProgress();
////		System.out.println(progress.getLoadedRepoNum()+"    "+progress.getTotalRepoNum()+"    "+progress.getLoadedRepoNum()*1.0/progress.getTotalRepoNum());
////		System.out.println(progress.getLoadedRepoNum()*1.0/progress.getTotalRepoNum());
////		pb.setProgress(progress.getLoadedRepoNum()*1.0/progress.getTotalRepoNum());
////		if (pb.getProgress()==1&&!a) {
////			pb.setVisible(false);
////			FadeTransition ft = new FadeTransition(Duration.millis(3000), button);
////			ft.setFromValue(1.0);
////			ft.setToValue(0);
////			ft.play();
////			a = true;
////		}
//	}
//	@Override
//	public void update(Observable observable, Object obj) {
//		update();
//	}
//}
