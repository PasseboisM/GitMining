package presentation;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.component.Bar;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Integer> marks = new ArrayList<>();
		marks.add(1);
		marks.add(3);
		marks.add(5);
		marks.add(7);
		marks.add(9);
		Bar radar = new Bar(marks);
		Scene scene = new Scene(radar, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
