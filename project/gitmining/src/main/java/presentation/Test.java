package presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import presentation.component.Bar;
import presentation.component.GitPieChart;
import presentation.component.Line;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Double> datas = new ArrayList<>();
		datas.add(1.0);
		datas.add(3.0);
		datas.add(5.0);
		datas.add(7.0);
		datas.add(9.0);
		List<String> headers = new ArrayList<>(Arrays.asList("a","b","c","d","e"));
//		Line rader = new Line(marks);
		GitPieChart pieChart = new GitPieChart(headers, datas, "testPieChart");
//		Line line = new Line(headers,datas,"Line");
		Scene scene = new Scene(pieChart, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
