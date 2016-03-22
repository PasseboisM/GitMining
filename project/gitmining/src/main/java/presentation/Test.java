package presentation;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.component.Radar;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Double> marks = new ArrayList<>();
		marks.add(0.1);
		marks.add(0.3);
		marks.add(0.5);
		marks.add(0.7);
		marks.add(0.9);
		Radar radar = new Radar(marks);
		Scene scene = new Scene(radar, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
