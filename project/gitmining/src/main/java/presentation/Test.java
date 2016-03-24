package presentation;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import presentation.component.Bar;
import presentation.component.Line;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Double> marks = new ArrayList<>();
		marks.add((double) 1.0);
		marks.add((double) 3.0);
		marks.add((double) 5.0);
		marks.add((double) 7.0);
		marks.add((double) 9.0);
		Line rader = new Line(marks);
		Scene scene = new Scene(rader, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
